package com.master.iot.entity.dao;

import com.master.core.persistence.dao.Dao;
import com.master.iot.entity.Historico;

public interface HistoricoInsertDao extends Dao {

	void insert(Historico historico);

}
