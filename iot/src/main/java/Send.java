import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.master.core.util.LogUtil;
import com.master.iot.connection.IOTConnection;

public class Send {

	private static final String COMMAND_EXECUTE = "3";
	private static final String COMMAND_REGISTRY = "1";
	private static final String COMMAND_UNREGISTRY = "2";

	private static final String DEVICE_RELAY = "1";
	private static final String DEVICE_THERMOMETER = "3";

	private static final int PORT = 800;

	private static final String SERVER = "192.168.25.10";

	public static void command(final String command) throws Exception {
		final Socket s = new Socket(Send.SERVER, Send.PORT);
		final OutputStream out = s.getOutputStream();
		System.out.println((byte) command.length());
		out.write((byte) command.length());
		out.write(command.getBytes());
		out.flush();
		final InputStream in = s.getInputStream();
		final int t = in.read();
		final ByteArrayOutputStream bout = new ByteArrayOutputStream(t);
		for (int i = 0; i < t; i++) {
			final char c = (char) in.read();
			bout.write(c);
		}
		System.out.println(new String(bout.toByteArray()));
		in.close();
		out.close();
		s.close();
		System.out.println("Fim");
	}

	public static void main(final String[] args) throws Exception {
		// Registra termometro na porta 2;
		// Thread.sleep(30000);
		// Send.command(Send.COMMAND_REGISTRY + ";4;" + Send.DEVICE_THERMOMETER);
		// Send.command(Send.COMMAND_EXECUTE + ";4");
		// Send.command(Send.COMMAND_REGISTRY + ";3;" + Send.DEVICE_RELAY);
		// Send.command(Send.COMMAND_EXECUTE + ";3;1");
		LogUtil.config();
		final IOTConnection conn = new IOTConnection();
		// Thread.sleep(2000);
		// conn.turnOn("192.168.25.9", "2");
		// Thread.sleep(2000);
		// conn.turnOff("192.168.25.9", "2");
		System.out.println(conn.read("192.168.25.9", "4"));
	}
}
