package utility;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

import Exceptions.ServerException;

import keyboardinput.Keyboard;

public class MainTest {

	/**
	 * @param args
	 */
	private ObjectOutputStream out;
	private ObjectInputStream in; // stream con richieste del client

	public MainTest(String ip, int port) throws IOException {
		InetAddress addr = InetAddress.getByName(ip); // ip
		System.out.println("addr = " + addr);
		Socket socket = new Socket(addr, port); // Port
		System.out.println(socket);

		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
		; // stream con richieste del client
	}

	private int menu() {
		int answer;
		System.out.println("Choose option");
		do {
			System.out.println("(1) Upload Clusters from file");
			System.out.println("(2) Upload Data");
			System.out.print("Reply:");
			answer = Keyboard.readInt();
		} while (answer <= 0 || answer > 2);
		return answer;

	}

	private String learningFromFile() throws SocketException, ServerException, IOException, ClassNotFoundException {
		out.writeObject(1);

		System.out.print("Table Name:");
		String tabName = Keyboard.readString();
		out.writeObject(tabName);
		System.out.print("Number of iterations:");
		int k = Keyboard.readInt();
		out.writeObject(k);
		String result = (String) in.readObject();
		if (result.equals("OK")) {
			return (String) in.readObject();
		} else {
			throw new ServerException(result);
		}
	}

	private void storeTableFromDb() throws SocketException, ServerException, IOException, ClassNotFoundException {
		out.writeObject(2);
		System.out.print("Table Name:");
		String tabName = Keyboard.readString();
		out.writeObject(tabName);
		String result = (String) in.readObject();
		if (!result.equals("OK")) {
			throw new ServerException(result);
		}
	}

	private String learningFromDbTable() throws SocketException, ServerException, IOException, ClassNotFoundException {
		out.writeObject(1);
		System.out.print("Number of cluster:");
		int k = Keyboard.readInt();
		out.writeObject(k);
		String result = (String) in.readObject();
		if (result.equals("OK")) {
			System.out.println("Clustering output:" + in.readObject());
			return (String) in.readObject();
		} else
			throw new ServerException(result);

	}
	// Bad method implementation for store Cluster in file you must send name file and clusters
	private void storeClusterInFile() throws SocketException, ServerException, IOException, ClassNotFoundException {
		out.writeObject(2);

		String result = (String) in.readObject();
		if (!result.equals("OK"))
			throw new ServerException(result);

	}

	public static void main(String[] args) {
		String ip = args[0];
		int port = new Integer(args[1]).intValue();
		MainTest main = null;
		try {
			main = new MainTest(ip, port);
		} catch (IOException e) {
			System.out.println(e);
			return;
		}

		do {
			int menuAnswer = main.menu();
			switch (menuAnswer) {
			case 1:
				try {
					String kmeans = main.learningFromFile();
					System.out.println(kmeans);
				} catch (SocketException e) {
					System.out.println(e);
					e.printStackTrace();
					return;
				} catch (FileNotFoundException e) {
					System.out.println(e);
					return;
				} catch (IOException e) {
					System.out.println(e);
					return;
				} catch (ClassNotFoundException e) {
					System.out.println(e);
					return;
				} catch (ServerException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2: // learning from db

				while (true) {
					try {
						main.storeTableFromDb();
						break; // exit while
					}

					catch (SocketException e) {
						System.out.println(e);
						return;
					} catch (FileNotFoundException e) {
						System.out.println(e);
						return;

					} catch (IOException e) {
						System.out.println(e);
						return;
					} catch (ClassNotFoundException e) {
						System.out.println(e);
						return;
					} catch (ServerException e) {
						System.out.println(e.getMessage());
					}
				} // end while [viene fuori dal while con un db (in alternativa il programma
					// termina)

				char answer = 'y';// itera per learning al variare di k
				do {
					try {
						String clusterSet = main.learningFromDbTable();
						System.out.println(clusterSet);

						main.storeClusterInFile();

					} catch (SocketException e) {
						System.out.println(e);
						return;
					} catch (FileNotFoundException e) {
						System.out.println(e);
						return;
					} catch (ClassNotFoundException e) {
						System.out.println(e);
						return;
					} catch (IOException e) {
						System.out.println(e);
						return;
					} catch (ServerException e) {
						System.out.println(e.getMessage());
					}
					System.out.print("Do you want to repete the execution(y/n)");
					answer = Keyboard.readChar();
				} while (answer == 'y');
				break; // fine case 2
			default:
				System.out.println("Invalid option");
			}

			System.out.print("Do you want to choose a new menu operation?(y/n)");
			if (Keyboard.readChar() != 'y')
				break;
		} while (true);
	}
}
