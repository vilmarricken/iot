package com.master.iot.server;

import java.util.List;

import org.apache.log4j.Logger;

import com.master.iot.connection.IOTConnection;
import com.master.iot.connection.IOTConnectionException;
import com.master.iot.entity.Board;
import com.master.iot.entity.Component;

public class RegistryComponent {

	private static final Logger log = Logger.getLogger(RegistryComponent.class);

	public void registryComponent(Component component) {
		final IOTConnection connection = new IOTConnection();
		final Board board = component.getBoard();
		try {
			connection.registry(board.getIp(), component.getPort().toString(), component.getType().getType());
		} catch (final IOTConnectionException e) {
			RegistryComponent.log.error(e.getMessage(), e);
		}
	}

	public void registryComponents(List<Component> components) {
		for (final Component component : components) {
			this.registryComponent(component);
		}
	}

}
