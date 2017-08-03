package com.master.platform.core.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.master.platform.core.resource.ResourceManager;
import com.master.platform.core.thread.ApplicationRunnable;
import com.master.platform.core.thread.ApplicationThread;
import com.master.platform.core.thread.ApplicationThreadContext;
import com.master.platform.core.thread.ApplicationThreadWeb;

public class MasterApplicationFilter implements Filter {

	private static final Logger log = LogManager.getLogger(ResourceManager.class);

	public MasterApplicationFilter() {
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		ApplicationThreadContext context = new ApplicationThreadWeb((HttpServletRequest) request, (HttpServletResponse) response);
		new ApplicationThread(context, new ApplicationRunnable() {
			@Override
			public void run() {
				try {
					chain.doFilter(request, response);
				} catch (IOException e) {
					MasterApplicationFilter.log.error(e.getMessage(), e);
				} catch (ServletException e) {
					MasterApplicationFilter.log.error(e.getMessage(), e);
				}
			}
		}).run();
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

}
