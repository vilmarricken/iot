package com.master.iot.entity;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "PLACA")
public class Placa extends com.master.core.persistence.entity.Entity {

	private List<Componente> componentes;

	private String descricao;

	private UUID id;

	private String ip;

	private String nome;

	private Integer versao;

	@OneToMany(mappedBy = "placa")
	public List<Componente> getComponentes() {
		return this.componentes;
	}

	@Column(name = "DESCRICAO")
	public String getDescricao() {
		return this.descricao;
	}

	@Id
	@GeneratedValue
	@Column(name = "ID")
	public UUID getId() {
		return this.id;
	}

	@Column(name = "IP")
	public String getIp() {
		return this.ip;
	}

	@Override
	@Transient
	protected Serializable getKey() {
		return this.id;
	}

	@Column(name = "NOME")
	public String getNome() {
		return this.nome;
	}

	@Column(name = "VERSAO")
	public Integer getVersao() {
		return this.versao;
	}

	public void setComponentes(final List<Componente> componentes) {
		this.componentes = componentes;
	}

	public void setDescricao(final String descricao) {
		this.descricao = descricao;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public void setIp(final String ip) {
		this.ip = ip;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}

	public void setVersao(final Integer versao) {
		this.versao = versao;
	}

}
