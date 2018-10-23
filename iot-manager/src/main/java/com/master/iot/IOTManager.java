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

import com.master.iot.transport.CommandException;

public class IOTManager implements Runnable {

	private static final IOTManager INSTANCE = new IOTManager();

	public static IOTManager getInstance() {
		if (!IOTManager.INSTANCE.isRunning()) {
			IOTManager.INSTANCE.start();
		}
		return IOTManager.INSTANCE;
	}

	public static void main(final String[] args) {
		IOTManager.getInstance();
	}

	private boolean running = false;

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

	public boolean isRunning() {
		return this.running;
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
				this.startControllers();
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void remove(final String key) {
		this.controllers.remove(key);
	}

	@Override
	public void run() {
		ServerSocket sc = null;
		try {
			this.running = true;
			sc = new ServerSocket(2000);
			while (true) {
				try {
					System.out.println("Aguardando");
					final Socket socket = sc.accept();
					final String address = this.getAddress(socket.getInetAddress().toString());
					socket.close();
					IOTController controller = this.controllers.get(address);
					if (controller == null) {
						controller = new IOTController(address);
					}
					final IOTController controllerThread = controller;
					new Thread() {
						@Override
						public void run() {
							try {
								String id = controllerThread.id();
								IOTManager.this.controllers.put(id, controllerThread);
							} catch (CommandException e) {
								e.printStackTrace();
							}
						};
					}.start();
					this.save();
				} catch (final Exception e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
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

	public void start() {
		new Thread(this).start();
	}

	private void startControllers() throws Exception {
		Collection<IOTController> controllers = this.controllers.values();
		for (IOTController controller : controllers) {
			controller.start();
		}
	}

}
