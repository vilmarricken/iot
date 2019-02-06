package com.master.iot.entity.dao;

import com.master.core.persistence.dao.DaoDefault;
import com.master.iot.entity.Timer;

public class TimerDao extends DaoDefault<Timer> {

	public TimerDao() {
		super(Timer.class);
	}

}
