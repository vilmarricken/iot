package com.master.persistence;

import java.io.File;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.master.iot.entity.Componente;
import com.master.iot.entity.Historico;
import com.master.iot.entity.Monitor;
import com.master.iot.entity.Placa;
import com.master.iot.entity.Temporizador;

public class PersistenceManager {

	private static PersistenceManager INSTANCE;

	private static final Object LOCK = new Object();

	private final SessionFactory factory;

	private List<PersistenceSessionControle> sessions;

	public PersistenceManager() {
		final Configuration config = new Configuration();
		final File f = new File("hibernate.cfg.xml");
		System.out.println(f.getAbsolutePath() + " - " + f.exists());
		config.configure(f);
		config.addAnnotatedClass(Componente.class);
		config.addAnnotatedClass(Placa.class);
		config.addAnnotatedClass(Historico.class);
		config.addAnnotatedClass(Monitor.class);
		config.addAnnotatedClass(Temporizador.class);
		final ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
		this.factory = config.buildSessionFactory(registry);
	}

	public static PersistenceManager getInstance() {
		if (PersistenceManager.INSTANCE != null) {
			return PersistenceManager.INSTANCE;
		}
		return PersistenceManager.newInstance();
	}

	public static final Persistence getPersistence() {
		final PersistenceManager manager = PersistenceManager.getInstance();
		return manager.openSession();
	}

	private Persistence openSession() {
		final PersistenceSessionControle session = new PersistenceSessionControle(this.factory.openSession());
		this.sessions.add(session);
		return new Persistence(this, session);
	}

	private static PersistenceManager newInstance() {
		synchronized (PersistenceManager.LOCK) {
			PersistenceManager.INSTANCE = new PersistenceManager();
		}
		return PersistenceManager.INSTANCE;
	}

	public void closeSession(PersistenceSessionControle session) {

	}

	public void close(PersistenceSessionControle sessionController) {

	}

}
