package com.master.core.util;

import java.net.URL;

import javax.xml.parsers.FactoryConfigurationError;

import org.apache.log4j.xml.DOMConfigurator;

public abstract class LogUtil {

	private static boolean configured;

	public static final void config() {
		if (!LogUtil.configured) {
			final URL resource = LogUtil.class.getResource("/log4j.xml");
			System.out.println(resource);
			try {
				DOMConfigurator.configure(resource);
			} catch (final FactoryConfigurationError e) {
				e.printStackTrace();
			}
			LogUtil.configured = true;
		} else {
			System.out.println("O log já esta configurado.");
		}
	}

	private LogUtil() {
	}

}
