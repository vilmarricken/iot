package com.master.platform.core.web.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JsonControllerServlet extends HttpServlet {

	private String path;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.path = config.getServletContext().getContextPath();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestURI();
	}

}
