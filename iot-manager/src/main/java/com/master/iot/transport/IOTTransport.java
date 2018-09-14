package com.master.iot.transport;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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

	public void close() {
	}

	public boolean isOpen() {
		return this.onLine;
	}

	public IOTCommand transport(final IOTCommand command) throws CommandException {
		final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		final byte[] send = command.getCommand();
		Socket socket = null;
		InputStream in = null;
		OutputStream out = null;
		try {
			socket = new Socket(this.address, this.port);
			in = socket.getInputStream();
			out = socket.getOutputStream();
			System.out.println(Arrays.toString(send));
			out.write((byte) send.length);
			out.write(send);
			int t = 0;
			t = in.read();
			byte status = (byte) in.read();
			for (int i = 0; i < t; i++) {
				buffer.write((byte) t);
			}
			final byte[] response = buffer.toByteArray();
			System.out.println(Arrays.toString(response));
			buffer.reset();
			this.onLine = true;
			command.setStatus(status == (byte) 1 ? IOTCommand.COMMAND_OK : IOTCommand.COMMAND_ERROR);
			command.setResponse(response);
			return command;
		} catch (final Exception e) {
			command.setStatus(IOTCommand.COMMAND_FAIL);
			this.onLine = false;
			throw new CommandException(e);
		} finally {
			this.close();
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
