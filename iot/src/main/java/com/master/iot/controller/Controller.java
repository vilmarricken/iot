package com.master.iot.controller;

import java.text.DateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.master.core.exception.MasterException;
import com.master.core.persistence.Update;
import com.master.core.resource.MasterContextTransaction;
import com.master.core.resource.MasterThread;
import com.master.iot.action.ActionUpdate;
import com.master.iot.entity.Board;
import com.master.iot.entity.Component;
import com.master.iot.entity.History;
import com.master.iot.entity.Status;
import com.master.iot.entity.dao.HistoryInsertDao;
import com.master.iot.entity.dao.HistoryInsertExceptionDao;
import com.master.iot.server.IOTConnection;

public abstract class Controller {

	private static final Logger log = Logger.getLogger(Controller.class);

	private final IOTConnection connection = new IOTConnection();

	private boolean running;

	String id;

	Controller(final String id) {
		this.id = id;
	}

	protected void turnOff(final Component component, final History history) throws MasterException {
		final Board board = component.getBoard();
		try {
			if (Controller.log.isTraceEnabled()) {
				Controller.log.trace("Turning off " + component);
			}
			this.connection.turnOff(board.getIp(), component.getPort().toString());
			this.saveHistory(new HistoryInsertDao(history));
		} catch (final Exception e) {
			final MasterException ex = new MasterException("Error on connect to board " + board.getName() + " - " + board.getIp() + " in door " + component.getPort(), e);
			this.saveHistory(new HistoryInsertExceptionDao(history, e));
			throw ex;
		}
	}

	public abstract void execute();

	int getValue(Integer value, final int defaultValue, final String identifier) {
		if (value == null) {
			value = defaultValue;
			Controller.log.warn("Timer " + identifier + " is null, using default value " + defaultValue);
			return value.intValue();
		}
		return defaultValue;
	}

	public boolean isRunning() {
		return this.running;
	}

	protected float read(final Component component, final History history) throws MasterException {
		final Board board = component.getBoard();
		if (Controller.log.isTraceEnabled()) {
			Controller.log.trace("Reading " + component);
		}
		history.setComponent(component);
		history.setRun(System.currentTimeMillis());
		history.setStatus(Status.RUNNING);
		try {
			final float read = this.connection.read(board.getIp(), component.getPort().toString());
			history.setRead(read);
			this.saveHistory(new HistoryInsertDao(history));
			return read;
		} catch (final Exception e) {
			final MasterException ex = new MasterException("Erro ao conectar na placa " + board.getName() + " - " + board.getIp() + " na porta " + component.getPort(), e);
			this.saveHistory(new HistoryInsertExceptionDao(history, e));
			throw ex;
		}
	}

	protected void turnOn(final Component component, final History history) throws MasterException {
		final Board board = component.getBoard();
		if (Controller.log.isTraceEnabled()) {
			Controller.log.trace("Turning on " + component);
		}
		history.setComponent(component);
		history.setRun(System.currentTimeMillis());
		history.setStatus(Status.ON);
		try {
			this.connection.turnOn(board.getIp(), component.getPort().toString());
			this.saveHistory(new HistoryInsertDao(history));
		} catch (final Exception e) {
			final MasterException ex = new MasterException("Erro ao conectar na placa " + board.getName() + " - " + board.getIp() + " na porta " + component.getPort(), e);
			this.saveHistory(new HistoryInsertExceptionDao(history, e));
			throw ex;
		}
	}

	void saveHistory(final Update update) throws MasterException {
		new MasterThread(new ActionUpdate(update), new MasterContextTransaction());
	}

	public synchronized void setRunning(final boolean executando) {
		this.running = executando;
		if (this.running) {
			this.notify();
		}
	}

	protected void sleep(final long tempo) {
		try {
			if (Controller.log.isTraceEnabled()) {
				Controller.log.trace("Aguardando " + tempo + " ms até " + DateFormat.getDateTimeInstance().format(new Date(System.currentTimeMillis() + tempo)));
			}
			Thread.sleep(tempo);
		} catch (final InterruptedException e) {
			Controller.log.error(e.getMessage(), e);
		}
	}

	void regisryComponent(Component component, History history) throws MasterException {
		final Board board = component.getBoard();
		history.setComponent(component);
		history.setRun(System.currentTimeMillis());
		history.setStatus(Status.REGISTRY);
		try {
			this.connection.registry(board.getIp(), component.getPort().toString(), component.getType().getType());
			this.saveHistory(new HistoryInsertDao(history));
		} catch (final Exception e) {
			final MasterException ex = new MasterException("Erro ao conectar na placa " + board.getName() + " - " + board.getIp() + " na porta " + component.getPort(), e);
			this.saveHistory(new HistoryInsertExceptionDao(history, e));
			throw ex;
		}
	}

	void unregisryComponent(Component component, History history) throws MasterException {
		final Board board = component.getBoard();
		history.setComponent(component);
		history.setRun(System.currentTimeMillis());
		history.setStatus(Status.UNREGISTRY);
		try {
			this.connection.unregistry(board.getIp(), component.getPort().toString(), component.getType().getType());
			this.saveHistory(new HistoryInsertDao(history));
		} catch (final Exception e) {
			final MasterException ex = new MasterException("Erro ao conectar na placa " + board.getName() + " - " + board.getIp() + " na porta " + component.getPort(), e);
			this.saveHistory(new HistoryInsertExceptionDao(history, e));
			throw ex;
		}
	}

}
