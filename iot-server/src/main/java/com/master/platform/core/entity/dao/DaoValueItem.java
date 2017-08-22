package com.master.platform.core.entity.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.master.platform.core.entity.dao.DaoEntityValue.DaoValueType;

public class DaoValueItem {

	private String attribute;

	private Object value;

	private DaoValueType type;

	public DaoValueItem(String attribute, Object value, DaoValueType type) {
		super();
		this.attribute = attribute;
		this.value = value;
		this.type = type;
	}

	public void setValue(PreparedStatement stmt, int position) throws SQLException {
		this.type.setValue(stmt, position, this.value);
	}

	public String getAttribute() {
		return this.attribute;
	}

}
