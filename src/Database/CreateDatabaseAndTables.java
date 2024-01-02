package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDatabaseAndTables {

	public void createDatabaseAndTables() {
		String jdbcUrl = "jdbc:mysql://localhost:3306/";
		String username = "Enrico";
		String password = "123456";

		String databaseName = "werkstatt";
		String[] tableNames = { "kunden", "anschrift", "mitarbeiter", "mitarbeiter_anschrift", "repStatus", "lager",
				"zeiterfassung", "hebebühne", "kundenFahrzeug", "auftrag" };

		try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
				Statement statement = connection.createStatement()) {

			if (databaseExists(statement, databaseName)) {
				System.out.println("Datenbank existiert bereits.");

				if (tablesExist(statement, tableNames)) {
					System.out.println("Tabellen existieren bereits.");
				} else {
					statement.executeUpdate("USE " + databaseName);
					for (String tableName : tableNames) {
						createTable(statement, tableName);
					}
					System.out.println("Tabellen erfolgreich erstellt.");
				}
			} else {
				statement.executeUpdate("CREATE DATABASE " + databaseName);
				statement.executeUpdate("USE " + databaseName);

				for (String tableName : tableNames) {
					createTable(statement, tableName);
				}
				System.out.println("Datenbank und Tabellen erfolgreich erstellt.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void createTable(Statement statement, String tableName) throws SQLException {
		switch (tableName) {

		case "kunden":
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS kunden (" + "kd_nr INT AUTO_INCREMENT PRIMARY KEY,"
					+ "vorname VARCHAR(50)," + "nachname VARCHAR(50)," + "telefonnummer INTEGER)");
			break;

		case "anschrift":
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS anschrift (" + "plz INTEGER," + "ort VARCHAR(50),"
					+ "straße VARCHAR(50)," + "hausnummer VARCHAR(20)," + "kd_nr INT AUTO_INCREMENT PRIMARY KEY,"
					+ "FOREIGN KEY (kd_nr) REFERENCES kunden(kd_nr))");
			break;

		case "mitarbeiter":
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS mitarbeiter (" + "mitarbeiterID INTEGER PRIMARY KEY,"
					+ "vorname VARCHAR(50)," + "nachname VARCHAR(50)," + "telefonnummer VARCHAR(50))");
			break;

		case "mitarbeiter_anschrift":
			statement.executeUpdate(
					"CREATE TABLE IF NOT EXISTS mitarbeiter_anschrift (" + "plz INTEGER," + "ort VARCHAR(50),"
							+ "straße VARCHAR(50)," + "hausnummer VARCHAR(20)," + "mitarbeiterID INT PRIMARY KEY,"
							+ "FOREIGN KEY (mitarbeiterID) REFERENCES mitarbeiter(mitarbeiterID))");
			break;

		case "repStatus":
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS repStatus ("
					+ "mitarbeiterID INTEGER REFERENCES mitarbeiter(mitarbeiterID)," + "status VARCHAR(25),"
					+ "kd_nr INT," + "auftrags_nr INTEGER,"
					+ "FOREIGN KEY (mitarbeiterID) REFERENCES mitarbeiter(mitarbeiterID),"
					+ "FOREIGN KEY (kd_nr) REFERENCES kunden(kd_nr))");
			break;

		case "lager":
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS lager (" + "teileID INTEGER PRIMARY KEY,"
					+ "teil VARCHAR(50)," + "bestand INT," + "preis FLOAT)");
			break;

		case "zeiterfassung":
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS zeiterfassung ("
					+ "mitarbeiterID INTEGER REFERENCES mitarbeiter(mitarbeiterID)," + "startZeit TIME,"
					+ "endZeit TIME," + "datum DATE," + "gesamt VARCHAR(50))");
			break;

		case "hebebühne":
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS hebebühne ("
					+ "mitarbeiterID INTEGER REFERENCES mitarbeiter(mitarbeiterID),"
					+ "hebebühneID INTEGER AUTO_INCREMENT PRIMARY KEY," + "status VARCHAR(20))");
			break;

		case "kundenFahrzeug":
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS kundenFahrzeug (" + "fahrzeug VARCHAR(50),"
					+ "tüv VARCHAR(10)," + "km_stand VARCHAR(10)," + "kennzeichen VARCHAR(10),"
					+ "kd_nr INT AUTO_INCREMENT PRIMARY KEY," + "FOREIGN KEY (kd_nr) REFERENCES kunden(kd_nr))");
			break;

		case "auftrag":
			statement.executeUpdate(
					"CREATE TABLE IF NOT EXISTS auftrag (" + "Auftrags_NR INT AUTO_INCREMENT PRIMARY KEY,"
							+ "mitarbeiterID INTEGER," + "kd_nr INTEGER," + "beschreibung TEXT(1000),"
							+ "start_datum DATE," + "start_uhrzeit TIME," + "end_datum DATE," + "end_uhrzeit TIME)");
			break;

		default:
			System.out.println("Ungültiger Tabellenname: " + tableName);
		}
	}

	private boolean databaseExists(Statement statement, String databaseName) throws SQLException {
		ResultSet resultSet = statement.executeQuery("SHOW DATABASES LIKE '" + databaseName + "'");
		return resultSet.next();
	}

	private boolean tablesExist(Statement statement, String[] tableNames) throws SQLException {
		for (String tableName : tableNames) {
			ResultSet resultSet = statement.executeQuery("SHOW TABLES FROM werkstatt LIKE '" + tableName + "'");
			if (!resultSet.next()) {
				return false;
			}
		}
		return true;
	}
}