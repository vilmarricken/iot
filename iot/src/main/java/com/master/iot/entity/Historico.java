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

@Entity
@Table(name = "HISTORICO")
public class Historico extends com.master.persistence.entity.Entity {

	private Componente componente;

	private Long criado;

	private String erro;

	private Long fim;

	private UUID id;

	private Long inicio;

	private Monitor monitor;

	private Situacao situacao;

	private Temporizador temporizador;

	@ManyToOne()
	@JoinColumn(name = "IDCOMPONENTE")
	public Componente getComponente() {
		return this.componente;
	}

	@Column(name = "CRIADO")
	public Long getCriado() {
		return this.criado;
	}

	@Column(name = "ERRO")
	public String getErro() {
		return this.erro;
	}

	@Column(name = "FIM")
	public Long getFim() {
		return this.fim;
	}

	@Id
	@GeneratedValue
	@Column(name = "ID")
	public UUID getId() {
		return this.id;
	}

	@Column(name = "INICIO")
	public Long getInicio() {
		return this.inicio;
	}

	@Override
	protected Serializable getKey() {
		return this.id;
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

	public void setCriado(final Long criado) {
		this.criado = criado;
	}

	public void setErro(final String erro) {
		this.erro = erro;
	}

	public void setFim(final Long fim) {
		this.fim = fim;
	}

	public void setId(final UUID id) {
		this.id = id;
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
