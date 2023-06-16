package utility;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiServer {
	
	private static final int PORT = 8080;

	public static void main(String[] args) {
		new MultiServer(PORT);
	}

	public MultiServer(int port) {
		run();
	}

	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket(PORT);
			System.out.println("Server listening on port " + PORT);
			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("Client conected: " + socket.getInetAddress().getHostAddress());
				ServerOneClient oneClient = new ServerOneClient(socket);
				oneClient.start();
			}
		} catch (IOException e) {
			System.out.println("Error starting the server: " + e.getMessage());
		}
	}
}
