package com.master.core.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Persistence {

	private static final Logger LOG = Logger.getLogger(Persistence.class);

	private final PersistenceManager manager;

	private final Session session;

	private final PersistenceSessionController sessionController;

	private Transaction transaction;

	public Persistence(final PersistenceManager manager, final PersistenceSessionController session) {
		this.manager = manager;
		this.sessionController = session;
		this.session = session.getSession();
	}

	public void clear() {
		this.session.clear();
	}

	public void close() {
		if (this.transaction != null) {
			Persistence.LOG.error("Transaction is open", new Exception());
			this.transaction.rollback();
			this.transaction = null;
		}
		this.manager.closeSession(this.sessionController);
		this.session.close();
	}

	public void commit() {
		if (this.transaction == null) {
			Persistence.LOG.error("There is no an open transaction", new Exception());
		} else {
			this.transaction.commit();
			this.transaction = null;
		}
	}

	public <T> List<T> list(final Class<T> clazz) {
		final CriteriaBuilder builder = this.session.getCriteriaBuilder();
		final CriteriaQuery<T> criteria = builder.createQuery(clazz);
		final Root<T> root = criteria.from(clazz);
		criteria.select(root);
		final List<T> list = this.session.createQuery(criteria).getResultList();
		return list;
	}

	public void openTransaction() {
		if (this.transaction != null) {
			Persistence.LOG.error("Transaction is open", new Exception());
			this.transaction.rollback();
			this.transaction = null;
		}
		this.transaction = this.session.beginTransaction();
	}

	public void persist(final Object object) {
		this.session.persist(object);
	}

	public void persist(final String entityName, final Object object) {
		this.session.persist(entityName, object);
	}

	public void rollback() {
		if (this.transaction == null) {
			Persistence.LOG.error("There is no an open transaction", new Exception());
		} else {
			this.transaction.rollback();
			this.transaction = null;
		}
	}

	public <T> Serializable save(final T t) {
		return this.session.save(t);
	}

}
