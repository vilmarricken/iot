package com.master.platform.core.resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ResourceManager {

	private static final ThreadLocal<Resource> RESOURCES = new ThreadLocal<>();

	private static final Logger log = LogManager.getLogger(ResourceManager.class);

	private static final ResourceManager INSTANCE = new ResourceManager();

	public ResourceManager() {
	}

	public void start(Resource resource) {
		Resource old = ResourceManager.RESOURCES.get();
		if (old != null) {
			Thread currentThread = Thread.currentThread();
			ResourceManager.log.warn("Recource is already active in thread " + currentThread.getName() + "(" + currentThread.getId() + ")" + ": " + old);
		}
		ResourceManager.RESOURCES.set(resource);
		if (ResourceManager.log.isTraceEnabled()) {

		}
	}

	public void finish() {
		Resource old = ResourceManager.RESOURCES.get();
		if (old == null) {
			Thread currentThread = Thread.currentThread();
			ResourceManager.log.warn("Recource is not active in thread " + currentThread.getName() + "(" + currentThread.getId() + ")");
		}
		ResourceManager.RESOURCES.remove();
	}

	public static ResourceManager get() {
		return ResourceManager.INSTANCE;
	}

}
