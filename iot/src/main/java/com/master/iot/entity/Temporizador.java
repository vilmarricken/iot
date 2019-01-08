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
@Table(name = "HISTORICO")
public class Temporizador extends com.master.core.persistence.entity.Entity {

	private Componente componente;

	private String descricao;

	private Integer desligado;

	private UUID id;

	private Integer inicial;

	private Integer iniciar;

	private Integer ligado;

	private String nome;

	private TemporizadorTipo tipo;

	@ManyToOne
	@JoinColumn(name = "IDCOMPONENTE")
	public Componente getComponente() {
		return this.componente;
	}

	@Column(name = "DESCRICAO")
	public String getDescricao() {
		return this.descricao;
	}

	@Column(name = "DESLIGADO")
	public Integer getDesligado() {
		return this.desligado;
	}

	@Id
	@GeneratedValue
	@Column(name = "ID")
	public UUID getId() {
		return this.id;
	}

	@Column(name = "INICIAL")
	public Integer getInicial() {
		return this.inicial;
	}

	@Column(name = "INICIAR")
	public Integer getIniciar() {
		return this.iniciar;
	}

	@Override
	@Transient
	protected Serializable getKey() {
		return this.id;
	}

	@Column(name = "LIGADO")
	public Integer getLigado() {
		return this.ligado;
	}

	@Column(name = "NOME")
	public String getNome() {
		return this.nome;
	}

	@Column(name = "TIPO")
	public TemporizadorTipo getTipo() {
		return this.tipo;
	}

	public void setComponente(final Componente componente) {
		this.componente = componente;
	}

	public void setDescricao(final String descricao) {
		this.descricao = descricao;
	}

	public void setDesligado(final Integer desligado) {
		this.desligado = desligado;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public void setInicial(final Integer inicial) {
		this.inicial = inicial;
	}

	public void setIniciar(final Integer iniciar) {
		this.iniciar = iniciar;
	}

	public void setLigado(final Integer ligado) {
		this.ligado = ligado;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}

	public void setTipo(final TemporizadorTipo tipo) {
		this.tipo = tipo;
	}

}
