package com.master.iot.controller;

import org.apache.log4j.Logger;

import com.master.iot.entity.Component;
import com.master.iot.entity.History;
import com.master.iot.entity.Monitor;
import com.master.iot.entity.MonitorType;

public class ControllerMonitor extends Controller implements Runnable {

	private static final Logger log = Logger.getLogger(ControllerMonitor.class);

	private final Monitor monitor;

	public ControllerMonitor(final Monitor monitor) {
		super("Monitor:" + monitor.getName());
		this.monitor = monitor;
	}

	public String getName() {
		return this.monitor.getName();
	}

	@Override
	public void execute() {
		new Thread(this, this.monitor.getName()).start();
	}

	private float leitura(final Component leitor) {

		return 0;
	}

	@Override
	public void run() {
		try {
			this.setRunning(true);
			final float target = this.monitor.getTarget();
			final float limit = this.monitor.getDelay();
			final MonitorType type = this.monitor.getType();
			final Component component = this.monitor.getController();
			while (this.isRunning()) {
				final float read = this.leitura(this.monitor.getReader());
				if (type.on(target, limit, read)) {
					this.on(component, new History(component));
				}
				if (type.off(target, limit, read)) {
					this.desligar(component, new History(component));
				}
				this.sleep(30000);
			}
		} catch (final Exception e) {
			ControllerMonitor.log.error(e.getMessage(), e);
		} finally {
			this.setRunning(false);
		}
	}

}
