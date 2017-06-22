package com.master.iot.servlet;

import javax.servlet.FilterConfig;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import com.sun.jersey.spi.container.servlet.ServletContainer;

public class TesteServlet extends ServletContainer {

	@Override
	public void init() throws ServletException {
		super.init();
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		super.init(filterConfig);
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
}
