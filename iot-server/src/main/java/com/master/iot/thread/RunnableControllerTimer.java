package com.master.iot.thread;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.master.iot.IOTController;

public class RunnableControllerTimer implements Runnable {

	private static GregorianCalendar TIME = new GregorianCalendar();

	private final int component;

	private final IOTController controller;

	private boolean running = true;

	public RunnableControllerTimer(final IOTController controller, final int component) {
		this.controller = controller;
		this.component = component;
		RunnableControllerTimer.TIME.setTimeInMillis(System.currentTimeMillis());
		RunnableControllerTimer.TIME.set(Calendar.HOUR, 0);
		RunnableControllerTimer.TIME.set(Calendar.MINUTE, 0);
		RunnableControllerTimer.TIME.set(Calendar.SECOND, 0);
		RunnableControllerTimer.TIME.set(Calendar.MILLISECOND, 0);
		System.out.println("Init" + DateFormat.getTimeInstance(DateFormat.FULL).format(RunnableControllerTimer.TIME.getTime()));
		while (RunnableControllerTimer.TIME.getTimeInMillis() < System.currentTimeMillis()) {
			RunnableControllerTimer.TIME.add(Calendar.HOUR, 6);
			System.out.println("Next " + DateFormat.getTimeInstance(DateFormat.FULL).format(RunnableControllerTimer.TIME.getTime()));
		}
		new Thread(new RunnableControllerRun(this.controller, this.component)).start();
	}

	@Override
	public void run() {
		while (this.running) {
			System.out.println("Execute on " + DateFormat.getTimeInstance(DateFormat.FULL).format(RunnableControllerTimer.TIME.getTime()));
			try {
				Thread.sleep(RunnableControllerTimer.TIME.getTimeInMillis() - System.currentTimeMillis());
				if (this.running) {
					new Thread(new RunnableControllerRun(this.controller, this.component)).start();
				}
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
			RunnableControllerTimer.TIME.add(Calendar.HOUR, 6);
		}
		System.out.println("Thread finalizada");
	}

	public void setRunning(final boolean running) {
		this.running = running;
	}

}
