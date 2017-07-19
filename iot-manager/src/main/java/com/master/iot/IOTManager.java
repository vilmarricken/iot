package com.master.iot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class IOTManager implements Runnable {

	private static final IOTManager INSTANCE = new IOTManager();

	private boolean running = false;

	public static IOTManager getInstance() {
		if (!INSTANCE.isRunning()) {
			INSTANCE.start();
		}
		return IOTManager.INSTANCE;
	}

	public static void main(final String[] args) {
		IOTManager.getInstance();
	}

	private Map<String, IOTController> controllers = new HashMap<>();

	private IOTManager() {
		this.read();
	}

	public void changeControllerName(final String id, final String name) throws Exception {
		this.get(id).setName(name);
	}

	public IOTController get(final String id) {
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

	public Collection<IOTController> list() {
		return this.controllers.values();
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
				startControllers();
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void startControllers() throws Exception {
		Collection<IOTController> controllers = this.controllers.values();
		for (IOTController controller : controllers) {
			controller.start();
		}
	}

	public void remove(final String key) {
		this.controllers.remove(key);
	}

	public void start(){
		new Thread(this).start();
	}
	
	public void run() {
		ServerSocket sc = null;
		try {
			try {
				this.running = true;
				sc = new ServerSocket(1000);
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
					controller.start();
					save();
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

	public boolean isRunning() {
		return running;
	}

}
