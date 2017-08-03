package com.master.platform.core.resource;

import java.sql.Connection;

import com.master.platform.core.entity.manager.MasterEntityManager;

public abstract class ResourceBase implements Resource {

	private MasterEntityManager entityManager;

	private Connection connection;

	public ResourceBase() {
	}

	public void setEntityManager(MasterEntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public MasterEntityManager getEntityManager() {
		return this.entityManager;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Connection getConnection() {
		return this.connection;
	}

}
