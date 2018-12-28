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
@Table(name = "HISTORICO")
public class Historico {

	private UUID id;

	private Long criado;

	private Long inicio;

	private Long fim;

	private Situacao situacao;

	private String erro;

	private Componente componente;

	private Monitor monitor;

	private Temporizador temporizador;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	@Column(name = "CRIADO")
	public Long getCriado() {
		return criado;
	}

	public void setCriado(Long criado) {
		this.criado = criado;
	}

	@Column(name = "INICIO")
	public Long getInicio() {
		return inicio;
	}

	public void setInicio(Long inicio) {
		this.inicio = inicio;
	}

	@Column(name = "FIM")
	public Long getFim() {
		return fim;
	}

	public void setFim(Long fim) {
		this.fim = fim;
	}

	@Column(name = "SITUACAO")
	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	@Column(name = "ERRO")
	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}

	@ManyToOne()
	@JoinColumn(name = "IDCOMPONENTE")
	public Componente getComponente() {
		return componente;
	}

	public void setComponente(Componente componente) {
		this.componente = componente;
	}

	@ManyToOne()
	@JoinColumn(name = "IDMONITOR")
	public Monitor getMonitor() {
		return monitor;
	}

	public void setMonitor(Monitor monitor) {
		this.monitor = monitor;
	}

	@ManyToOne()
	@JoinColumn(name = "IDTEMPORIZADOR")
	public Temporizador getTemporizador() {
		return temporizador;
	}

	public void setTemporizador(Temporizador temporizador) {
		this.temporizador = temporizador;
	}

}
