package com.master.platform.core.resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResourceHTTP extends ResourceBase {

	private HttpServletRequest request;

	private HttpServletResponse response;

	public ResourceHTTP(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public HttpServletRequest getRequest() {
		return this.request;
	}

	public HttpServletResponse getResponse() {
		return this.response;
	}

}
