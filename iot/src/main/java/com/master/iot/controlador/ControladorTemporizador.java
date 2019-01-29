package com.master.iot.controlador;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

import com.master.core.exception.MasterException;
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
		super("Temporizador:" + temporizador.getNome());
		this.temporizador = temporizador;
	}

	@Override
	public void execute() {
		new Thread(this, this.temporizador.getNome()).start();
	}

	private void ligar(long tempoLigado) throws MasterException {
		final Historico historico = new Historico(ControladorTemporizador.this.temporizador, Situacao.EXECUTANDO);
		this.saveHistorico(new HistoricoInsertDao(historico));
		final Componente componente = this.temporizador.getComponente();
		final Placa placa = componente.getPlaca();
		try {
			this.connection.ligar(placa.getIp(), componente.getPorta().toString());
		} catch (final Exception e) {
			final MasterException ex = new MasterException("Erro ao conectar na placa " + placa.getNome() + " - " + placa.getIp() + " na porta " + componente.getPorta(), e);
			this.saveHistorico(new HistoricoUpdateFinishDao(historico, e));
			throw ex;
		}
		this.sleep(tempoLigado);
	}

	public static void main(String[] args) {
		final long t = (System.currentTimeMillis() % 3600000) / 1000;
		System.out.println(t);
		System.out.println((t % 60));
		System.out.println(1000 * 60 * 30);
	}

	private static long calcularProximaExecucao(int inicial, long incremento) {
		final GregorianCalendar gc = new GregorianCalendar();
		gc.setTimeInMillis(System.currentTimeMillis());
		if (inicial >= 0) {
			gc.set(Calendar.MINUTE, inicial);
		}
		long proxima = gc.getTimeInMillis();
		while (proxima < System.currentTimeMillis()) {
			proxima = gc.getTimeInMillis() + incremento;
		}
		return proxima;
	}

	@Override
	public void run() {
		try {
			ControladorTemporizador.this.setExecutando(true);
			final boolean iniciar = TemporizadorTipo.START_ON.equals(ControladorTemporizador.this.temporizador.getTipo());
			final int tempoLigado = this.getValue(this.temporizador.getLigado(), 60, "Ligado");
			final int tempoDesligado = this.getValue(this.temporizador.getDesligado(), 1800, "Desligado");
			final int inicial = this.getValue(this.temporizador.getInicial(), -1, "Inicial");
			if (iniciar) {
				this.ligar(tempoLigado);
			}
			final long incremento = (tempoLigado + tempoDesligado) * 60_000;
			long proximaExecucao = ControladorTemporizador.calcularProximaExecucao(inicial, incremento);
			synchronized (this) {
				while (this.isExecutando()) {
					this.sleep(proximaExecucao - System.currentTimeMillis());
					if (this.isExecutando() || proximaExecucao > System.currentTimeMillis()) {
						continue;
					}
					this.ligar(tempoLigado);
					proximaExecucao += incremento;
				}
			}
		} catch (final Exception e) {
			ControladorTemporizador.log.error(e.getMessage(), e);
		} finally {
			this.setExecutando(false);
		}
	}

}
