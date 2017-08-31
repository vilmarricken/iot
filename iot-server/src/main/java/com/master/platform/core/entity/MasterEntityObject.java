package com.master.platform.core.entity;

import com.master.platform.core.entity.dao.DaoAttributeEntity;
import com.master.platform.core.entity.dao.DaoEntityFake;
import com.master.platform.core.entity.dao.DaoEntityValue;

public class MasterEntityObject implements MasterEntity {

	private DaoAttributeEntity dao = DaoEntityFake.INSTANCE;

	public MasterEntityObject() {
	}

	void attach() {
		if (this.dao.hasChanges()) {
			throw new RuntimeException("Objeto com altera��es: " + this.dao);
		}
		this.dao = new DaoEntityValue();
	}

	void detach() {
		if (this.dao.hasChanges()) {
			throw new RuntimeException("Objeto com altera��es: " + this.dao);
		}
		this.dao = DaoEntityFake.INSTANCE;
	}

	boolean hasChanges() {
		return this.dao.hasChanges();
	}

}
