package com.master.iot;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class IOTTransport {

	byte[] transport(byte[] send, OutputStream out, InputStream in) throws Exception {
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		try {
			out.write(send);
			out.write(-1);
			int t = 0;
			do {
				t = in.read();
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

	void setControllerName(String id, OutputStream out, InputStream in) throws Exception {
		byte idBytes[] = id.getBytes();
		byte send[] = new byte[idBytes.length + 1];
		send[0] = 0;
		System.arraycopy(idBytes, 0, send, 1, idBytes.length);
		transport(send, out, in);
		String newName = getControllerName(out, in);
		if (newName == null) {
			throw new Exception("Erro ao trocar o nome do component");
		}
		if (!newName.equals(id)) {
			throw new Exception("Não foi possível trocar o nome do component");
		}
	}

	String getControllerName(OutputStream out, InputStream in) throws Exception {
		byte[] r = transport(new byte[] { (byte) 1 }, out, in);
		if (r.length == 0) {
			return null;
		}
		return new String(r, 0, r.length);
	}

	abstract void close();

}
