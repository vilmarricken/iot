package com.master.iot;

import java.io.Serializable;

import com.master.iot.command.IOTCommand;
import com.master.iot.command.IOTCommandAction;
import com.master.iot.command.IOTCommandCheck;
import com.master.iot.command.IOTCommandID;
import com.master.iot.transport.CommandException;
import com.master.iot.transport.IOTTransport;

public class IOTController implements Serializable {

	private static final long serialVersionUID = -1057493262219216697L;

	private IOTCompenent components[];

	private final String id;

	private String name;

	private transient IOTTransport transport;

	public IOTController(final String id) {
		this.id = id;
		this.name = id;
	}

	private byte[] checkState() throws Exception {
		return this.getTransport().transport(new IOTCommandCheck()).getCommand();
	}

	public void close() {
		try {
			this.transport.close();
			this.transport = null;
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	private void connect() throws CommandException {
		this.transport = new IOTTransport(this.id, 1001);
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj != null && obj.getClass().equals(this.getClass())) {
			return this.id != null && this.id.equals(((IOTController) obj).id);
		}
		return false;
	}

	public IOTCompenent[] getComponents() {
		return this.components;
	}

	public String getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	private IOTTransport getTransport() throws CommandException {
		if (this.transport == null || !this.transport.isOpen()) {
			this.connect();
		}
		return this.transport;
	}

	@Override
	public int hashCode() {
		return this.id.hashCode();
	}

	public String id() throws CommandException {
		IOTCommand command = new IOTCommandID();
		this.getTransport().transport(command);
		return new String(command.getResponse());
	}

	public boolean isOpen() {
		return this.transport != null && this.transport.isOpen();
	}

	public void off(final byte port) throws Exception {
		this.getTransport().transport(new IOTCommandAction(port, IOTCommand.ACTION_OFF));
		this.components[port].setOn(false);
	}

	public void on(final byte port) throws Exception {
		this.getTransport().transport(new IOTCommandAction(port, IOTCommand.ACTION_ON));
		this.components[port].setOn(true);
	}

	public void setName(String name) {
		this.name = name;
	}

	public void start() throws Exception {
		this.stateStart(this.checkState());
	}

	public void state() throws Exception {
		this.state(this.checkState());
	}

	public void state(final byte[] state) throws Exception {
		this.components = new IOTCompenent[state.length];
		for (int i = 0; i < state.length; i++) {
			this.components[i].setOn(state[i] == 1);
		}
	}

	private void stateStart(final byte[] state) throws Exception {
		this.components = new IOTCompenent[state.length];
		for (int i = 0; i < state.length; i++) {
			this.components[i] = new IOTCompenent("Component " + i, state[i] == 1, i);
		}

	}

}
