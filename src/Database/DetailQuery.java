package Database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DetailQuery {
	private DatabaseConnection connection;
	private String descriptionText;

	public DetailQuery(DatabaseConnection connection) {
		this.connection = connection;
	}

	public void getOrdersText(int orderNumber) {
		connection = new DatabaseConnection();
		connection.connectDatabase();
		try {
			ResultSet resultSet = connection.statement.executeQuery(
					"SELECT auftrag.beschreibung FROM auftrag WHERE auftrag.auftrags_nr = " + orderNumber);

			while (resultSet.next()) {
				descriptionText = resultSet.getString("beschreibung");

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getValue() {
		return descriptionText;
	}
}
