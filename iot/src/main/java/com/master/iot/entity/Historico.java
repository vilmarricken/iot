package com.master.iot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "HISTORICO")
public class Historico extends com.master.core.persistence.entity.Entity {

	private Componente componente;

	private String erro;

	private Long fim;

	private Long inicio;

	private Monitor monitor;

	private Situacao situacao;

	private Temporizador temporizador;

	@ManyToOne()
	@JoinColumn(name = "IDCOMPONENTE")
	public Componente getComponente() {
		return this.componente;
	}

	@Column(name = "ERRO")
	public String getErro() {
		return this.erro;
	}

	@Column(name = "FIM")
	public Long getFim() {
		return this.fim;
	}

	@Column(name = "INICIO")
	public Long getInicio() {
		return this.inicio;
	}

	@ManyToOne()
	@JoinColumn(name = "IDMONITOR")
	public Monitor getMonitor() {
		return this.monitor;
	}

	@Column(name = "SITUACAO")
	public Situacao getSituacao() {
		return this.situacao;
	}

	@ManyToOne()
	@JoinColumn(name = "IDTEMPORIZADOR")
	public Temporizador getTemporizador() {
		return this.temporizador;
	}

	public void setComponente(final Componente componente) {
		this.componente = componente;
	}

	public void setErro(final String erro) {
		this.erro = erro;
	}

	public void setFim(final Long fim) {
		this.fim = fim;
	}

	public void setInicio(final Long inicio) {
		this.inicio = inicio;
	}

	public void setMonitor(final Monitor monitor) {
		this.monitor = monitor;
	}

	public void setSituacao(final Situacao situacao) {
		this.situacao = situacao;
	}

	public void setTemporizador(final Temporizador temporizador) {
		this.temporizador = temporizador;
	}

}
