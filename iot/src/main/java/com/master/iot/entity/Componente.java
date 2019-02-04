package com.master.iot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "COMPONENTE")
public class Componente extends com.master.core.persistence.entity.Entity {

	private String nome;

	private Placa placa;

	private Integer porta;

	private ComponenteTipo tipo;

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

	@Override
	public String toString() {
		return "Componente: " + this.nome + ", tipo: " + this.tipo + ", placa: " + this.placa + ", porta: " + this.porta;
	}
}
