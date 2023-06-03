package main;

import java.sql.SQLException;

import Agent.DbAccess;
import Exceptions.DatabaseConnectionException;
import Exceptions.OutOfRangeSampleSize;
import data.Data;
import keyboardinput.Keyboard;
import mining.KMeansMiner;

public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		char repeat = 'n';
		try {
			do {
				DbAccess db = new DbAccess();
				db.initConnection();

				Data data = new Data("playtennis", db);
				Keyboard keyboard = new Keyboard();
				System.out.println(data);
				int k;
				int numIter = 0;
				KMeansMiner kmeans = null;

				try {
					k = keyboard.readInt();
					kmeans = new KMeansMiner(k);
					numIter = kmeans.kmeans(data);
				} catch (OutOfRangeSampleSize e) {
					e.printStackTrace(System.err);
				}
				System.out.println("Numero di Iterazione:" + numIter);
				System.out.println(kmeans.getC().toString(data));

				System.out.println("\n\nDo you want to repete the execution? y/n");
				repeat = keyboard.readChar();

			} while (repeat == 'y');
		} catch (DatabaseConnectionException ex) {
			System.out.println(ex.getMessage());
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

}
