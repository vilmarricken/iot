import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Teste {

	public static void main(final String[] args) throws Exception {
		//final ServerSocket sc = new ServerSocket(800);
		//final Socket s = sc.accept();
		//final InputStream in = s.getInputStream();
		//final OutputStream out = s.getOutputStream();
		int t = 0;
		//while (true) {
			Socket s = new Socket("192.168.25.7",1000);
			final InputStream in = s.getInputStream();
			final OutputStream out = s.getOutputStream();
			final String msg = "Servidor para cliente: " + t++ + "\r";
			System.out.println(msg);
			out.write((msg).getBytes());
			final ByteArrayOutputStream stream = new ByteArrayOutputStream(512);
			int r = 0;
			while ((r = in.read()) != '\r') {
				System.out.println(r);
				stream.write(r);
			}
			System.out.println("------------------");
			System.out.println(new String(stream.toByteArray()));
			System.out.println("------------------");
		//}
	}

	public Teste() {
	}

}
