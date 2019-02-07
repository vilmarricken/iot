package com.master.iot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "HISTORY")
public class History extends com.master.core.persistence.entity.Entity {

	private Component component;

	private String error;

	private Long run;

	private Float read;

	private Monitor monitor;

	private Status status;

	private Timer timer;

	public History() {
	}

	public History(final Component component) {
		this.component = component;
	}

	public History(final Monitor monitor, Component component) {
		this.monitor = monitor;
		this.component = component;
	}

	public History(final Timer timer) {
		this.timer = timer;
	}

	@ManyToOne()
	@JoinColumn(name = "IDCOMPONENT")
	public Component getComponent() {
		return this.component;
	}

	@Column(name = "ERROR")
	public String getError() {
		return this.error;
	}

	@Column(name = "RUN")
	public Long getRun() {
		return this.run;
	}

	@Column(name = "READ")
	public Float getRead() {
		return this.read;
	}

	@ManyToOne()
	@JoinColumn(name = "IDMONITOR")
	public Monitor getMonitor() {
		return this.monitor;
	}

	@Column(name = "STATUS")
	public Status getStatus() {
		return this.status;
	}

	@ManyToOne()
	@JoinColumn(name = "IDTIMER")
	public Timer getTimer() {
		return this.timer;
	}

	public void setComponent(Component component) {
		this.component = component;
	}

	public void setError(String error) {
		this.error = error;
	}

	public void setRun(Long run) {
		this.run = run;
	}

	public void setMonitor(Monitor monitor) {
		this.monitor = monitor;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public void setRead(Float read) {
		this.read = read;
	}

}
