package com.master.core.persistence;

import java.sql.Connection;

public interface Update {

	void executeUpdate(Connection connection) throws PersistenceException;

}
