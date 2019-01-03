package com.master.iot.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Monitor extends com.master.persistence.entity.Entity {

	private Float alvo;

	private Componente controlador;

	private String descricao;

	private UUID id;

	private Componente leitor;

	private Float limite;

	private String nome;

	private MonitorTipo tipo;

	@Column(name = "ALVO")
	public Float getAlvo() {
		return this.alvo;
	}

	@ManyToOne
	@JoinColumn(name = "IDCONTROLADOR")
	public Componente getControlador() {
		return this.controlador;
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

	@Override
	protected Serializable getKey() {
		return this.id;
	}

	@ManyToOne
	@JoinColumn(name = "IDLEITOR")
	public Componente getLeitor() {
		return this.leitor;
	}

	@Column(name = "LIMITE")
	public Float getLimite() {
		return this.limite;
	}

	@Column(name = "NOME")
	public String getNome() {
		return this.nome;
	}

	@Column(name = "TIPO")
	public MonitorTipo getTipo() {
		return this.tipo;
	}

	public void setAlvo(final Float alvo) {
		this.alvo = alvo;
	}

	public void setControlador(final Componente controlador) {
		this.controlador = controlador;
	}

	public void setDescricao(final String descricao) {
		this.descricao = descricao;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public void setLeitor(final Componente leitor) {
		this.leitor = leitor;
	}

	public void setLimite(final Float limite) {
		this.limite = limite;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}

	public void setTipo(final MonitorTipo tipo) {
		this.tipo = tipo;
	}

}
