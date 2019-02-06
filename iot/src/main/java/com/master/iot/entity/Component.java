package com.master.iot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "COMPONENT")
public class Component extends com.master.core.persistence.entity.Entity {

	private String name;

	private Board board;

	private Integer door;

	private ComponentType type;

	@Column(name = "NAME")
	public String getName() {
		return this.name;
	}

	@ManyToOne()
	@JoinColumn(name = "IDBOARD")
	public Board getBoard() {
		return this.board;
	}

	@Column(name = "DOOR")
	public Integer getDoor() {
		return this.door;
	}

	@Column(name = "TYPE")
	public ComponentType getType() {
		return this.type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public void setDoor(Integer door) {
		this.door = door;
	}

	public void setType(ComponentType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Component: {name:" + this.name + ", type: " + this.type + ", board: " + this.board + ", door: " + this.door + "}";
	}

}
