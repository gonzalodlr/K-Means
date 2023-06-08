package Agent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import Exceptions.DatabaseConnectionException;

public class DbAccess {

	private static String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
	private final String DBMS = "jdbc:mysql";
	private final String SERVER = "localhost";
	private final String DATABASE = "MapDB";
	private final int PORT = 3306;
	private final String USER_ID = "MapUser";
	private final String PASSWORD = "map";

	private Connection conn;
	
	public void initConnection() throws DatabaseConnectionException {
		try {
			Class.forName(DRIVER_CLASS_NAME);
			String url = DBMS + "://" + SERVER + ":" + PORT + "/" + DATABASE;
			conn = DriverManager.getConnection(url, USER_ID, PASSWORD);
		} catch (ClassNotFoundException e) {
			throw new DatabaseConnectionException("Driver class not found: " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("[!] SQLException: " + e.getMessage());
			System.out.println("[!] SQLState: " + e.getSQLState());
			System.out.println("[!] VendorError: " + e.getErrorCode());
			throw new DatabaseConnectionException("Failed to connect to database: " + e.getMessage());
		}
	}

	public Connection getConnection() throws SQLException {
		return conn;
	}

	public void closeConnection() throws SQLException {
		conn.close();
	}
}