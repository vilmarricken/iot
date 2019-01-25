package com.master.iot.controlador;

import org.apache.log4j.Logger;

import com.master.core.exception.MasterException;
import com.master.core.persistence.PersistenceManager;
import com.master.iot.entity.Componente;
import com.master.iot.entity.Historico;
import com.master.iot.entity.Placa;
import com.master.iot.entity.Situacao;
import com.master.iot.entity.Temporizador;
import com.master.iot.entity.TemporizadorTipo;
import com.master.iot.entity.dao.HistoricoInsertDao;
import com.master.iot.entity.dao.HistoricoUpdateFinishDao;
import com.master.iot.server.IOTConnection;

public class ControladorTemporizador extends Controlador implements Runnable {

	private static final Logger log = Logger.getLogger(ControladorTemporizador.class);

	private final IOTConnection connection = new IOTConnection();

	private final Temporizador temporizador;

	public ControladorTemporizador(final Temporizador temporizador) {
		super();
		this.temporizador = temporizador;
	}

	@Override
	public void execute() {
		new Thread(this, this.temporizador.getNome()).start();
	}

	private long getTempoLigado() {
		Integer ligado = ControladorTemporizador.this.temporizador.getLigado();
		if (ligado == null) {
			ligado = 60000;
			ControladorTemporizador.log.warn("Tempo ligado é nulo, utilizando valor padrão de 1 minuto");
		}
		return ligado.longValue();
	}

	private void ligar() throws MasterException {
		final long ligado = this.getTempoLigado();
		final Historico historico = new Historico(ControladorTemporizador.this.temporizador, Situacao.EXECUTANDO);
		PersistenceManager.getPersistence().execute(new HistoricoInsertDao(historico));
		final Componente componente = this.temporizador.getComponente();
		final Placa placa = componente.getPlaca();
		try {
			this.connection.ligar(placa.getIp(), componente.getPorta().toString());
		} catch (final Exception e) {
			final MasterException ex = new MasterException("Erro ao conectar na placa " + placa.getNome() + " - " + placa.getIp() + " na porta " + componente.getPorta(), e);
			PersistenceManager.getPersistence().execute(new HistoricoUpdateFinishDao(historico, e));
			throw ex;
		}
		this.sleep(ligado);
	}

	@Override
	public void run() {
		try {
			ControladorTemporizador.this.setExecutando(true);
			final boolean iniciar = TemporizadorTipo.START_ON.equals(ControladorTemporizador.this.temporizador.getTipo());
			if (iniciar) {
				this.ligar();
			}
		} catch (final Exception e) {
			ControladorTemporizador.log.error(e.getMessage(), e);
		} finally {
			this.setExecutando(false);
		}
	}

}
