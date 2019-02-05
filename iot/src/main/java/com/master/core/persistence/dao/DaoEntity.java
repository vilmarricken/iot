package com.master.core.persistence.dao;

import java.util.List;
import java.util.UUID;

import com.master.core.persistence.PersistenceException;
import com.master.core.persistence.entity.Entity;
import com.master.core.persistence.filter.Filter;

public interface DaoEntity<E extends Entity> {
	
	void delete(final E entity) throws PersistenceException;

	E find(final UUID id) throws PersistenceException;

	UUID save(final E entity) throws PersistenceException;

	E unique(final Filter filter) throws PersistenceException;

	List<E> all() throws PersistenceException;

}
