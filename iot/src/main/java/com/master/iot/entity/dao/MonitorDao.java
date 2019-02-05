package com.master.iot.entity.dao;

import com.master.core.persistence.dao.DaoDefault;
import com.master.iot.entity.Monitor;

public class MonitorDao extends DaoDefault<Monitor> {

	public MonitorDao() {
		super(Monitor.class);
	}

}
