package com.master.platform.core.thread;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.master.platform.core.entity.manager.MasterEntityManagerController;
import com.master.platform.core.entity.manager.MasterEntityManagerFactory;
import com.master.platform.core.resource.Resource;
import com.master.platform.core.resource.ResourceManager;

public abstract class ApplicationThreadContextAbstract implements ApplicationThreadContext {

	private EntityManager entityManager;
	private EntityTransaction transaction;

	private void closeEntityManager() {
		if (this.entityManager != null) {
			this.entityManager.close();
		}
	}

	private void commit() {
		if (this.transaction != null) {
			this.transaction.commit();
		}
	}

	@Override
	public void error() {
		this.rollback();
		this.closeEntityManager();
	}

	@Override
	public void finish() {
		this.commit();
		this.closeEntityManager();
	}

	@Override
	public void init() {
		this.startEntityManager();
		this.startTransaction();
	}

	protected abstract boolean isActiveTransaction();

	private void rollback() {
		if (this.transaction != null) {
			this.transaction.rollback();
		}
	}

	void setResource(final Resource resource) {
		ResourceManager.get().start(resource);
	}

	private void startEntityManager() {
		final MasterEntityManagerController entityManagerController = MasterEntityManagerController.get();
		final MasterEntityManagerFactory entityManagerFactory = entityManagerController.getEntityManagerFactory();
		this.entityManager = entityManagerFactory.createEntityManager();
	}

	private void startTransaction() {
		if (this.entityManager != null) {
			this.transaction = this.entityManager.getTransaction();
			this.transaction.begin();
		}
	}

}
