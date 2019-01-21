package com.master.core.util;

import java.io.File;
import java.net.MalformedURLException;

import javax.xml.parsers.FactoryConfigurationError;

import org.apache.log4j.xml.DOMConfigurator;

public abstract class LogUtil {

	private static boolean configured;

	public static final void config() {
		if (!LogUtil.configured) {
			final File f = new File("log4j.xml");
			System.out.println(f.getAbsolutePath() + " - " + f.exists());
			try {
				DOMConfigurator.configure(f.toURI().toURL());
			} catch (final MalformedURLException e) {
				e.printStackTrace();
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
