package com.master.iot.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "COMPONENTE")
public class Componente {

	private UUID id;

	private String nome;

	private ComponenteTipo tipo;

	private Integer porta;
	
	private Placa placa;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	@Column(name = "NOME")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "TIPO")
	public ComponenteTipo getTipo() {
		return tipo;
	}

	public void setTipo(ComponenteTipo tipo) {
		this.tipo = tipo;
	}

	@Column(name = "PORTA")
	public Integer getPorta() {
		return porta;
	}

	public void setPorta(Integer porta) {
		this.porta = porta;
	}

	@ManyToOne()
	@JoinColumn(name="IDPLACA")
	public Placa getPlaca() {
		return placa;
	}
	
	public void setPlaca(Placa placa) {
		this.placa = placa;
	}
	
}
