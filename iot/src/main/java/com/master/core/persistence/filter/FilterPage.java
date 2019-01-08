package com.master.core.persistence.filter;

public class FilterPage {

	private final int start;

	private final int size;

	public FilterPage(int start, int size) {
		this.start = start;
		this.size = size;
	}

	public int getSize() {
		return this.size;
	}

	public int getStart() {
		return this.start;
	}

}
