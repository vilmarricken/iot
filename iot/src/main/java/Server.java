import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(final String[] args) throws Exception {
		ServerSocket sc = null;
		try {
			sc = new ServerSocket(800);
			System.out.println("Started");
			final boolean run = true;
			while (run) {
				final Socket s = sc.accept();
				System.out.println(s.getInetAddress());
				final InputStream in = s.getInputStream();
				final int t = in.read();
				System.out.println("T: " + t);
				final char[] text = new char[t];
				for (int i = 0; i < t; i++) {
					final int c = in.read();
					text[i] = (char) c;
				}
				System.out.println(new String(text));
			}
		} finally {
			sc.close();
		}
	}

	public Server() {
	}

}
