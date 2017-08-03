package com.master.platform.core.entity.manager;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;

public class MasterEntityManagerCreator {

	public MasterEntityManagerCreator() {
	}

	static final MasterEntityManagerFactory create() {

		StandardServiceRegistryBuilder config = new StandardServiceRegistryBuilder();
		config.configure("org/hibernate/example/hibernate.cfg.xml");
		config.applySetting(AvailableSettings.JPA_JDBC_DRIVER, "org.postgresql.Driver");
		config.applySetting(AvailableSettings.JPA_JDBC_URL, "jdbc:postgresql://serverdb:5432/iot");
		config.applySetting(AvailableSettings.JPA_JDBC_USER, "postgres");
		config.applySetting(AvailableSettings.JPA_JDBC_PASSWORD, "postgres");
		StandardServiceRegistry ssr = config.build();

		MetadataSources ms = new MetadataSources(ssr);
		ms.addAnnotatedClass(null);
		SessionFactory sessionFactory = ms.buildMetadata().buildSessionFactory();

		return new MasterEntityManagerFactory(sessionFactory);
	}
}
