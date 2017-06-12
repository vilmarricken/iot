package com.master.iot;

import java.io.Serializable;
import java.net.Socket;

public class IOTController implements Serializable {

	private static final long serialVersionUID = -1057493262219216697L;

	public enum COMMAND {
		INFO, STATE, ON, OFF
	}

	private final String id;
	
	private String name;

	private IOTCompenent components[];

	private transient IOTTransport transport;

	public IOTController(String id) {
		this.id = id;
	}

	public void connect(Socket socket) throws Exception {
		this.transport = new IOTTransport(socket);
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
		return this.transport.transport(new byte[] { 2 });
	}

	public void state() throws Exception {
		state(checkState());
	}

	private void start() throws Exception {
		stateStart(checkState());
	}

	public void changeControllerName(String name) throws Exception {
		this.name = name;
	}

	public void on(byte port) throws Exception {
		this.transport.transport(new byte[] { 3, port });
		this.components[port].setOn(true);
	}

	public void off(byte port) throws Exception {
		this.transport.transport(new byte[] { 4, port });
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
	
	public String getName() {
		return name;
	}

	public void close() {
		try {
			this.transport.close();
			this.transport = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isOpen() {
		return this.transport != null && this.transport.isOpen();
	}

}
