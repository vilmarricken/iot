package com.master.core.persistence.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Entity {

	private UUID id;

	@Override
	public boolean equals(final Object obj) {
		if (this.id == null) {
			return false;
		}
		if (obj.getClass().equals(this.getClass())) {
			final Entity e = (Entity) obj;
			if (e.id == null) {
				return false;
			}
			return this.id.equals(e.id);
		}
		return false;
	}

	@Id
	@GeneratedValue
	@Column(name = "ID")
	public final UUID getId() {
		return this.id;
	}

	@Override
	public int hashCode() {
		if (this.id == null) {
			throw new RuntimeException("Erro ao gerar o hashcode pois o ID é nulo");
		}
		return this.id.hashCode();
	}

	public final void setId(final UUID id) {
		this.id = id;
	}

}
