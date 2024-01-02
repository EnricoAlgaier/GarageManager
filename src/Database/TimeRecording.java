package Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import GuiElements.CTextField;

public class TimeRecording {

	private DatabaseConnection connectionData;
	private LocalDateTime localDateTime;
	private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
	private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:m");
	private CTextField employerIDField;

	private List<String> employeID;
	private boolean close;

	public TimeRecording(DatabaseConnection connectionData, CTextField employeIDField) {
		this.connectionData = connectionData;
		this.employerIDField = employeIDField;
		this.localDateTime = LocalDateTime.now();
		employeID = new ArrayList<>();
	}

	public void timeStampStart() {
		connectionData.connectDatabase();
		int employeId = Integer.parseInt(employeID.get(0));

		try {
			connectionData.connectDatabase();

			String checkQuery = "SELECT COUNT(*) FROM zeiterfassung WHERE mitarbeiterID = ? AND datum = ?";
			PreparedStatement checkStatement = connectionData.connection.prepareStatement(checkQuery);
			
			checkStatement.setInt(1, employeId);
			checkStatement.setDate(2, java.sql.Date.valueOf(localDateTime.toLocalDate()));

			ResultSet resultSet = checkStatement.executeQuery();
			resultSet.next();
			int rowCount = resultSet.getInt(1);

			if (rowCount > 0) {
				JOptionPane.showMessageDialog(null, "Personalnummer wurde heute bereits eingeloggt", "Fehler",
						JOptionPane.ERROR_MESSAGE);
				close = false;
				return;
			}

			String insertQuery = "INSERT INTO zeiterfassung (mitarbeiterID, startZeit, datum) VALUES (?, ?, ?)";
			PreparedStatement preparedStatement = connectionData.connection.prepareStatement(insertQuery);
			java.sql.Date sqlDate = java.sql.Date.valueOf(localDateTime.toLocalDate());

			try {
				preparedStatement.setInt(1, employeId);
				preparedStatement.setTime(2, java.sql.Time.valueOf(localDateTime.toLocalTime()));
				preparedStatement.setDate(3, sqlDate);
				preparedStatement.executeUpdate();

				close = true;

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Nicht alle Felder ausgefüllt", "Abbruch",
						JOptionPane.WARNING_MESSAGE);
				System.err.println("Ein Fehler ist aufgetreten: " + e.getMessage());
				close = false;
			}
		} catch (SQLException e) {
			close = false;
		}
	}

	public void timeStampStop() {
		connectionData.connectDatabase();
		int employeId = Integer.parseInt(employeID.get(0));

		try {
			connectionData.connectDatabase();

			String checkQuery = "SELECT COUNT(*) FROM zeiterfassung WHERE mitarbeiterID = ? AND datum = ? AND endZeit IS NULL";
			PreparedStatement checkStatement = connectionData.connection.prepareStatement(checkQuery);
			
			checkStatement.setInt(1, employeId);
			checkStatement.setDate(2, java.sql.Date.valueOf(localDateTime.toLocalDate()));

			ResultSet resultSet = checkStatement.executeQuery();
			resultSet.next();
			int rowCount = resultSet.getInt(1);

			if (rowCount == 0) {
				JOptionPane.showMessageDialog(null, "Personalnummer heute nicht eingeloggt oder bereits beendet",
						"Fehler", JOptionPane.ERROR_MESSAGE);
				close = false;
				return;
			}

			String updateQuery = "UPDATE zeiterfassung SET endZeit = ? WHERE mitarbeiterID = ? AND datum = ?";
			PreparedStatement preparedStatement = connectionData.connection.prepareStatement(updateQuery);
			java.sql.Date sqlDate = java.sql.Date.valueOf(localDateTime.toLocalDate());

			try {
				preparedStatement.setTime(1, java.sql.Time.valueOf(localDateTime.toLocalTime()));
				preparedStatement.setInt(2, employeId);
				preparedStatement.setDate(3, sqlDate);
				preparedStatement.executeUpdate();

				close = true;

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Nicht alle Felder ausgefüllt", "Abbruch",
						JOptionPane.WARNING_MESSAGE);
				System.err.println("Ein Fehler ist aufgetreten: " + e.getMessage());
				close = false;
			}
			
		} catch (SQLException e) {
			close = false;
		}
	}

	public void setEmployeId(int number) {
		employerIDField.textInput(number);
		employeID = new ArrayList<>();
		employeID = employerIDField.getText();
	}

	public boolean getClose() {
		return close;
	}
}