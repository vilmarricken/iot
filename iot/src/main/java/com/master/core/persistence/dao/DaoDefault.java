package com.master.core.persistence.dao;

import java.util.UUID;

import com.master.core.persistence.Persistence;
import com.master.core.persistence.PersistenceException;
import com.master.core.persistence.PersistenceManager;
import com.master.core.persistence.entity.Entity;
import com.master.core.persistence.filter.Filter;

public class DaoDefault<E extends Entity> {

	private final Class<E> clazz;

	public DaoDefault(final Class<E> clazz) {
		this.clazz = clazz;
	}

	public void delete(final E entity) throws PersistenceException {
		final Persistence persistence = PersistenceManager.getPersistence();
		persistence.delete(entity);
	}

	public E find(final UUID id) throws PersistenceException {
		final Persistence persistence = PersistenceManager.getPersistence();
		return persistence.find(this.clazz, id);
	}

	public UUID save(final E entity) throws PersistenceException {
		final Persistence persistence = PersistenceManager.getPersistence();
		return persistence.save(entity);
	}

	public E unique(final Filter filter) throws PersistenceException {
		final Persistence persistence = PersistenceManager.getPersistence();
		return persistence.get(this.clazz, filter);
	}
}
