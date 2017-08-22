package com.master.platform.core.entity;

import com.master.platform.core.entity.dao.DaoEntity;
import com.master.platform.core.entity.dao.DaoEntityFake;

public class MasterEntityObject implements MasterEntity {

	private DaoEntity dao = DaoEntityFake.INSTANCE;

	public MasterEntityObject() {
	}

	boolean hasChanges() {
		return this.dao.hasChanges();
	}

	void detach() {

	}

}
