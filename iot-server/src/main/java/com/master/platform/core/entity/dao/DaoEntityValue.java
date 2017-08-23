package com.master.platform.core.entity.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

public class DaoEntityValue implements DaoEntity {

	protected enum DaoValueType {

		BOOLEAN() {
			@Override
			public void setValue(final PreparedStatement stmt, final int position, final Object value) throws SQLException {
				if (value != null) {
					stmt.setInt(position, ((Boolean) value).booleanValue() ? 1 : 0);
				} else {
					stmt.setNull(position, Types.INTEGER);
				}
			}
		},
		DOUBLE() {
			@Override
			public void setValue(final PreparedStatement stmt, final int position, final Object value) throws SQLException {
				if (value != null) {
					stmt.setDouble(position, ((Double) value).doubleValue());
				} else {
					stmt.setNull(position, Types.DOUBLE);
				}
			}
		},
		INT() {
			@Override
			public void setValue(final PreparedStatement stmt, final int position, final Object value) throws SQLException {
				if (value != null) {
					stmt.setInt(position, ((Integer) value).intValue());
				} else {
					stmt.setNull(position, Types.INTEGER);
				}
			}
		},
		STRING() {
			@Override
			public void setValue(final PreparedStatement stmt, final int position, final Object value) throws SQLException {
				if (value != null) {
					stmt.setString(position, (String) value);
				} else {
					stmt.setNull(position, Types.VARCHAR);
				}
			}
		};

		private DaoValueType() {
		}

		public abstract void setValue(PreparedStatement stmt, int position, Object value) throws SQLException;

	}

	private final Map<String, DaoValueItem> key = new HashMap<>();

	private final Map<String, DaoValueItem> values = new HashMap<>();

	public DaoEntityValue() {
	}

	@Override
	public void addKey(final DaoValueItem value) {
		this.key.put(value.getAttribute(), value);
	}

	@Override
	public void addValue(final DaoValueItem value) {
		this.values.put(value.getAttribute(), value);
	}

	@Override
	public DaoValueItem buildBoolean(final String attribute, final boolean value) {
		return this.buildBoolean(attribute, Boolean.valueOf(value));
	}

	@Override
	public DaoValueItem buildBoolean(final String attribute, final Boolean value) {
		return new DaoValueItem(attribute, value, DaoValueType.BOOLEAN);
	}

	@Override
	public DaoValueItem buildDouble(final String attribute, final double value) {
		return this.buildDouble(attribute, Double.valueOf(value));
	}

	@Override
	public DaoValueItem buildDouble(final String attribute, final Double value) {
		return this.values.put(attribute, new DaoValueItem(attribute, value, DaoValueType.DOUBLE));
	}

	@Override
	public DaoValueItem buildInt(final String attribute, final int value) {
		return this.buildInt(attribute, Integer.valueOf(value));
	}

	@Override
	public DaoValueItem buildInt(final String attribute, final Integer value) {
		return new DaoValueItem(attribute, value, DaoValueType.INT);
	}

	@Override
	public DaoValueItem buildString(final String attribute, final String value) {
		return new DaoValueItem(attribute, value, DaoValueType.STRING);
	}

	@Override
	public boolean hasChanges() {
		return this.values.size() != 0;
	}

}
