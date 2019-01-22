package com.master.core.persistence;

import java.sql.Connection;

public interface Select<T> {

	T executeQuery(Connection connection);

}
