package com.master.persistence.entity;

import java.io.Serializable;

public abstract class Entity {

	abstract protected Serializable getKey();

}
