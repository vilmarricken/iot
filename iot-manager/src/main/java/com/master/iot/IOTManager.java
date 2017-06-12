package com.master.iot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class IOTManager {

	private Map<String, IOTController> controllers = new HashMap<>();

	private static final IOTManager INSTANCE = new IOTManager();

	public static void main(String[] args) {
		IOTManager.getInstance().run();
	}

	private IOTManager() {
		read();
	}

	public static IOTManager getInstance() {
		return INSTANCE;
	}

	private void run() {
		ServerSocket sc = null;
		try {
			try {
				sc = new ServerSocket(1000);
			} catch (IOException e) {
				e.printStackTrace();
			}
			while (true) {
				try {
					Socket socket = sc.accept();
					String key = socket.getInetAddress().toString();
					IOTController controller = this.controllers.get(key);
					if (controller == null) {
						controller = new IOTController(key);
						this.controllers.put(key, controller);
					}
					controller.connect(socket);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} finally {
			if (sc != null) {
				try {
					sc.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private File getFile() {
		return new File("iot.bin");
	}

	@SuppressWarnings("unchecked")
	private void read() {
		File file = getFile();
		if (file.exists()) {
			try {
				FileInputStream in = new FileInputStream(file);
				ObjectInputStream o = new ObjectInputStream(in);
				this.controllers = (Map<String, IOTController>) o.readObject();
				o.close();
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void save() {
		try {
			FileOutputStream out = new FileOutputStream(getFile());
			ObjectOutputStream o = new ObjectOutputStream(out);
			o.writeObject(this.controllers);
			o.flush();
			o.close();
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void changeId(String currentID, String newId) {
		IOTController component = controllers.get(currentID);
		controllers.put(newId, component);
	}

	public void changeControllerName(String currentId, String newId) throws Exception {
		this.get(currentId).changeControllerName(newId);
	}

	private IOTController get(String id) {
		return this.controllers.get(id);
	}

	public void on(String id, byte port) throws Exception {
		this.get(id).on(port);
	}

	public void off(String id, byte port) throws Exception {
		this.get(id).off(port);
	}

	public void remove(String key) {
		this.controllers.remove(key);
	}

	public void list() {
		Set<Entry<String, IOTController>> values = controllers.entrySet();
		for (Entry<String, IOTController> entry : values) {
			IOTController c = entry.getValue();
			System.out.println(c.getId() + " - " + c.getName() + " - " + (c.isOpen() ? "Open" : "Closed"));
			IOTCompenent[] cs = c.getComponents();
			for (IOTCompenent iotCompenent : cs) {
				System.out.println(
						iotCompenent.getIndex() + " - " + iotCompenent.getName() + " - " + iotCompenent.isOn());
			}
		}

	}

}
