package com.master.iot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MONITOR")
public class Monitor extends com.master.core.persistence.entity.Entity {

	private Float alvo;

	private Componente controlador;

	private String descricao;

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
