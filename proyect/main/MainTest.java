package main;

import java.sql.SQLException;

import Agent.DbAccess;
import Exceptions.DatabaseConnectionException;
import Exceptions.EmptySetException;
import Exceptions.NoValueException;
import Exceptions.OutOfRangeSampleSize;
import data.Data;
import keyboardinput.Keyboard;
import mining.KMeansMiner;

public class MainTest {

	/**
	 * @param args
	 */
	static final String TABLE_NAME = "playtennis";
	
	public static void main(String[] args) {
		char repeat = 'n';
		try {
			do {
				DbAccess db = new DbAccess();
				db.initConnection();

				Data data = new Data(TABLE_NAME, db);
				System.out.println(data);
				int k;
				int numIter = 0;
				KMeansMiner kmeans = null;

				k = Keyboard.readInt();
				kmeans = new KMeansMiner(k);
				numIter = kmeans.kmeans(data);
				
				db.closeConnection();
				
				System.out.println("Numero di Iterazione:" + numIter);
				System.out.println(kmeans.getC().toString(data));
				
				System.out.println("\n\nDo you want to repete the execution? y/n");
				repeat = Keyboard.readChar();
				
			} while (repeat == 'y');

		} catch (EmptySetException ex) {
			System.out.println(ex.getMessage());
		} catch (NoValueException ex) {
			System.out.println(ex.getMessage());
		} catch (DatabaseConnectionException ex) {
			System.out.println(ex.getMessage());
		} catch (OutOfRangeSampleSize ex) {
			System.out.println(ex.getMessage());
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
