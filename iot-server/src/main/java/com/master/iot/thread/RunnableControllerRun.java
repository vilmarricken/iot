package com.master.iot.thread;

import java.text.DateFormat;
import java.util.Date;

import com.master.iot.IOTController;

public class RunnableControllerRun implements Runnable {

	private static final int delay = 60000 * 5;
	private final int component;

	private final IOTController controller;

	public RunnableControllerRun(final IOTController controller, final int component) {
		this.controller = controller;
		this.component = component;
	}

	@Override
	public void run() {
		try {
			System.out.println("Execution started in: " + DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL).format(new Date(System.currentTimeMillis())));
			this.controller.on(this.component);
			System.out.println("Executing ends on: " + DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL).format(new Date(System.currentTimeMillis() + RunnableControllerRun.delay)));
			Thread.sleep(RunnableControllerRun.delay);
			this.controller.off((byte) this.component);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

}
