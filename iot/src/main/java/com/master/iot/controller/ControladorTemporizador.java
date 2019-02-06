package com.master.iot.controller;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

import com.master.core.exception.MasterException;
import com.master.iot.entity.Component;
import com.master.iot.entity.History;
import com.master.iot.entity.Timer;
import com.master.iot.entity.dao.HistoryInsertExceptionDao;

public class ControladorTemporizador extends Controller implements Runnable {

	private static final Logger log = Logger.getLogger(ControladorTemporizador.class);

	public String getNome() {
		return this.timer.getName();
	}

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

	private final Timer timer;

	public ControladorTemporizador(final Timer temporizador) {
		super("Temporizador:" + temporizador.getName());
		this.timer = temporizador;
	}

	@Override
	public void execute() {
		new Thread(this, this.timer.getName()).start();
	}

	private void ligar(final long tempoLigado) throws MasterException {
		final Component componente = this.timer.getComponent();
		try {
			this.on(componente, new History(this.timer));
			this.sleep(tempoLigado);
			this.desligar(componente, new History(ControladorTemporizador.this.timer));
		} catch (final MasterException e) {
			this.saveHistory(new HistoryInsertExceptionDao(new History(this.timer), e));
			this.desligar(componente, new History(ControladorTemporizador.this.timer));
			throw e;
		} catch (final Exception e) {
			this.saveHistory(new HistoryInsertExceptionDao(new History(this.timer), e));
			this.desligar(componente, new History(ControladorTemporizador.this.timer));
			throw new MasterException(e);
		}
	}

	@Override
	public void run() {
		try {
			this.setRunning(true);
			final int timerOn = this.getValue(this.timer.getOn(), 60, "On");
			final int timerOff = this.getValue(this.timer.getOff(), 1800, "Off");
			Integer start = this.timer.getStart();
			if (ControladorTemporizador.log.isTraceEnabled()) {
				ControladorTemporizador.log.trace("Executando temporizador: " + this.timer);
			}
			this.ligar(timerOn);
			final long incremento = (timerOn + timerOff) * 60_000;
			long proximaExecucao = ControladorTemporizador.calcularProximaExecucao(start, incremento);
			synchronized (this) {
				while (this.isRunning()) {
					this.sleep(proximaExecucao - System.currentTimeMillis());
					if (this.isRunning() || proximaExecucao > System.currentTimeMillis()) {
						continue;
					}
					this.ligar(timerOn);
					proximaExecucao += incremento;
				}
			}
		} catch (final Exception e) {
			ControladorTemporizador.log.error(e.getMessage(), e);
		} finally {
			this.setRunning(false);
		}
	}

}
