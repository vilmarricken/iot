package com.master.core.persistence.filter;

import javax.persistence.Query;

public enum FilterOperation {

	/** equal to/ not equal to */
	EQ("="), NOT_EQ("!="),

	/** greater than/ greater than or equal to */
	GT(">"), GT_EQ(">="),

	/** less than/ less than or equal to */
	LT("<"), LT_EQ("<="),

	/** in/ not in */
	// IN("in"), NIN("not in"),

	/** is null/ is not null */
	NULL("IS NULL") {

		@Override
		public int apply(Query query, int position, Object value) {
			return position;
		}

		@Override
		public int buildFilter(StringBuilder sb, int position) {
			return position;
		}

	},

	NOT_NULL("IS NOT NULL") {

		@Override
		public int apply(Query query, int position, Object value) {
			return position;
		}

		@Override
		public int buildFilter(StringBuilder sb, int position) {
			return position;
		}

	},

	/** like/ ilike */
	LIKE("LIKE"), ILIKE("ILIKE");

	private String operator;

	private FilterOperation(final String operator) {
		this.operator = operator;
	}

	public String getOperator() {
		return this.operator;
	}

	public int apply(Query query, int position, Object value) {
		query.setParameter(position, value);
		return position + 1;
	}

	public int buildFilter(StringBuilder sb, int position) {
		sb.append(this.operator).append(" ?").append(position);
		return position + 1;
	}

}
