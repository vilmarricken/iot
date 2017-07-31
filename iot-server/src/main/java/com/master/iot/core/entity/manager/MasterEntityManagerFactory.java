package com.master.iot.core.entity.manager;

import java.util.Map;

import javax.persistence.Cache;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnitUtil;
import javax.persistence.Query;
import javax.persistence.SynchronizationType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.metamodel.Metamodel;

public class MasterEntityManagerFactory implements EntityManagerFactory {

	private EntityManagerFactory entityManagerFactory;

	public MasterEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	@Override
	public <T> void addNamedEntityGraph(String arg0, EntityGraph<T> arg1) {
		this.entityManagerFactory.addNamedEntityGraph(arg0, arg1);
	}

	@Override
	public void addNamedQuery(String arg0, Query arg1) {
		this.entityManagerFactory.addNamedQuery(arg0, arg1);
	}

	@Override
	public void close() {
		this.entityManagerFactory.close();
	}

	@Override
	public EntityManager createEntityManager() {
		return new MasterEntityManager(this.entityManagerFactory.createEntityManager(), this);
	}

	@Override
	public EntityManager createEntityManager(@SuppressWarnings("rawtypes") Map arg0) {
		return new MasterEntityManager(this.entityManagerFactory.createEntityManager(arg0), this);
	}

	@Override
	public EntityManager createEntityManager(SynchronizationType arg0) {
		return new MasterEntityManager(this.entityManagerFactory.createEntityManager(arg0), this);
	}

	@Override
	public EntityManager createEntityManager(SynchronizationType arg0, @SuppressWarnings("rawtypes") Map arg1) {
		return new MasterEntityManager(this.entityManagerFactory.createEntityManager(arg0, arg1), this);
	}

	@Override
	public Cache getCache() {
		return this.entityManagerFactory.getCache();
	}

	@Override
	public CriteriaBuilder getCriteriaBuilder() {
		return this.entityManagerFactory.getCriteriaBuilder();
	}

	@Override
	public Metamodel getMetamodel() {
		return this.entityManagerFactory.getMetamodel();
	}

	@Override
	public PersistenceUnitUtil getPersistenceUnitUtil() {
		return this.entityManagerFactory.getPersistenceUnitUtil();
	}

	@Override
	public Map<String, Object> getProperties() {
		return this.entityManagerFactory.getProperties();
	}

	@Override
	public boolean isOpen() {
		return this.entityManagerFactory.isOpen();
	}

	@Override
	public <T> T unwrap(Class<T> arg0) {
		return this.entityManagerFactory.unwrap(arg0);
	}

}
