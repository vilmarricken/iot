package com.master.iot.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PLACA")
public class Placa extends com.master.core.persistence.entity.Entity {

	private List<Componente> componentes;

	private String descricao;

	private String ip;

	private String nome;

	private Integer versao;

	@OneToMany(mappedBy = "placa")
	public List<Componente> getComponentes() {
		return this.componentes;
	}

	@Column(name = "DESCRICAO")
	public String getDescricao() {
		return this.descricao;
	}

	@Column(name = "IP")
	public String getIp() {
		return this.ip;
	}

	@Column(name = "NOME")
	public String getNome() {
		return this.nome;
	}

	@Column(name = "VERSAO")
	public Integer getVersao() {
		return this.versao;
	}

	public void setComponentes(final List<Componente> componentes) {
		this.componentes = componentes;
	}

	public void setDescricao(final String descricao) {
		this.descricao = descricao;
	}

	public void setIp(final String ip) {
		this.ip = ip;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}

	public void setVersao(final Integer versao) {
		this.versao = versao;
	}

	@Override
	public String toString() {
		return "nome: " + this.nome + ", ip: " + this.ip;
	}

}
