package EmployeElements;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Database.DatabaseConnection;

public class FillChangeFrame {

	private DatabaseConnection connection;
	private ChangeEmployeeFrame employeeChangeFrame;

	private List<String> employeeText;
	private List<String> addressText;
	private List<String> idText;

	public FillChangeFrame(DatabaseConnection connection, ChangeEmployeeFrame employeeChangeFrame) {
		this.connection = connection;
		this.employeeChangeFrame = employeeChangeFrame;

		employeeText = new ArrayList<>();
		addressText = new ArrayList<>();
		idText = new ArrayList<>();
	}

	public void getDatabaseValueToTextFields(int employeeID) {
		try {

			connection.connectDatabase();

			String employee = "SELECT mitarbeiter.mitarbeiterID , mitarbeiter.vorname, mitarbeiter.nachname, mitarbeiter.telefonnummer, mitarbeiter_anschrift.plz, mitarbeiter_anschrift.ort, "
					+ "mitarbeiter_anschrift.straße, mitarbeiter_anschrift.hausnummer " + "FROM mitarbeiter "
					+ "LEFT JOIN mitarbeiter_anschrift ON mitarbeiter.mitarbeiterID = mitarbeiter_anschrift.mitarbeiterID "
					+ "WHERE mitarbeiter.mitarbeiterID = " + employeeID;

			PreparedStatement preparedStatement = connection.connection.prepareStatement(employee);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					resultSet.getInt("mitarbeiterID");
					String vorname = resultSet.getString("vorname");
					String nachname = resultSet.getString("nachname");
					String telefonnummer = resultSet.getString("telefonnummer");

					String plz = resultSet.getString("plz");
					String ort = resultSet.getString("ort");
					String straße = resultSet.getString("straße");
					String hausnummer = resultSet.getString("hausnummer");

					employeeText.clear();
					employeeText.add(vorname);
					employeeText.add(nachname);
					employeeText.add(telefonnummer);

					addressText.clear();
					addressText.add(plz);
					addressText.add(ort);
					addressText.add(straße);
					addressText.add(hausnummer);
					
	                idText.add(String.valueOf(employeeID));

	                employeeChangeFrame = new ChangeEmployeeFrame(this, String.valueOf(employeeID));

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<String> getcustomerText() {
		return employeeText;
	}

	public List<String> getaddressText() {
		return addressText;
	}
	
	public List<String> getIdText(){
		return idText;
	}
}