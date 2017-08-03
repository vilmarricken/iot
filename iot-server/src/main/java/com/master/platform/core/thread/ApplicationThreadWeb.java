package com.master.platform.core.thread;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.platform.core.resource.ResourceHTTP;
import com.master.platform.core.resource.ResourceManager;

public class ApplicationThreadWeb implements ApplicationThreadContext {

	private HttpServletRequest request;

	private HttpServletResponse response;

	public ApplicationThreadWeb(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	@Override
	public void init() {
		ResourceManager.get().start(new ResourceHTTP(this.request, this.response));
	}

	@Override
	public void finish() {
		ResourceManager.get().finish();
	}

}
