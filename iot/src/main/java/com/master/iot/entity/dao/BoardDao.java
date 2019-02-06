package com.master.iot.entity.dao;

import com.master.core.persistence.dao.DaoDefault;
import com.master.iot.entity.Board;

public class BoardDao extends DaoDefault<Board> {

	public BoardDao() {
		super(Board.class);
	}

}
