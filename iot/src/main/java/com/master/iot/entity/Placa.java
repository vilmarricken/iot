package com.master.iot.entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

public class Placa {

	private UUID id;

	private String nome;

	private String descricao;

	private Integer versao;

	private String ip;
	
	private List<Componente> componentes;

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

	@Column(name = "VERSAO")
	public Integer getVersao() {
		return versao;
	}

	public void setVersao(Integer versao) {
		this.versao = versao;
	}

	@Column(name = "IP")
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@OneToMany(mappedBy="placa")
	public List<Componente> getComponentes() {
		return componentes;
	}
	
	public void setComponentes(List<Componente> componentes) {
		this.componentes = componentes;
	}
	
}
