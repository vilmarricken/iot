package com.master.platform.core.resource;

import java.sql.Connection;

import com.master.platform.core.entity.manager.MasterEntityManager;

public abstract class Resource {

	private MasterEntityManager entityManager;

	private Connection connection;

	private ResourceManager resourceManager;

	public Resource() {
	}

	void setResourceManager(ResourceManager resourceManager) {
		this.resourceManager = resourceManager;
	}

	public void setEntityManager(MasterEntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public MasterEntityManager getEntityManager() {
		return this.entityManager;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Connection getConnection() {
		return this.connection;
	}

}
