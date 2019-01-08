package com.master.core.persistence.filter;

import javax.persistence.Query;

public interface Filter {

	void apply(Query query);

	void buildFilter(StringBuilder sb);

}
