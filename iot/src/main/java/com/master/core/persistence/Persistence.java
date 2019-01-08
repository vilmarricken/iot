package com.master.core.persistence;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.master.core.persistence.filter.Filter;
import com.master.core.persistence.filter.FilterPage;
import com.master.core.persistence.filter.Order;

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

	public <T> List<T> list(final Class<T> clazz, Filter filter, FilterPage page, Order order) {
		final Query<T> query = this.session.createQuery(this.buildQuery(clazz, filter, page, order), clazz);
		this.apllyValues(query, filter);
		return query.getResultList();
	}

	public <T> List<T> list(final Class<T> clazz, Filter filter) {
		return this.list(clazz, filter, null, null);
	}

	private void apllyValues(Query<?> query, Filter filter) {
		if (filter != null) {
			filter.apply(query);
		}
	}

	private String buildQuery(Class<?> clazz, Filter filter, FilterPage page, Order order) {
		final StringBuilder sb = new StringBuilder(256);
		sb.append("from ").append(clazz.getSimpleName());
		if (filter != null) {
			sb.append(" where ");
			filter.buildFilter(sb);
		}
		return sb.toString();
	}

	public <T> List<T> list(final Class<T> clazz, FilterPage page) {
		final Query<T> query = this.session.createQuery("from " + clazz.getSimpleName(), clazz);
		query.setFirstResult(page.getStart());
		query.setFirstResult(page.getSize());
		final List<T> list = query.getResultList();
		return list;
	}

	public <T> List<T> list(final Class<T> clazz) {
		final Query<T> query = this.session.createQuery("from " + clazz.getSimpleName(), clazz);
		final List<T> list = query.getResultList();
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
