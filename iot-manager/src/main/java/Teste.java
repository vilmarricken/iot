import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Teste {

	private static int deviceState[] = { 0, 0, 1, 0, 0, 0, 0, 0 };

	public static void main(final String[] args) throws Exception {
		// final ServerSocket sc = new ServerSocket(800);
		// final Socket s = sc.accept();
		// final InputStream in = s.getInputStream();
		// final OutputStream out = s.getOutputStream();
		int t = 0;
		// while (true) {
		Socket s = new Socket("127.0.0.1", 1000);
		s.close();
		final ServerSocket sc = new ServerSocket(1001);
		while (true) {
			s = sc.accept();
			final InputStream in = s.getInputStream();
			final OutputStream out = s.getOutputStream();
			int op = in.read();
			switch (op) {
			case 49:
				for (int x : deviceState) {
					out.write(x);
				}
				out.write('\r');
				break;
			case 50:
				int p1 = in.read();
				deviceState[p1 - 48] = 1;
				out.write('0');
				out.write('\r');
				break;
			case 51:
				int p2 = in.read();
				deviceState[p2 - 48] = 0;
				out.write('0');
				out.write('\r');
				break;
			default:
				break;
			}
		}
	}

	public Teste() {
	}

}
