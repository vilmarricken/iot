package com.master.iot.core.entity.manager;

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
		config.applySetting(AvailableSettings.JPA_JDBC_DRIVER, "");
		config.applySetting(AvailableSettings.JPA_JDBC_URL, "");
		config.applySetting(AvailableSettings.JPA_JDBC_USER, "");
		config.applySetting(AvailableSettings.JPA_JDBC_PASSWORD, "");
		config.build();

		MetadataSources ms = new MetadataSources();
		ms.SessionFactory s = ms.buildMetadata().buildSessionFactory();

		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		StandardServiceRegistryBuilder.destroy(registry);

		// Map<String, String> properties = new HashMap<>();
		// properties.put(TRANSACTION_TYPE,
		// PersistenceUnitTransactionType.RESOURCE_LOCAL.name());
		// properties.put(JDBC_DRIVER, "org.postgresql.Driver");
		// properties.put(JDBC_URL, "jdbc:postgresql://serverdb:5432/iot");
		// properties.put(JDBC_USER, "postgres");
		// properties.put(JDBC_PASSWORD, "postgres");
		// properties.put(LOGGING_LEVEL, "FINE");
		// properties.put(LOGGING_TIMESTAMP, "false");
		// properties.put(LOGGING_THREAD, "false");
		// properties.put(LOGGING_SESSION, "false");
		String unitName = "DEFAULT";
		return new MasterEntityManagerFactory(sessionFactory);
	}
}
