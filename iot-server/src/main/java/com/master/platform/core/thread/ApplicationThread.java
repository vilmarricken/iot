package com.master.platform.core.thread;

public class ApplicationThread {

	private ApplicationThreadContext context;

	private ApplicationRunnable runnable;

	public ApplicationThread(ApplicationThreadContext context, ApplicationRunnable runnable) {
		this.context = context;
		this.runnable = runnable;
	}

	public void start() {
		new Thread() {
			@Override
			public void run() {
				ApplicationThread.this.run();
			}
		}.start();
	}

	public void run() {
		try {
			this.context.init();
			this.runnable.run();
		} finally {
			this.context.finish();
		}
	}

}
