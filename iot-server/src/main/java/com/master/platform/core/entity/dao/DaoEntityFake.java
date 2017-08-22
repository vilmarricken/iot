package com.master.platform.core.entity.dao;

public class DaoEntityFake implements DaoEntity {

	public static final DaoEntity INSTANCE = new DaoEntityFake();

	public DaoEntityFake() {
	}

	@Override
	public void setInt(String attribute, int value) {
	}

	@Override
	public void setInt(String attribute, Integer value) {
	}

	@Override
	public void setString(String attribute, String value) {
	}

	@Override
	public void setDouble(String attribute, double value) {
	}

	@Override
	public void setDouble(String attribute, Double value) {
	}

	@Override
	public void setBoolean(String attribute, boolean value) {
	}

	@Override
	public void setBoolean(String attribute, Boolean value) {
	}

	@Override
	public boolean hasChanges() {
		return false;
	}

}
