package com.master.core.persistence.filter;

import javax.persistence.Query;

public abstract class FilterAbstract implements Filter {

	abstract int apply(Query query, int position);

	abstract int buildFilter(StringBuilder sb, int position);

}
