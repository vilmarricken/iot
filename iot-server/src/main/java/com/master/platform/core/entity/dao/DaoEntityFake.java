package com.master.platform.core.entity.dao;

public class DaoEntityFake implements DaoEntity {

	public static final DaoEntity INSTANCE = new DaoEntityFake();

	public DaoEntityFake() {
	}

	@Override
	public void addKey(final DaoValueItem value) {
	}

	@Override
	public void addValue(final DaoValueItem value) {
	}

	@Override
	public DaoValueItem buildBoolean(final String attribute, final boolean value) {
		return null;
	}

	@Override
	public DaoValueItem buildBoolean(final String attribute, final Boolean value) {
		return null;
	}

	@Override
	public DaoValueItem buildDouble(final String attribute, final double value) {
		return null;
	}

	@Override
	public DaoValueItem buildDouble(final String attribute, final Double value) {
		return null;
	}

	@Override
	public DaoValueItem buildInt(final String attribute, final int value) {
		return null;
	}

	@Override
	public DaoValueItem buildInt(final String attribute, final Integer value) {
		return null;
	}

	@Override
	public DaoValueItem buildString(final String attribute, final String value) {
		return null;
	}

	@Override
	public boolean hasChanges() {
		return false;
	}

}
