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

	private static final IOTManager INSTANCE = new IOTManager();

	public static IOTManager getInstance() {
		return IOTManager.INSTANCE;
	}

	public static void main(final String[] args) {
		IOTManager.getInstance().run();
	}

	private Map<String, IOTController> controllers = new HashMap<>();

	private IOTManager() {
		this.read();
	}

	public void changeControllerName(final String currentId, final String newId) throws Exception {
		this.get(currentId).changeControllerName(newId);
	}

	void changeId(final String currentID, final String newId) {
		final IOTController component = this.controllers.get(currentID);
		this.controllers.put(newId, component);
	}

	private IOTController get(final String id) {
		return this.controllers.get(id);
	}

	private String getAddress(final String address) {
		System.out.println("Client address: " + address);
		if (address.startsWith("/")) {
			return address.substring(1);
		}
		return address;
	}

	private File getFile() {
		return new File("iot.bin");
	}

	public void list() {
		final Set<Entry<String, IOTController>> values = this.controllers.entrySet();
		for (final Entry<String, IOTController> entry : values) {
			final IOTController c = entry.getValue();
			System.out.println(c.getId() + " - " + c.getName() + " - " + (c.isOpen() ? "Open" : "Closed"));
			final IOTCompenent[] cs = c.getComponents();
			for (final IOTCompenent iotCompenent : cs) {
				System.out.println(iotCompenent.getIndex() + " - " + iotCompenent.getName() + " - " + iotCompenent.isOn());
			}
		}

	}

	public void off(final String id, final byte port) throws Exception {
		this.get(id).off(port);
	}

	public void on(final String id, final byte port) throws Exception {
		this.get(id).on(port);
	}

	@SuppressWarnings("unchecked")
	private void read() {
		final File file = this.getFile();
		if (file.exists()) {
			try {
				final FileInputStream in = new FileInputStream(file);
				final ObjectInputStream o = new ObjectInputStream(in);
				this.controllers = (Map<String, IOTController>) o.readObject();
				o.close();
				in.close();
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void remove(final String key) {
		this.controllers.remove(key);
	}

	private void run() {
		ServerSocket sc = null;
		try {
			try {
				sc = new ServerSocket(800);
			} catch (final IOException e) {
				e.printStackTrace();
			}
			while (true) {
				try {
					System.out.println("Aguardando");
					final Socket socket = sc.accept();
					final String key = this.getAddress(socket.getInetAddress().toString());
					socket.close();
					IOTController controller = this.controllers.get(key);
					if (controller == null) {
						controller = new IOTController(key);
						this.controllers.put(key, controller);
					}
					controller.connect();
				} catch (final Exception e) {
					e.printStackTrace();
				}
			}
		} finally {
			if (sc != null) {
				try {
					sc.close();
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void save() {
		try {
			final FileOutputStream out = new FileOutputStream(this.getFile());
			final ObjectOutputStream o = new ObjectOutputStream(out);
			o.writeObject(this.controllers);
			o.flush();
			o.close();
			out.flush();
			out.close();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

}
