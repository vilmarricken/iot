package com.master.iot;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class IOTManager {

	private int code = 1;

	private Map<String, IOTController> components = new HashMap<>();

	private static final IOTManager INSTANCE = new IOTManager();

	public static void main(String[] args) {
		IOTManager.getInstance().run();
	}

	public static IOTManager getInstance() {
		return INSTANCE;
	}

	private void run() {
		ServerSocket sc = null;
		try {
			sc = new ServerSocket(1000);
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (true) {
			try {
				new IOTController(sc.accept(), this);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	void changeId(String currentID, String newId) {
		IOTController component = components.get(currentID);
		components.put(newId, component);
	}

	public void changeControllerName(String currentId, String newId) throws Exception {
		this.get(currentId).changeControllerName(newId);
	}

	private IOTController get(String id) {
		return this.components.get(id);
	}

	public void on(String id, byte port) throws Exception {
		this.get(id).on(port);
	}

	public void off(String id, byte port) throws Exception {
		this.get(id).off(port);
	}

	void addComponent(IOTController component) {
		components.put(component.getId(), component);
	}

	String newID() {
		return "Controller " + code++;
	}

	public void remove(String key) {
		this.components.remove(key);
	}

	public void list() {
		Set<Entry<String, IOTController>> values = components.entrySet();
		for (Entry<String, IOTController> entry : values) {
			System.out.println(entry.getKey());
			IOTController c = entry.getValue();
			IOTCompenent[] cs = c.getComponents();
			for (IOTCompenent iotCompenent : cs) {
				System.out.println(
						iotCompenent.getIndex() + " - " + iotCompenent.getName() + " - " + iotCompenent.isOn());
			}
		}

	}

}
