package com.master.core.resource;

import org.apache.log4j.Logger;

public class Resource {

	private static final Logger LOG = Logger.getLogger(Resource.class);

	private static final ThreadLocal<Resource> RESOURCE = new ThreadLocal<>();

	static void finish() {
		final Resource resource = Resource.RESOURCE.get();
		if (resource == null) {
			Resource.LOG.error("Resource was not initialized");
			Resource.RESOURCE.remove();
		} else {
			Resource.RESOURCE.remove();
		}
	}

	static void init() {
		final Resource resource = Resource.RESOURCE.get();
		if (resource != null) {
			Resource.LOG.error("Resource was not finalized: " + resource.thread);
			Resource.RESOURCE.remove();
		}
		Resource.RESOURCE.set(new Resource(Thread.currentThread()));
	}

	private final Thread thread;

	private Resource(final Thread thread) {
		this.thread = thread;
	}

}
