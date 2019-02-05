package com.master.core.persistence.dao;

import java.util.HashMap;
import java.util.Map;

import com.master.core.exception.MasterException;
import com.master.core.persistence.entity.Entity;

public class DaoFactory {

	private static final DaoFactory INSTANCE = new DaoFactory();

	public static DaoFactory getInstance() {
		return DaoFactory.INSTANCE;
	}

	private final Map<Class<?>, Dao> daos = new HashMap<>();

	private final Map<Class<? extends DaoEntity<? extends Entity>>, DaoEntity<? extends Entity>> daoEntities = new HashMap<>();

	private final <D extends DaoEntity<? extends Entity>, E extends Entity> D createDao(Class<D> clazz, Class<E> entity) {
		
		return null;
	}

	private <D extends Dao> D createDao(final Class<D> entity) throws MasterException {
		final String daoClassName = entity.getName() + "Dao";
		try {
			@SuppressWarnings("unchecked")
			final Class<D> daoClass = (Class<D>) Class.forName(daoClassName);
			final D dao = daoClass.newInstance();
			return dao;
		} catch (final ClassNotFoundException e) {
			throw new MasterException("Classe dao '" + daoClassName + "' não encontrada", e);
		} catch (final InstantiationException e) {
			throw new MasterException("Não foi possível instanciar a classe '" + daoClassName + "'", e);
		} catch (final IllegalAccessException e) {
			throw new MasterException("Erro ao instanciar a classe '" + daoClassName + "'", e);
		}
	}

	public final <D extends DaoEntity<? extends Entity>, E extends Entity> D getDao(Class<D> clazz, final Class<E> entity) throws MasterException {
		@SuppressWarnings("unchecked")
		D d = (D) daoEntities.get(clazz);
		if (d == null) {
			d = this.createDao(clazz, entity);
			this.daoEntities.put(clazz, d);
		}
		return d;
	}


	public final <D extends Dao> D getDao(Class<D> clazz) throws MasterException {
		@SuppressWarnings("unchecked")
		D d = (D) this.daos.get(clazz);
		if (d == null) {
			d = this.createDao(clazz);
			this.daos.put(clazz, d);
		}
		return d;
	}

}
