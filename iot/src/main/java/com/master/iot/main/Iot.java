package com.master.iot.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.master.core.exception.MasterException;
import com.master.core.persistence.PersistenceException;
import com.master.core.persistence.dao.DaoEntity;
import com.master.core.resource.MasterContextRead;
import com.master.core.resource.MasterRunnable;
import com.master.core.resource.MasterThread;
import com.master.core.util.LogUtil;
import com.master.iot.controller.ControllerMonitor;
import com.master.iot.controller.ControllerTimer;
import com.master.iot.entity.Monitor;
import com.master.iot.entity.Timer;
import com.master.iot.entity.dao.MonitorDao;
import com.master.iot.entity.dao.TimerDao;
import com.master.iot.server.IotServer;

public class Iot implements MasterRunnable {

	private static Iot iot;

	private final IotServer server = new IotServer();

	private final Map<String, ControllerMonitor> monitors = new HashMap<>();

	private final Map<String, ControllerTimer> timers = new HashMap<>();

	private static final Logger log = Logger.getLogger(Iot.class);

	Iot() throws MasterException {
		new MasterThread(this, new MasterContextRead()).run();
	}

	@Override
	public void run() throws MasterException {
		try {
			final DaoEntity<Monitor> daoMonitor = new MonitorDao();
			if (log.isDebugEnabled()) {
				log.debug("Initializing monitors");
			}
			final List<Monitor> monitors = daoMonitor.all();
			if (monitors != null) {
				for (final Monitor monitor : monitors) {
					this.add(new ControllerMonitor(monitor));
				}
			}
			if (log.isDebugEnabled()) {
				log.debug("Initializing timers");
			}
			final DaoEntity<Timer> daoTimers = new TimerDao();
			final List<Timer> timers = daoTimers.all();
			if (timers != null) {
				for (final Timer timer : timers) {
					this.add(new ControllerTimer(timer));
				}
			}
		} catch (final PersistenceException e) {
			throw new MasterException(e);
		}
		this.startServer();
	}

	private void startServer() {
		new Thread() {
			@Override
			public void run() {
				Iot.this.server.listen();
			}
		}.start();
	}

	private void add(ControllerTimer controladorTemporizador) {
		this.timers.put(controladorTemporizador.getNome(), controladorTemporizador);
	}

	private void add(ControllerMonitor controladorMonitor) {
		this.monitors.put(controladorMonitor.getName(), controladorMonitor);
	}

	public static void main(String[] args) throws MasterException {
		LogUtil.config();
		Iot.getIot();
	}

	public static Iot getIot() throws MasterException {
		if (Iot.iot == null) {
			Iot.iot = new Iot();
		}
		return Iot.iot;
	}

}
