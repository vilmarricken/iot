package com.master.platform.core.resource;

import java.sql.Connection;

import com.master.platform.core.entity.manager.MasterEntityManager;

public interface Resource {

	MasterEntityManager getEntityManager();

	Connection getConnection();

}
