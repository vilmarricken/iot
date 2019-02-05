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

	private Map<String, ControladorMonitor> monitores = new HashMap<>();

	private Map<String, ControladorTemporizador> temporizadores = new HashMap<>();

	Iot() throws MasterException {
		new MasterThread(new Iot(), new MasterContextRead()).run();
	}

	@Override
	public void run() throws MasterException {
		try {
			DaoEntity<Monitor> daoMonitor = new MonitorDao();
			List<Monitor> monitors = daoMonitor.all();
			if (monitors != null) {
				for (Monitor monitor : monitors) {
					this.add(new ControladorMonitor(monitor));
				}
			}
			DaoEntity<Temporizador> daoTemporizador = new TemporizadorDao();
			List<Temporizador> temporiadores = daoTemporizador.all();
			if (temporiadores != null) {
				for (Temporizador temporizador : temporiadores) {
					this.add(new ControladorTemporizador(temporizador));
				}
			}
		} catch (PersistenceException e) {
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
		getIot();
	}

	public static Iot getIot() throws MasterException {
		if (iot == null) {
			iot = new Iot();
		}
		return iot;
	}

}
