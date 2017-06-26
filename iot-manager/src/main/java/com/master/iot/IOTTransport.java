package com.master.iot;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

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

	byte[] transport(final String send) throws Exception {

		final Socket socket = new Socket(this.address, this.port);

		final InputStream in = socket.getInputStream();

		final OutputStream out = socket.getOutputStream();

		final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		try {
			final byte[] b = send.getBytes();
			System.out.println(Arrays.toString(b));
			out.write(b);
			int t = 0;
			do {
				t = in.read();
				System.out.println(t);
				if (t != '\r') {
					buffer.write(t);
				}
			} while (t != '\r');
			final byte[] ret = buffer.toByteArray();
			System.out.println(new String(ret));
			buffer.reset();
			socket.close();
			in.close();
			out.close();
			this.onLine = true;
			return ret;
		} catch (final Exception e) {
			this.onLine = false	;
			this.close();
			throw e;
		}

	}

}
