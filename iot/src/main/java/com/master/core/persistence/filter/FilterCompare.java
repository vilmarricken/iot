package com.master.core.persistence.filter;

import javax.persistence.Query;

public class FilterCompare extends FilterAbstract {

	private final String attribute;

	private final Object value;

	private final FilterOperation operation;

	public FilterCompare(String attribute, Object value, FilterOperation operation) {
		this.attribute = attribute;
		this.value = value;
		this.operation = operation;
	}

	@Override
	int apply(Query query, int position) {
		return this.operation.apply(query, position, this.value);
	}

	@Override
	public void apply(Query query) {
		this.apply(query, 1);
	}

	@Override
	public void buildFilter(StringBuilder sb) {
		this.buildFilter(sb, 1);
	}

	@Override
	int buildFilter(StringBuilder sb, int position) {
		sb.append(this.attribute).append(" ");
		return this.operation.buildFilter(sb, position);
	}

}
