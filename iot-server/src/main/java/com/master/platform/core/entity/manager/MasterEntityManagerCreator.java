package com.master.platform.core.entity.manager;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;

import com.master.iot.entity.HouseComponent;
import com.master.iot.entity.HouseDevice;
import com.master.iot.entity.HouseController;
import com.master.platform.core.entity.MasterEntityObject;

public class MasterEntityManagerCreator {

	static final MasterEntityManagerFactory create() {
		StandardServiceRegistryBuilder config = new StandardServiceRegistryBuilder();
		config.configure("org/hibernate/example/hibernate.cfg.xml");
		config.applySetting(AvailableSettings.JPA_JDBC_DRIVER, "org.postgresql.Driver");
		config.applySetting(AvailableSettings.JPA_JDBC_URL, "jdbc:postgresql://serverdb:5432/iot");
		config.applySetting(AvailableSettings.JPA_JDBC_USER, "postgres");
		config.applySetting(AvailableSettings.JPA_JDBC_PASSWORD, "postgres");
		StandardServiceRegistry ssr = config.build();

		MetadataSources ms = new MetadataSources(ssr);
		ms.addAnnotatedClass(MasterEntityObject.class);
		ms.addAnnotatedClass(HouseComponent.class);
		ms.addAnnotatedClass(HouseDevice.class);
		ms.addAnnotatedClass(HouseDevice.class);
		ms.addAnnotatedClass(HouseController.class);
		SessionFactory sessionFactory = ms.buildMetadata().buildSessionFactory();

		return new MasterEntityManagerFactory(sessionFactory);
	}

	public MasterEntityManagerCreator() {
	}
}
