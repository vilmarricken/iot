package com.master.core.persistence;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.master.iot.entity.Component;
import com.master.iot.entity.History;
import com.master.iot.entity.Monitor;
import com.master.iot.entity.Board;
import com.master.iot.entity.Timer;

public class PersistenceManager {

	private static PersistenceManager INSTANCE;

	private static final Object LOCK = new Object();

	private static final Logger LOG = Logger.getLogger(PersistenceManager.class);

	private static final ThreadLocal<Persistence> PERSISTENCE = new ThreadLocal<>();

	public static PersistenceManager getInstance() {
		if (PersistenceManager.INSTANCE == null) {
			PersistenceManager.INSTANCE = PersistenceManager.newInstance();
		}
		return PersistenceManager.INSTANCE;
	}

	public static final Persistence getPersistence() {
		final PersistenceManager manager = PersistenceManager.getInstance();
		Persistence persistence = PersistenceManager.PERSISTENCE.get();
		if (persistence == null) {
			persistence = manager.openSession();
			PersistenceManager.PERSISTENCE.set(persistence);
		}
		return persistence;
	}

	private static PersistenceManager newInstance() {
		synchronized (PersistenceManager.LOCK) {
			PersistenceManager.INSTANCE = new PersistenceManager();
		}
		return PersistenceManager.INSTANCE;
	}

	private final SessionFactory factory;

	private final List<PersistenceSessionController> sessions = new ArrayList<>();

	public PersistenceManager() {
		final Configuration config = new Configuration();
		final File f = new File("hibernate.cfg.xml");
		System.out.println(f.getAbsolutePath() + " - " + f.exists());
		config.configure(f);
		config.addAnnotatedClass(Component.class);
		config.addAnnotatedClass(Board.class);
		config.addAnnotatedClass(History.class);
		config.addAnnotatedClass(Monitor.class);
		config.addAnnotatedClass(Timer.class);
		final ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
		this.factory = config.buildSessionFactory(registry);
	}

	void closeSession(final PersistenceSessionController session) {
		session.close();
		if (!this.sessions.remove(session)) {
			PersistenceManager.LOG.error("There is not session: " + session);
		}
	}

	private Persistence openSession() {
		final PersistenceSessionController session = new PersistenceSessionController(this.factory.openSession());
		this.sessions.add(session);
		return new Persistence(this, session);
	}

}
