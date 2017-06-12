package com.master.iot;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class IOTTransport {

	private Socket socket;

	private InputStream in;

	private OutputStream out;

	public IOTTransport(Socket socket) throws Exception {
		this.socket = socket;
		this.in = this.socket.getInputStream();
		this.out = this.socket.getOutputStream();
	}

	byte[] transport(byte[] send) throws Exception {
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		try {
			this.out.write(send);
			this.out.write(-1);
			int t = 0;
			do {
				t = this.in.read();
				if (t != 255) {
					buffer.write(t);
				}
			} while (t != 255);
			byte[] ret = buffer.toByteArray();
			buffer.reset();
			return ret;
		} catch (Exception e) {
			close();
			throw e;
		}
	}

	void close() {
		try {
			this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.in = null;
		this.out = null;
		this.socket = null;
	}

	public boolean isOpen() {
		return this.socket != null;
	}

}
