package com.master.iot.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "BOARD")
public class Board extends com.master.core.persistence.entity.Entity {

	private List<Component> components;

	private String description;

	private String ip;

	private String name;

	private Integer version;

	@OneToMany(mappedBy = "board")
	public List<Component> getComponents() {
		return this.components;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return this.description;
	}

	@Column(name = "IP")
	public String getIp() {
		return this.ip;
	}

	@Column(name = "NAME")
	public String getName() {
		return this.name;
	}

	@Column(name = "VERSION")
	public Integer getVersion() {
		return this.version;
	}

	public void setComponents(final List<Component> componentes) {
		this.components = componentes;
	}

	public void setDescription(final String descricao) {
		this.description = descricao;
	}

	public void setIp(final String ip) {
		this.ip = ip;
	}

	public void setName(final String nome) {
		this.name = nome;
	}

	public void setVersion(final Integer versao) {
		this.version = versao;
	}

	@Override
	public String toString() {
		return "Board:{name: " + this.name + ", ip: " + this.ip + "}";
	}

}
