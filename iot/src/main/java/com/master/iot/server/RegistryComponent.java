package com.master.iot.server;

import java.util.List;

import org.apache.log4j.Logger;

import com.master.iot.entity.Board;
import com.master.iot.entity.Component;

public class RegistryComponent {

	private static final Logger log = Logger.getLogger(RegistryComponent.class);

	public void registryComponent(Component component) {
		IOTConnection connection = new IOTConnection();
		Board board = component.getBoard();
		try {
			connection.registry(board.getIp(), component.getPort().toString(), component.getType().getType());
		} catch (IOTConnectionException e) {
			log.error(e.getMessage(), e);
		}
	}

	public void registryComponents(List<Component> components) {
		for (Component component : components) {
			this.registryComponent(component);
		}
	}

}
