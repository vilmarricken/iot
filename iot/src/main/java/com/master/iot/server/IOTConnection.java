package com.master.iot.server;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class IOTConnection {

	private static final String COMMAND_EXECUTE = "3";
	private static final String COMMAND_REGISTRY = "1";
	private static final String COMMAND_UNREGISTRY = "2";

	private static final String DEVICE_RELAY = "1";
	private static final String DEVICE_THERMOMETER = "3";

	private static final String DEVICE_TURN_ON = "1";
	private static final String DEVICE_TURN_OFF = "0";

	private static final int PORT = 800;

	private void command(final String command, String address) throws IOTConnectionException {
		Socket s;
		try {
			s = new Socket(address, IOTConnection.PORT);
			final OutputStream out = s.getOutputStream();
			System.out.println((byte) command.length());
			out.write((byte) command.length());
			out.write(command.getBytes());
			out.flush();
			final InputStream in = s.getInputStream();
			final int t = in.read();
			final ByteArrayOutputStream bout = new ByteArrayOutputStream(t);
			for (int i = 0; i < t; i++) {
				final char c = (char) in.read();
				bout.write(c);
			}
			System.out.println(new String(bout.toByteArray()));
			in.close();
			out.close();
			s.close();
			System.out.println("Fim");
		} catch (final Exception e) {
			throw new IOTConnectionException(e.getMessage(), e);
		}
	}

	public void ligar(String address, String port) throws IOTConnectionException {
		this.command(IOTConnection.COMMAND_EXECUTE + ";" + port + ";" + IOTConnection.DEVICE_TURN_ON, address);
	}

}
