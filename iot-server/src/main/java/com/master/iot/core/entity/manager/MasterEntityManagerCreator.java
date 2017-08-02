package com.master.iot.core.entity.manager;

import static org.eclipse.persistence.config.PersistenceUnitProperties.JDBC_DRIVER;
import static org.eclipse.persistence.config.PersistenceUnitProperties.JDBC_PASSWORD;
import static org.eclipse.persistence.config.PersistenceUnitProperties.JDBC_URL;
import static org.eclipse.persistence.config.PersistenceUnitProperties.JDBC_USER;
import static org.eclipse.persistence.config.PersistenceUnitProperties.LOGGING_LEVEL;
import static org.eclipse.persistence.config.PersistenceUnitProperties.LOGGING_SESSION;
import static org.eclipse.persistence.config.PersistenceUnitProperties.LOGGING_THREAD;
import static org.eclipse.persistence.config.PersistenceUnitProperties.LOGGING_TIMESTAMP;
import static org.eclipse.persistence.config.PersistenceUnitProperties.TRANSACTION_TYPE;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Persistence;
import javax.persistence.spi.PersistenceUnitTransactionType;

public class MasterEntityManagerCreator {

	public MasterEntityManagerCreator() {
	}
	
	static final MasterEntityManagerFactory create() {
		Map<String, String> properties = new HashMap<>();
		properties.put(TRANSACTION_TYPE, PersistenceUnitTransactionType.RESOURCE_LOCAL.name());
		properties.put(JDBC_DRIVER, "org.postgresql.Driver");
		properties.put(JDBC_URL, "jdbc:postgresql://serverdb:5432/iot");
		properties.put(JDBC_USER, "postgres");
		properties.put(JDBC_PASSWORD, "postgres");
		properties.put(LOGGING_LEVEL, "FINE");
		properties.put(LOGGING_TIMESTAMP, "false");
		properties.put(LOGGING_THREAD, "false");
		properties.put(LOGGING_SESSION, "false");
		String unitName = "DEFAULT";
		return new MasterEntityManagerFactory(Persistence.createEntityManagerFactory(unitName, properties));
	}

}
