package GarageElements;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Database.DatabaseConnection;

public class OrdersToDatabaseInput {
	private static DatabaseConnection connection;

	public OrdersToDatabaseInput(DatabaseConnection connection) {
		this.connection = connection;
	}

	public void insertData(String kdNr, String mitarbeiterID, String beschreibung) {
		connection.connectDatabase();
		try {
			String query = "INSERT INTO auftrag (kd_nr, mitarbeiterID, beschreibung) VALUES (?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, kdNr);
			preparedStatement.setString(2, mitarbeiterID);
			preparedStatement.setString(3, beschreibung);

			preparedStatement.executeUpdate();
			System.out.println("erfolgreich eingetragen");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}