package com.master.core.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;
import org.hibernate.query.Query;

import com.master.core.persistence.entity.Entity;
import com.master.core.persistence.filter.Filter;
import com.master.core.persistence.filter.FilterPage;
import com.master.core.persistence.filter.Order;

public class Persistence {

	private static final Logger LOG = Logger.getLogger(Persistence.class);

	private final PersistenceManager manager;

	private final Session session;

	private final PersistenceSessionController sessionController;

	private Transaction transaction;

	Persistence(final PersistenceManager manager, final PersistenceSessionController session) {
		this.manager = manager;
		this.sessionController = session;
		this.session = session.getSession();
	}

	private void apllyValues(final Query<?> query, final Filter filter) {
		if (filter != null) {
			filter.apply(query);
		}
	}

	private String buildQuery(final Class<?> clazz, final Filter filter, final FilterPage page, final Order order) {
		final StringBuilder sb = new StringBuilder(256);
		sb.append("from ").append(clazz.getSimpleName());
		if (filter != null) {
			sb.append(" where ");
			filter.buildFilter(sb);
		}
		return sb.toString();
	}

	public void clear() throws PersistenceException {
		this.session.clear();
	}

	public void close() throws PersistenceException {
		if (this.transaction != null) {
			Persistence.LOG.error("Transaction is open", new Exception());
			this.transaction.rollback();
			this.transaction = null;
		}
		this.manager.closeSession(this.sessionController);
		this.session.close();
	}

	public void commit() throws PersistenceException {
		if (this.transaction == null) {
			Persistence.LOG.error("There is no an open transaction", new Exception());
		} else {
			this.transaction.commit();
			this.transaction = null;
		}
	}

	public void delete(final Entity entity) throws PersistenceException {
		this.session.delete(entity);
	}

	public void execute(final Update u) throws PersistenceException {
		final PersistenceException[] ex = new PersistenceException[0];
		this.session.doWork(new Work() {
			@Override
			public void execute(final Connection connection) throws SQLException {
				try {
					u.executeUpdate(connection);
				} catch (final PersistenceException e) {
					ex[0] = e;
				}
			}
		});
		if (ex[0] != null) {
			throw ex[0];
		}
	}

	public <E> E find(final Class<E> clazz, final UUID id) throws PersistenceException {
		return this.session.byId(clazz).getReference(id);
	}

	public <T> T get(final Class<T> clazz) throws PersistenceException {
		return this.get(clazz, null);
	}

	public <T> T get(final Class<T> clazz, final Filter filter) throws PersistenceException {
		final List<T> result = this.list(clazz, filter, null, null);
		if (result.size() > 1) {
			throw new PersistenceException("Quantidade de registros, " + result.size() + ", não esperado para " + clazz.getSimpleName() + " : " + filter);
		}
		if (result.size() == 0) {
			return null;
		}
		return result.get(0);
	}

	public <T> List<T> list(final Class<T> clazz) throws PersistenceException {
		final Query<T> query = this.session.createQuery("from " + clazz.getSimpleName(), clazz);
		final List<T> list = query.getResultList();
		return list;
	}

	public <T> List<T> list(final Class<T> clazz, final Filter filter) throws PersistenceException {
		return this.list(clazz, filter, null, null);
	}

	public <T> List<T> list(final Class<T> clazz, final Filter filter, final FilterPage page, final Order order) throws PersistenceException {
		final Query<T> query = this.session.createQuery(this.buildQuery(clazz, filter, page, order), clazz);
		this.apllyValues(query, filter);
		return query.getResultList();
	}

	public <T> List<T> list(final Class<T> clazz, final FilterPage page) throws PersistenceException {
		final Query<T> query = this.session.createQuery("from " + clazz.getSimpleName(), clazz);
		query.setFirstResult(page.getStart());
		query.setFirstResult(page.getSize());
		final List<T> list = query.getResultList();
		return list;
	}

	public void openTransaction() throws PersistenceException {
		if (this.transaction != null) {
			Persistence.LOG.error("Transaction is open", new Exception());
			this.transaction.rollback();
			this.transaction = null;
		}
		this.transaction = this.session.beginTransaction();
	}

	public void persist(final Object object) throws PersistenceException {
		this.session.persist(object);
	}

	public void persist(final String entityName, final Object object) throws PersistenceException {
		this.session.persist(entityName, object);
	}

	public void rollback() throws PersistenceException {
		if (this.transaction == null) {
			Persistence.LOG.error("There is no an open transaction", new Exception());
		} else {
			this.transaction.rollback();
			this.transaction = null;
		}
	}

	public <T> UUID save(final T t) throws PersistenceException {
		return (UUID) this.session.save(t);
	}

}
