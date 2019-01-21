package com.master.iot.server;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

public class Server {

	private static final Logger logger = Logger.getLogger(Server.class);

	public static void main(final String[] args) throws Exception {
		ServerSocket sc = null;
		try {
			sc = new ServerSocket(800);
			if (Server.logger.isInfoEnabled()) {
				Server.logger.info("Started");
			}
			final boolean run = true;
			while (run) {
				final Socket s = sc.accept();
				final String address = s.getInetAddress().getHostAddress();
				if (Server.logger.isDebugEnabled()) {
					Server.logger.debug("Address: " + address);
				}
				final InputStream in = s.getInputStream();
				final int t = in.read();
				if (Server.logger.isTraceEnabled()) {
					Server.logger.trace("Size: " + t);
				}
				final char[] text = new char[t];
				for (int i = 0; i < t; i++) {
					final int c = in.read();
					text[i] = (char) c;
				}
				final String id = new String(text);
				if (Server.logger.isDebugEnabled()) {
					Server.logger.debug("Id: " + id);
				}
			}
		} finally {
			sc.close();
		}
	}

	public Server() {
	}

}
