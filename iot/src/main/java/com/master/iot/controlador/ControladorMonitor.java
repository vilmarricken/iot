package com.master.iot.controlador;

import org.apache.log4j.Logger;

import com.master.iot.entity.Componente;
import com.master.iot.entity.Historico;
import com.master.iot.entity.Monitor;
import com.master.iot.entity.MonitorTipo;

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
			final float limite = this.monitor.getLimite();
			final MonitorTipo tipo = this.monitor.getTipo();
			final Componente componente = this.monitor.getControlador();
			while (this.isExecutando()) {
				final float leitura = this.leitura(this.monitor.getLeitor());
				if (tipo.ligar(alvo, limite, leitura)) {
					this.ligar(componente, new Historico(componente));
				}
				if (tipo.desligar(alvo, limite, leitura)) {
					this.desligar(componente, new Historico(componente));
				}
				this.sleep(30000);
			}
		} catch (final Exception e) {
			ControladorMonitor.log.error(e.getMessage(), e);
		} finally {
			this.setExecutando(false);
		}
	}

}
