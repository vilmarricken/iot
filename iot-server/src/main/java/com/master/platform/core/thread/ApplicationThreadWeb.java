package com.master.platform.core.thread;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.master.platform.core.resource.ResourceHTTP;
import com.master.platform.core.resource.ResourceManager;

public class ApplicationThreadWeb extends ApplicationThreadContextAbstract {

	private static final Logger log = LogManager.getLogger(ApplicationThreadWeb.class);

	private static final String OPEN_TRANSACTION_PARAMETER = "openTransaction";

	private final HttpServletRequest request;

	private final HttpServletResponse response;

	public ApplicationThreadWeb(final HttpServletRequest request, final HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	@Override
	public void finish() {
		ResourceManager.get().finish();
	}

	@Override
	public void init() {
		this.setResource(new ResourceHTTP(this.request, this.response));
	}

	protected boolean isActiveTransaction() {
		final String openTransactionParameter = this.request.getParameter(ApplicationThreadWeb.OPEN_TRANSACTION_PARAMETER);
		if (ApplicationThreadWeb.log.isDebugEnabled()) {
			ApplicationThreadWeb.log.debug("Transaction parameter: " + openTransactionParameter);
		}
		if (openTransactionParameter == null || openTransactionParameter.trim().length() == 0) {
			return "POST".equalsIgnoreCase(this.request.getMethod());
		}
		return openTransactionParameter.equals("true");
	}

}
