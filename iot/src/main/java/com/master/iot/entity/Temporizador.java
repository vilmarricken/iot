package com.master.iot.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Temporizador {

	private UUID id;

	private String nome;

	private String descricao;

	private TemporizadorTipo tipo;

	private Integer inicial;

	private Integer iniciar;

	private Integer ligado;

	private Integer desligado;

	private Componente componente;

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

	@Column(name = "TIPO")
	public TemporizadorTipo getTipo() {
		return tipo;
	}

	public void setTipo(TemporizadorTipo tipo) {
		this.tipo = tipo;
	}

	@Column(name = "INICIAL")
	public Integer getInicial() {
		return inicial;
	}

	public void setInicial(Integer inicial) {
		this.inicial = inicial;
	}

	@Column(name = "INICIAR")
	public Integer getIniciar() {
		return iniciar;
	}

	public void setIniciar(Integer iniciar) {
		this.iniciar = iniciar;
	}

	@Column(name = "LIGADO")
	public Integer getLigado() {
		return ligado;
	}

	public void setLigado(Integer ligado) {
		this.ligado = ligado;
	}

	@Column(name = "DESLIGADO")
	public Integer getDesligado() {
		return desligado;
	}

	public void setDesligado(Integer desligado) {
		this.desligado = desligado;
	}

	@ManyToOne
	@JoinColumn(name = "IDCOMPONENTE")
	public Componente getComponente() {
		return componente;
	}

	public void setComponente(Componente componente) {
		this.componente = componente;
	}

}
