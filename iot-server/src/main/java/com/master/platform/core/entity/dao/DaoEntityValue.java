package com.master.platform.core.entity.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

public class DaoEntityValue implements DaoEntity {

	protected enum DaoValueType {

		STRING() {
			@Override
			public void setValue(PreparedStatement stmt, int position, Object value) throws SQLException {
				if (value != null) {
					stmt.setString(position, (String) value);
				} else {
					stmt.setNull(position, Types.VARCHAR);
				}
			}
		},
		INT() {
			@Override
			public void setValue(PreparedStatement stmt, int position, Object value) throws SQLException {
				if (value != null) {
					stmt.setInt(position, ((Integer) value).intValue());
				} else {
					stmt.setNull(position, Types.INTEGER);
				}
			}
		},
		DOUBLE() {
			@Override
			public void setValue(PreparedStatement stmt, int position, Object value) throws SQLException {
				if (value != null) {
					stmt.setDouble(position, ((Double) value).doubleValue());
				} else {
					stmt.setNull(position, Types.DOUBLE);
				}
			}
		},
		BOOLEAN() {
			@Override
			public void setValue(PreparedStatement stmt, int position, Object value) throws SQLException {
				if (value != null) {
					stmt.setInt(position, ((Boolean) value).booleanValue() ? 1 : 0);
				} else {
					stmt.setNull(position, Types.INTEGER);
				}
			}
		};

		private DaoValueType() {
		}

		public abstract void setValue(PreparedStatement stmt, int position, Object value) throws SQLException;

	}

	private Map<String, DaoValueItem> values = new HashMap<>();

	public DaoEntityValue() {
	}

	@Override
	public void setInt(String attribute, int value) {
		this.setInt(attribute, Integer.valueOf(value));
	}

	@Override
	public void setInt(String attribute, Integer value) {
		this.values.put(attribute, new DaoValueItem(attribute, value, DaoValueType.INT));
	}

	@Override
	public void setString(String attribute, String value) {
		this.values.put(attribute, new DaoValueItem(attribute, value, DaoValueType.STRING));
	}

	@Override
	public void setDouble(String attribute, double value) {
		this.setDouble(attribute, Double.valueOf(value));
	}

	@Override
	public void setDouble(String attribute, Double value) {
		this.values.put(attribute, new DaoValueItem(attribute, value, DaoValueType.DOUBLE));
	}

	@Override
	public void setBoolean(String attribute, boolean value) {
		this.setBoolean(attribute, Boolean.valueOf(value));
	}

	@Override
	public void setBoolean(String attribute, Boolean value) {
		this.values.put(attribute, new DaoValueItem(attribute, value, DaoValueType.BOOLEAN));
	}

	@Override
	public boolean hasChanges() {
		return this.values.size() != 0;
	}

}
