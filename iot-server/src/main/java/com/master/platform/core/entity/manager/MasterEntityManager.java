package com.master.platform.core.entity.manager;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;

public class MasterEntityManager implements javax.persistence.EntityManager {

	private EntityManager entityManager;

	private MasterEntityManagerFactory masterEntityManagerFactory;

	public MasterEntityManager(EntityManager entityManager, MasterEntityManagerFactory masterEntityManagerFactory) {
		this.entityManager = entityManager;
		this.masterEntityManagerFactory = masterEntityManagerFactory;
	}

	@Override
	public void clear() {
		this.entityManager.clear();
	}

	@Override
	public void close() {
		this.entityManager.close();
	}

	@Override
	public boolean contains(Object arg0) {
		return this.entityManager.contains(arg0);
	}

	@Override
	public <T> EntityGraph<T> createEntityGraph(Class<T> arg0) {
		return this.entityManager.createEntityGraph(arg0);
	}

	@Override
	public EntityGraph<?> createEntityGraph(String arg0) {
		return this.entityManager.createEntityGraph(arg0);
	}

	@Override
	public Query createNamedQuery(String arg0) {
		return this.entityManager.createNamedQuery(arg0);
	}

	@Override
	public <T> TypedQuery<T> createNamedQuery(String arg0, Class<T> arg1) {
		return this.createNamedQuery(arg0, arg1);
	}

	@Override
	public StoredProcedureQuery createNamedStoredProcedureQuery(String arg0) {
		return this.entityManager.createNamedStoredProcedureQuery(arg0);
	}

	@Override
	public Query createNativeQuery(String arg0) {
		return this.entityManager.createNamedQuery(arg0);
	}

	@Override
	public Query createNativeQuery(String arg0, @SuppressWarnings("rawtypes") Class arg1) {
		return this.entityManager.createNativeQuery(arg0, arg1);
	}

	@Override
	public Query createNativeQuery(String arg0, String arg1) {
		return this.entityManager.createNativeQuery(arg0, arg1);
	}

	@Override
	public Query createQuery(String arg0) {
		return this.entityManager.createQuery(arg0);
	}

	@Override
	public <T> TypedQuery<T> createQuery(CriteriaQuery<T> arg0) {
		return this.createQuery(arg0);
	}

	@Override
	public Query createQuery(@SuppressWarnings("rawtypes") CriteriaUpdate arg0) {
		return this.entityManager.createQuery(arg0);
	}

	@Override
	public Query createQuery(@SuppressWarnings("rawtypes") CriteriaDelete arg0) {
		return this.createQuery(arg0);
	}

	@Override
	public <T> TypedQuery<T> createQuery(String arg0, Class<T> arg1) {
		return this.createQuery(arg0, arg1);
	}

	@Override
	public StoredProcedureQuery createStoredProcedureQuery(String arg0) {
		return this.createStoredProcedureQuery(arg0);
	}

	@Override
	public StoredProcedureQuery createStoredProcedureQuery(String arg0, @SuppressWarnings("rawtypes") Class... arg1) {
		return this.entityManager.createStoredProcedureQuery(arg0, arg1);
	}

	@Override
	public StoredProcedureQuery createStoredProcedureQuery(String arg0, String... arg1) {
		return this.entityManager.createStoredProcedureQuery(arg0, arg1);
	}

	@Override
	public void detach(Object arg0) {
		this.entityManager.detach(arg0);
	}

	@Override
	public <T> T find(Class<T> arg0, Object arg1) {
		return this.entityManager.find(arg0, arg1);
	}

	@Override
	public <T> T find(Class<T> arg0, Object arg1, Map<String, Object> arg2) {
		return this.entityManager.find(arg0, arg1, arg2);
	}

	@Override
	public <T> T find(Class<T> arg0, Object arg1, LockModeType arg2) {
		return this.entityManager.find(arg0, arg1, arg2);
	}

	@Override
	public <T> T find(Class<T> arg0, Object arg1, LockModeType arg2, Map<String, Object> arg3) {
		return this.entityManager.find(arg0, arg1, arg2, arg3);
	}

	@Override
	public void flush() {
		this.entityManager.flush();
	}

	@Override
	public CriteriaBuilder getCriteriaBuilder() {
		return this.entityManager.getCriteriaBuilder();
	}

	@Override
	public Object getDelegate() {
		return this.entityManager.getDelegate();
	}

	@Override
	public EntityGraph<?> getEntityGraph(String arg0) {
		return this.entityManager.getEntityGraph(arg0);
	}

	@Override
	public <T> List<EntityGraph<? super T>> getEntityGraphs(Class<T> arg0) {
		return this.entityManager.getEntityGraphs(arg0);
	}

	@Override
	public EntityManagerFactory getEntityManagerFactory() {
		return this.masterEntityManagerFactory;
	}

	@Override
	public FlushModeType getFlushMode() {
		return this.entityManager.getFlushMode();
	}

	@Override
	public LockModeType getLockMode(Object arg0) {
		return this.entityManager.getLockMode(arg0);
	}

	@Override
	public Metamodel getMetamodel() {
		return this.entityManager.getMetamodel();
	}

	@Override
	public Map<String, Object> getProperties() {
		return this.entityManager.getProperties();
	}

	@Override
	public <T> T getReference(Class<T> arg0, Object arg1) {
		return this.entityManager.getReference(arg0, arg1);
	}

	@Override
	public EntityTransaction getTransaction() {
		return this.entityManager.getTransaction();
	}

	@Override
	public boolean isJoinedToTransaction() {
		return this.entityManager.isJoinedToTransaction();
	}

	@Override
	public boolean isOpen() {
		return this.entityManager.isOpen();
	}

	@Override
	public void joinTransaction() {
		this.entityManager.joinTransaction();
	}

	@Override
	public void lock(Object arg0, LockModeType arg1) {
		this.entityManager.lock(arg0, arg1);
	}

	@Override
	public void lock(Object arg0, LockModeType arg1, Map<String, Object> arg2) {
		this.entityManager.lock(arg0, arg1, arg2);
	}

	@Override
	public <T> T merge(T arg0) {
		return this.entityManager.merge(arg0);
	}

	@Override
	public void persist(Object arg0) {
		this.entityManager.persist(arg0);
	}

	@Override
	public void refresh(Object arg0) {
		this.entityManager.refresh(arg0);
	}

	@Override
	public void refresh(Object arg0, Map<String, Object> arg1) {
		this.entityManager.refresh(arg0, arg1);
	}

	@Override
	public void refresh(Object arg0, LockModeType arg1) {
		this.entityManager.refresh(arg0, arg1);
	}

	@Override
	public void refresh(Object arg0, LockModeType arg1, Map<String, Object> arg2) {
		this.entityManager.refresh(arg0, arg1, arg2);
	}

	@Override
	public void remove(Object arg0) {
		this.entityManager.remove(arg0);
	}

	@Override
	public void setFlushMode(FlushModeType arg0) {
		this.entityManager.setFlushMode(arg0);
	}

	@Override
	public void setProperty(String arg0, Object arg1) {
		this.entityManager.setProperty(arg0, arg1);
	}

	@Override
	public <T> T unwrap(Class<T> arg0) {
		return this.entityManager.unwrap(arg0);
	}

}
