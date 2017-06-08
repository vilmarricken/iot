package com.master.iot;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class IOTClient {

	public static void main(String[] args) throws Exception {
		boolean c[] = new boolean[5];
		String name = null;
		Socket s = new Socket("localhost", 1000);
		while (true) {
			InputStream in = s.getInputStream();
			OutputStream out = s.getOutputStream();
			byte[] b = read(in);
			switch (b[0]) {
			case 0:
				name = new String(b, 1, b.length - 1);
				send(new byte[0], out);
				break;
			case 1:
				if (name != null) {
					send(name.getBytes(), out);
				} else {
					send(new byte[0], out);
				}
				break;
			case 2:
				byte send[] = new byte[c.length];
				for (int i = 0; i < b.length; i++) {
					send[i] = (byte) (c[i] ? 1 : 0);
					send(send, out);
				}
				break;
			case 3:
				c[b[1]] = true;
				send(new byte[0], out);
				break;
			case 4:
				c[b[1]] = false;
				send(new byte[0], out);
				break;
			default:
				System.out.println("Invalid command");
			}
		}
	}

	static byte[] read(InputStream in) throws Exception {
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		int t = 0;
		do {
			t = in.read();
			if (t != 255) {
				buffer.write(t);
			}
		} while (t != 255);
		byte[] ret = buffer.toByteArray();
		return ret;
	}

	static void send(byte[] send, OutputStream out) throws Exception {
		try {
			out.write(send);
			out.write(-1);
		} catch (Exception e) {
			throw e;
		}
	}

}
