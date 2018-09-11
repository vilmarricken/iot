package com.master.iot.transport;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

import com.master.iot.command.IOTCommand;

public class IOTTransport {

	private final String address;

	private boolean onLine = false;

	private final int port;

	public IOTTransport(final String address, final int port) {
		this.address = address;
		this.port = port;
	}

	void close() {
	}

	public boolean isOpen() {
		return this.onLine;
	}

	void transport(final IOTCommand command) throws Exception {
		final Socket socket = new Socket(this.address, this.port);
		final InputStream in = socket.getInputStream();
		final OutputStream out = socket.getOutputStream();
		final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		final byte[] send = command.getCommand();
		try {
			System.out.println(Arrays.toString(send));
			out.write((byte) send.length);
			out.write(send);
			int t = 0;
			t = in.read();
			for (int i = 0; i < t; i++) {
				buffer.write(t);
			}
			final byte[] response = buffer.toByteArray();
			System.out.println(Arrays.toString(response));
			buffer.reset();
			socket.close();
			in.close();
			out.close();
			this.onLine = true;
			command.setResponse(response);
		} catch (final Exception e) {
			this.onLine = false;
			this.close();
			throw e;
		}

	}

}
