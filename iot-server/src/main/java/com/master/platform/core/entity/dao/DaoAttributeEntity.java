package com.master.platform.core.entity.dao;

public interface DaoAttributeEntity {

	void addKey(DaoValueItem value);

	void addValue(DaoValueItem value);

	DaoValueItem buildBoolean(String attribute, boolean value);

	DaoValueItem buildBoolean(String attribute, Boolean value);

	DaoValueItem buildDouble(String attribute, double value);

	DaoValueItem buildDouble(String attribute, Double value);

	DaoValueItem buildInt(String attribute, int value);

	DaoValueItem buildInt(String attribute, Integer value);

	DaoValueItem buildString(String attribute, String value);

	boolean hasChanges();

}