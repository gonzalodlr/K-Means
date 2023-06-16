package utility;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;

import Agent.DbAccess;
import Exceptions.DatabaseConnectionException;
import Exceptions.EmptySetException;
import Exceptions.NoValueException;
import Exceptions.OutOfRangeSampleSize;
import Exceptions.ServerException;
import data.Data;
import keyboardinput.Keyboard;
import mining.KMeansMiner;

public class ServerOneClient extends Thread {
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private KMeansMiner kmeans;

	public ServerOneClient(Socket socket) throws IOException {
		this.socket = socket;
		this.in = new ObjectInputStream(socket.getInputStream());
		this.out = new ObjectOutputStream(socket.getOutputStream());
		// this.kmeans = new KMeansMiner();
		// this.kmeans = kmeans;
	}

	@Override
	public void run() {
		// Tiene que manejar todo lo que el cliente pide?
		// Ejemplo metodo: learningFromFile, MainTest,
		// El servidor recibe tabName y k: Tiene que responder diciendo:OK y despues
		// pasando todo el Kmeans Data?
		try {
			while (true) {
				int option = (int) in.readObject();
				switch (option) {
				case 1:
					discoverClusters();
					break;
				case 2:
					storeTableFromDb();
					break;
				default:
					System.out.println("Invalid request");
				}
			}

		} catch (IOException e) {
			System.out.println("Error: " + e);
		} catch (ClassNotFoundException e) {
			System.out.println("Error: " + e);
		} catch (SQLException e) {
			System.out.println("Error: " + e);
		} catch (NoValueException e) {
			System.out.println("Error: " + e);
		} catch (EmptySetException e) {
			System.out.println("Error: " + e);
		} catch (DatabaseConnectionException e) {
			System.out.println("Error: " + e);
		} catch (OutOfRangeSampleSize e) {
			System.out.println("Error: " + e);
		} finally {
			// In any case, closing resources
			try {
				in.close();
				out.close();
				socket.close();
			} catch (IOException e) {
				System.out.println("Error closing client connection: " + e.getMessage());
			}
		}
	}

	private void storeTableFromDb() throws IOException, ClassNotFoundException {
		String tableName = (String) in.readObject();

		out.writeObject("OK");
	}

	private void discoverClusters() throws SQLException, OutOfRangeSampleSize, IOException, ClassNotFoundException,
			NoValueException, EmptySetException, DatabaseConnectionException {

		DbAccess db = new DbAccess();
		db.initConnection();
		String text = "";

		// I obtain the table name and search in db, also the interactions
		String tableName = (String) in.readObject();
		int k = (int) in.readObject();

		Data data = new Data(tableName, db);
		text = "" + data + "\n\n";

		kmeans = new KMeansMiner(k);
		int numIter = kmeans.kmeans(data);

		db.closeConnection();

		text += "Numero di Iterazione:" + numIter + "\n\n" + kmeans.getC().toString(data) + "\n";

		// All this ok, send message
		out.writeObject("OK");
		out.writeObject(text);
	}
}