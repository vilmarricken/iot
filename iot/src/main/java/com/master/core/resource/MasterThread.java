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

	public void run() throws MasterException {
		try {
			MasterThread.this.context.start();
			MasterThread.this.runnable.run();
			MasterThread.this.context.finish();
		} catch (final Throwable e) {
			try {
				MasterThread.this.context.error();
			} catch (final Throwable e1) {
				MasterThread.LOGGER.error(e1.getMessage(), e1);
			} finally {
				MasterThread.LOGGER.error(e.getMessage(), e);
			}
		}
	}

	public void start(final String name) {
		new Thread(name) {
			@Override
			public void run() {
				try {
					MasterThread.this.run();
				} catch (final MasterException e) {
					MasterThread.LOGGER.error(e.getMessage(), e);
				}
			}
		}.start();
	}

}
