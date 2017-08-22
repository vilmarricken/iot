package com.master.platform.core.entity.dao;

public interface DaoEntity {

	void setInt(String attribute, int value);

	void setInt(String attribute, Integer value);

	void setString(String attribute, String value);

	void setDouble(String attribute, double value);

	void setDouble(String attribute, Double value);

	void setBoolean(String attribute, boolean value);

	void setBoolean(String attribute, Boolean value);

	boolean hasChanges();

}