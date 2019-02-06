package com.master.iot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TIMER")
public class Timer extends com.master.core.persistence.entity.Entity {

	private Component component;

	private String description;

	private Integer off;

	private Integer on;

	private Integer start;

	private String name;

	@ManyToOne
	@JoinColumn(name = "IDCOMPONENT")
	public Component getComponent() {
		return this.component;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return this.description;
	}

	@Column(name = "OFF")
	public Integer getOff() {
		return this.off;
	}

	@Column(name = "ON")
	public Integer getOn() {
		return this.on;
	}

	@Column(name = "NAME")
	public String getName() {
		return this.name;
	}

	@Column(name = "START")
	public Integer getStart() {
		return this.start;
	}

	public void setComponent(final Component componente) {
		this.component = componente;
	}

	public void setDescription(final String descricao) {
		this.description = descricao;
	}

	public void setOff(final Integer desligado) {
		this.off = desligado;
	}

	public void setOn(final Integer inicial) {
		this.on = inicial;
	}

	public void setName(final String nome) {
		this.name = nome;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	@Override
	public String toString() {
		return "Timer: Name: " + this.name + "}";
	}
}
