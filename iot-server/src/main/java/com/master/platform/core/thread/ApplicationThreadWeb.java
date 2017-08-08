package com.master.platform.core.thread;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.master.platform.core.resource.ResourceHTTP;
import com.master.platform.core.resource.ResourceManager;

public class ApplicationThreadWeb implements ApplicationThreadContext {

	private static final String OPEN_TRANSACTION_PARAMETER = "openTransaction";

	private static final Logger log = LogManager.getLogger(ApplicationThreadWeb.class);

	private HttpServletRequest request;

	private HttpServletResponse response;

	public ApplicationThreadWeb(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	@Override
	public void init() {
		ResourceManager.get().start(new ResourceHTTP(this.request, this.response));
		boolean isActiveTransaction = isActiveTransaction();
	}

	private boolean isActiveTransaction() {
		String openTransactionParameter = this.request.getParameter(OPEN_TRANSACTION_PARAMETER);
		if (log.isDebugEnabled()) {
			log.debug("Transaction parameter: " + openTransactionParameter);
		}
		if (openTransactionParameter == null || openTransactionParameter.trim().length() == 0) {
return this.request.getMethod()
		}
		return false;
	}

	@Override
	public void finish() {
		ResourceManager.get().finish();
	}

}
