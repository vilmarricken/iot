package com.master.core.resource;

import com.master.core.exception.MasterException;

public interface MasterContext {

	void error() throws MasterException;

	void finish() throws MasterException;

	void start() throws MasterException;

}
