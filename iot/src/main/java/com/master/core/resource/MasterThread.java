package com.master.core.resource;

import org.jboss.logging.Logger;

import com.master.core.exception.MasterException;

public class MasterThread {

	private static final Logger LOGGER = Logger.getLogger(MasterThread.class);

	private final MasterContext context;

	private final MasterRunnable runnable;

	public MasterThread(final MasterRunnable runnable, final MasterContext context) {
		this.runnable = runnable;
		this.context = context;
	}

	public void start(final String name) {
		new Thread(name) {
			@Override
			public void run() {
				try {
					MasterThread.this.runnable.run();
				} catch (final MasterException e) {
					MasterThread.LOGGER.error(e.getMessage(), e);
				}
			}
		};
	}

}
