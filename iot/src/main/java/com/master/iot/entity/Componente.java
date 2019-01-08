package com.master.iot.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "COMPONENTE")
public class Componente extends com.master.core.persistence.entity.Entity {

	private UUID id;

	private String nome;

	private Placa placa;

	private Integer porta;

	private ComponenteTipo tipo;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	public UUID getId() {
		return this.id;
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

	@ManyToOne()
	@JoinColumn(name = "IDPLACA")
	public Placa getPlaca() {
		return this.placa;
	}

	@Column(name = "PORTA")
	public Integer getPorta() {
		return this.porta;
	}

	@Column(name = "TIPO")
	public ComponenteTipo getTipo() {
		return this.tipo;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}

	public void setPlaca(final Placa placa) {
		this.placa = placa;
	}

	public void setPorta(final Integer porta) {
		this.porta = porta;
	}

	public void setTipo(final ComponenteTipo tipo) {
		this.tipo = tipo;
	}

}
