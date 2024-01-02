package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DatabaseConnection {
	public Statement statement;
	public Connection connection;
	

	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/werkstatt"; 
	private static final String USERNAME = "Enrico"; 
	private static final String PASSWORD = "123456";

	public void connectDatabase() {
		try {
			if (connection == null) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
				statement = connection.createStatement();
				System.out.println("Connection successful");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No connection to the Server", "Attention", JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}
	}
	
	public void closeConnection() {
		try {
			connection.close();
			System.out.println("Connection successfully terminated");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	public PreparedStatement prepareStatement(String query) {
		return null;
	}
}