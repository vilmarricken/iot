package com.master.iot.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Monitor {

	private UUID id;

	private String nome;

	private String descricao;

	private Float alvo;

	private Float limite;

	private MonitorTipo tipo;

	private Componente leitor;

	private Componente controlador;

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

	@Column(name = "DESCRICAO")
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(name = "ALVO")
	public Float getAlvo() {
		return alvo;
	}

	public void setAlvo(Float alvo) {
		this.alvo = alvo;
	}

	@Column(name = "LIMITE")
	public Float getLimite() {
		return limite;
	}

	public void setLimite(Float limite) {
		this.limite = limite;
	}

	@Column(name = "TIPO")
	public MonitorTipo getTipo() {
		return tipo;
	}

	public void setTipo(MonitorTipo tipo) {
		this.tipo = tipo;
	}

	@ManyToOne
	@JoinColumn(name = "IDLEITOR")
	public Componente getLeitor() {
		return leitor;
	}

	public void setLeitor(Componente leitor) {
		this.leitor = leitor;
	}

	@ManyToOne
	@JoinColumn(name = "IDCONTROLADOR")
	public Componente getControlador() {
		return controlador;
	}

	public void setControlador(Componente controlador) {
		this.controlador = controlador;
	}

}
