package com.master.iot.controlador;

import org.apache.log4j.Logger;

import com.master.iot.entity.Componente;
import com.master.iot.entity.Monitor;
import com.master.iot.entity.TemporizadorTipo;

public class ControladorMonitor extends Controlador implements Runnable {

	private static final Logger log = Logger.getLogger(ControladorMonitor.class);

	private final Monitor monitor;

	public ControladorMonitor(final Monitor monitor) {
		super("Monitor:" + monitor.getNome());
		this.monitor = monitor;
	}

	@Override
	public void execute() {
		new Thread(this, this.monitor.getNome()).start();
	}

	private float leitura(final Componente leitor) {

		return 0;
	}

	@Override
	public void run() {
		try {
			this.setExecutando(true);
			final float alvo = this.monitor.getAlvo();
			final float leitura = this.leitura(this.monitor.getLeitor());
			final boolean iniciar = TemporizadorTipo.START_ON.equals(this.temporizador.getTipo());
			final int tempoLigado = this.getValue(this.temporizador.getLigado(), 60, "Ligado");
			final int tempoDesligado = this.getValue(this.temporizador.getDesligado(), 1800, "Desligado");
			final int inicial = this.getValue(this.temporizador.getInicial(), -1, "Inicial");
			if (ControladorMonitor.log.isTraceEnabled()) {
				ControladorMonitor.log.trace("Executando temporizador: " + this.temporizador);
			}
			if (iniciar) {
				this.ligar(tempoLigado);
			}
			final long incremento = (tempoLigado + tempoDesligado) * 60_000;
			long proximaExecucao = ControladorMonitor.calcularProximaExecucao(inicial, incremento);
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
			ControladorMonitor.log.error(e.getMessage(), e);
		} finally {
			this.setExecutando(false);
		}
	}

}
