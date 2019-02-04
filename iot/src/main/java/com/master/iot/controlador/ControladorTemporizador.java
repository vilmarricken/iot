package com.master.iot.controlador;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

import com.master.core.exception.MasterException;
import com.master.iot.entity.Componente;
import com.master.iot.entity.Historico;
import com.master.iot.entity.Temporizador;
import com.master.iot.entity.TemporizadorTipo;
import com.master.iot.entity.dao.HistoricoInsertExceptionDao;

public class ControladorTemporizador extends Controlador implements Runnable {

	private static final Logger log = Logger.getLogger(ControladorTemporizador.class);

	private static long calcularProximaExecucao(final int inicial, final long incremento) {
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

	public static void main(final String[] args) {
		final long t = (System.currentTimeMillis() % 3600000) / 1000;
		System.out.println(t);
		System.out.println((t % 60));
		System.out.println(1000 * 60 * 30);
	}

	private final Temporizador temporizador;

	public ControladorTemporizador(final Temporizador temporizador) {
		super("Temporizador:" + temporizador.getNome());
		this.temporizador = temporizador;
	}

	@Override
	public void execute() {
		new Thread(this, this.temporizador.getNome()).start();
	}

	private void ligar(final long tempoLigado) throws MasterException {
		final Componente componente = this.temporizador.getComponente();
		try {
			this.ligar(componente, new Historico(this.temporizador));
			this.sleep(tempoLigado);
			this.desligar(componente, new Historico(ControladorTemporizador.this.temporizador));
		} catch (final MasterException e) {
			this.saveHistorico(new HistoricoInsertExceptionDao(new Historico(this.temporizador), e));
			this.desligar(componente, new Historico(ControladorTemporizador.this.temporizador));
			throw e;
		} catch (final Exception e) {
			this.saveHistorico(new HistoricoInsertExceptionDao(new Historico(this.temporizador), e));
			this.desligar(componente, new Historico(ControladorTemporizador.this.temporizador));
			throw new MasterException(e);
		}
	}

	@Override
	public void run() {
		try {
			this.setExecutando(true);
			final boolean iniciar = TemporizadorTipo.START_ON.equals(this.temporizador.getTipo());
			final int tempoLigado = this.getValue(this.temporizador.getLigado(), 60, "Ligado");
			final int tempoDesligado = this.getValue(this.temporizador.getDesligado(), 1800, "Desligado");
			final int inicial = this.getValue(this.temporizador.getInicial(), -1, "Inicial");
			if (ControladorTemporizador.log.isTraceEnabled()) {
				ControladorTemporizador.log.trace("Executando temporizador: " + this.temporizador);
			}
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
