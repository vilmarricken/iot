package com.master.iot;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class IOTController extends IOTTransport {

	private enum COMMAND {
		CHANGE_NAME, INFO, STATE, ON, OFF
	}

	private String id;

	private IOTCompenent components[];

	private Socket socket;

	private InputStream in;

	private OutputStream out;

	private IOTManager iotManager;

	public IOTController(Socket socket, IOTManager iotManager) throws Exception {
		this.socket = socket;
		this.in = this.socket.getInputStream();
		this.out = this.socket.getOutputStream();
		this.iotManager = iotManager;
		start();
	}

	public IOTCompenent[] getComponents() {
		return components;
	}

	private void stateStart(byte[] state) throws Exception {
		this.components = new IOTCompenent[state.length];
		for (int i = 0; i < state.length; i++) {
			this.components[i] = new IOTCompenent("Component " + i, state[i] == 1, i);
		}
	}

	public void state(byte[] state) throws Exception {
		this.components = new IOTCompenent[state.length];
		for (int i = 0; i < state.length; i++) {
			this.components[i].setOn(state[i] == 1);
		}
	}

	private byte[] checkState() throws Exception {
		return transport(new byte[] { 2 }, this.out, this.in);
	}

	public void state() throws Exception {
		state(checkState());
	}

	private void start() throws Exception {
		this.info();
		this.iotManager.addComponent(this);
		stateStart(checkState());
	}

	public void info() throws Exception {
		String id = getControllerName(this.out, this.in);
		if (id == null) {
			this.id = this.iotManager.newID();
			this.setControllerName(this.id, out, in);
			this.iotManager.addComponent(this);
		} else {
			this.id = id;
		}
	}

	public void changeControllerName(String id) throws Exception {
		setControllerName(id, this.out, this.in);
		String currentId = this.id;
		this.id = id;
		this.iotManager.changeId(currentId, id);
	}

	public void on(byte port) throws Exception {
		transport(new byte[] { 3, port }, out, in);
		this.components[port].setOn(true);
	}

	public void off(byte port) throws Exception {
		transport(new byte[] { 4, port }, out, in);
		this.components[port].setOn(false);
	}

	@Override
	public int hashCode() {
		return this.id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj.getClass().equals(this.getClass())) {
			return this.id != null && this.id.equals(((IOTController) obj).id);
		}
		return false;
	}

	public String getId() {
		return this.id;
	}

	public void close() {
		try {
			if (this.socket != null) {
				this.socket.close();
			}
			this.iotManager.remove(this.id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setId(String id) {
		this.id = id;
	}

}
