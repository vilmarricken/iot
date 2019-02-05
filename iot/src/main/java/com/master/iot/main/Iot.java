package com.master.iot.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.master.core.exception.MasterException;
import com.master.core.persistence.PersistenceException;
import com.master.core.persistence.dao.DaoEntity;
import com.master.core.resource.MasterContextRead;
import com.master.core.resource.MasterRunnable;
import com.master.core.resource.MasterThread;
import com.master.iot.controlador.ControladorMonitor;
import com.master.iot.controlador.ControladorTemporizador;
import com.master.iot.entity.Monitor;
import com.master.iot.entity.Temporizador;
import com.master.iot.entity.dao.MonitorDao;
import com.master.iot.entity.dao.TemporizadorDao;

public class Iot implements MasterRunnable {

	private static Iot iot;

	private final Map<String, ControladorMonitor> monitores = new HashMap<>();

	private final Map<String, ControladorTemporizador> temporizadores = new HashMap<>();

	Iot() throws MasterException {
		new MasterThread(this, new MasterContextRead()).run();
	}

	@Override
	public void run() throws MasterException {
		try {
			final DaoEntity<Monitor> daoMonitor = new MonitorDao();
			final List<Monitor> monitors = daoMonitor.all();
			if (monitors != null) {
				for (final Monitor monitor : monitors) {
					this.add(new ControladorMonitor(monitor));
				}
			}
			final DaoEntity<Temporizador> daoTemporizador = new TemporizadorDao();
			final List<Temporizador> temporiadores = daoTemporizador.all();
			if (temporiadores != null) {
				for (final Temporizador temporizador : temporiadores) {
					this.add(new ControladorTemporizador(temporizador));
				}
			}
		} catch (final PersistenceException e) {
			throw new MasterException(e);
		}
	}

	private void add(ControladorTemporizador controladorTemporizador) {
		this.temporizadores.put(controladorTemporizador.getNome(), controladorTemporizador);
	}

	private void add(ControladorMonitor controladorMonitor) {
		this.monitores.put(controladorMonitor.getNome(), controladorMonitor);
	}

	public static void main(String[] args) throws MasterException {
		Iot.getIot();
	}

	public static Iot getIot() throws MasterException {
		if (Iot.iot == null) {
			Iot.iot = new Iot();
		}
		return Iot.iot;
	}

}
