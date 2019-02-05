package com.master.iot.main;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

import com.master.core.resource.MasterContextTransaction;
import com.master.core.resource.MasterThread;

public class IotServer {

	private static final Logger log = Logger.getLogger(IotServer.class);

	public IotServer() {
	}

	protected void listen() {
		ServerSocket sc = null;
		try {
			sc = new ServerSocket(800);
			if (IotServer.log.isInfoEnabled()) {
				IotServer.log.info("Started");
			}
			final boolean run = true;
			while (run) {
				final Socket s = sc.accept();
				final String address = s.getInetAddress().getHostAddress();
				if (IotServer.log.isDebugEnabled()) {
					IotServer.log.debug(address);
				}
				final InputStream in = s.getInputStream();
				final int t = in.read();
				if (IotServer.log.isDebugEnabled()) {
					IotServer.log.debug("T: " + t);
				}
				final char[] text = new char[t];
				for (int i = 0; i < t; i++) {
					final int c = in.read();
					text[i] = (char) c;
				}
				final String identifier = new String(text);
				if (IotServer.log.isDebugEnabled()) {
					IotServer.log.debug(identifier);
				}
				new MasterThread(new RegistryBoard(identifier, address), new MasterContextTransaction()).start("Registry board");

			}
		} catch (final IOException e) {
			IotServer.log.error(e.getMessage(), e);
		} finally {
			if (sc != null) {
				try {
					sc.close();
				} catch (final IOException e) {
					IotServer.log.error(e.getMessage(), e);
				}
			}
		}
	}

}
