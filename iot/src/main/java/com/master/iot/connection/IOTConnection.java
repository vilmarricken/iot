package com.master.iot.connection;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.apache.log4j.Logger;

public class IOTConnection {

	private static final String COMMAND_EXECUTE = "3";
	private static final String COMMAND_REGISTRY = "1";
	private static final String COMMAND_UNREGISTRY = "2";

	private static final String DEVICE_RELAY = "1";
	private static final String DEVICE_THERMOMETER = "3";

	private static final String DEVICE_TURN_OFF = "0";
	private static final String DEVICE_TURN_ON = "1";

	private static final Logger log = Logger.getLogger(IOTConnection.class);

	private static final int PORT = 800;

	private String command(final String command, final String address, int tries) throws IOTConnectionException {
		Socket s = null;
		InputStream in = null;
		OutputStream out = null;
		try {
			s = new Socket(address, IOTConnection.PORT);
			out = s.getOutputStream();
			if (IOTConnection.log.isDebugEnabled()) {
				IOTConnection.log.debug("Command:" + command);
			}
			System.out.println((byte) command.length());
			out.write((byte) command.length());
			out.write(command.getBytes());
			out.flush();
			in = s.getInputStream();
			final int t = in.read();
			final ByteArrayOutputStream bout = new ByteArrayOutputStream(t);
			for (int i = 0; i < t; i++) {
				final char c = (char) in.read();
				bout.write(c);
			}
			String response = new String(bout.toByteArray());
			if (IOTConnection.log.isDebugEnabled()) {
				IOTConnection.log.debug("Response: " + response);
			}
			if (response.startsWith("OK")) {
				if (response.length() > 2) {
					return response.substring(3);
				}
				return "";
			}
			if (response.startsWith("ERROR")) {
				IOTConnection.log.error("Response error: " + response);
				if (response.startsWith(IOTConnectionException.IOT_ERROR_INVALID_COMMAND)) {
					return this.command(command, address, tries + 1);
				}
				if (response.length() > 6) {
					response = response.substring(6);
					throw new IOTConnectionException(response.split(":"));
				}
			}
			throw new IOTConnectionException(response);
		} catch (final Exception e) {
			throw new IOTConnectionException(e.getMessage(), e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (final IOException e) {
					IOTConnection.log.error(e.getMessage(), e);
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (final IOException e) {
					IOTConnection.log.error(e.getMessage(), e);
				}
			}
			if (s != null) {
				try {
					s.close();
				} catch (final IOException e) {
					IOTConnection.log.error(e.getMessage(), e);
				}
			}
		}
	}

	public void unregistry(final String address, final String port, String device) throws IOTConnectionException {
		this.command(IOTConnection.COMMAND_UNREGISTRY + ";" + port + ";" + device, address, 0);
	}

	public void registry(final String address, final String port, String device) throws IOTConnectionException {
		this.command(IOTConnection.COMMAND_REGISTRY + ";" + port + ";" + device, address, 0);
	}

	public void turnOff(final String address, final String port) throws IOTConnectionException {
		this.command(IOTConnection.COMMAND_EXECUTE + ";" + port + ";" + IOTConnection.DEVICE_TURN_OFF, address, 0);
	}

	public void turnOn(final String address, final String port) throws IOTConnectionException {
		this.command(IOTConnection.COMMAND_EXECUTE + ";" + port + ";" + IOTConnection.DEVICE_TURN_ON, address, 0);
	}

	public float read(String address, String port) throws IOTConnectionException {
		final String value = this.command(IOTConnection.COMMAND_EXECUTE + ";" + port, address, 0);
		if (IOTConnection.log.isDebugEnabled()) {
			IOTConnection.log.debug("Returning value: " + value);
		}
		return Float.valueOf(value);
	}

}
