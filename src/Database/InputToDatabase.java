package Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.AbstractProcessor;
import javax.swing.JOptionPane;
import GuiElements.CTextField;

public class InputToDatabase {
	private DatabaseConnection connectionData;
	private CTextField nameText, addresstext, vehiclesText;
	private List<String> customerText;
	private List<String> addressText;
	private List<String> vehicleText;
	private boolean closeInput;

	public InputToDatabase(DatabaseConnection connectionData, CTextField nameText, CTextField addresstext,
			CTextField vehiclesText) {
		this.connectionData = connectionData;
		this.nameText = nameText;
		this.addresstext = addresstext;
		this.vehiclesText = vehiclesText;
	}

	public void insertData() {

		try {
			connectionData.connectDatabase();

			String insertQuery1 = "INSERT INTO kunden (vorname, nachname, telefonnummer) VALUES (?, ?, ?)";
			String insertQuery2 = "INSERT INTO anschrift (plz, ort, straße, hausnummer) VALUES (?, ?, ?, ?)";
			String insertQuery3 = "INSERT INTO kundenfahrzeug (fahrzeug, tüv, km_stand, kennzeichen) VALUES (?, ?, ?, ?)";

			PreparedStatement preparedStatement1 = connectionData.connection.prepareStatement(insertQuery1);
			PreparedStatement preparedStatement2 = connectionData.connection.prepareStatement(insertQuery2);
			PreparedStatement preparedStatement3 = connectionData.connection.prepareStatement(insertQuery3);

			try {
				if (isDuplicateCustomer(customerText.get(0), customerText.get(1), addressText.get(0),
						addressText.get(2), addressText.get(3))) {
					JOptionPane.showMessageDialog(null, "Kunde existiert bereits", "Abbruch",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				preparedStatement1.setString(1, customerText.get(0));
				preparedStatement1.setString(2, customerText.get(1));
				preparedStatement1.setString(3, customerText.get(2));
				preparedStatement1.executeUpdate();

				preparedStatement2.setString(1, addressText.get(0));
				preparedStatement2.setString(2, addressText.get(1));
				preparedStatement2.setString(3, addressText.get(2));
				preparedStatement2.setString(4, addressText.get(3));
				preparedStatement2.executeUpdate();

				preparedStatement3.setString(1, vehicleText.get(0));
				preparedStatement3.setString(2, vehicleText.get(1));
				preparedStatement3.setString(3, vehicleText.get(2));
				preparedStatement3.setString(4, vehicleText.get(3));
				preparedStatement3.executeUpdate();

				closeInput = true;
				connectionData.closeConnection();

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Nicht alle Felder ausgefüllt", "Abbruch",
						JOptionPane.WARNING_MESSAGE);
				System.err.println("Ein Fehler ist aufgetreten: " + e.getMessage());
			}
		} catch (SQLException e) {

		}
	}

	private boolean isDuplicateCustomer(String firstName, String lastName, String plz, String street,
			String houseNumber) throws SQLException {
		String query = "SELECT * FROM kunden " + "INNER JOIN anschrift ON kunden.kd_nr = anschrift.kd_nr "
				+ "WHERE kunden.vorname = ? AND kunden.nachname = ? AND anschrift.plz = ? AND anschrift.straße = ? AND anschrift.hausnummer = ?";

		try (PreparedStatement preparedStatement = connectionData.connection.prepareStatement(query)) {

			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			preparedStatement.setString(3, plz);
			preparedStatement.setString(4, street);
			preparedStatement.setString(5, houseNumber);

			ResultSet resultSet = preparedStatement.executeQuery();
			return resultSet.next();
		}
	}

	public void setNameTextInput(int nameSize) {
		nameText.textInput(nameSize);
		customerText = new ArrayList<>();
		customerText = nameText.getText();
	}

	public void setAddressTextInput(int addressSize) {
		addresstext.textInput(addressSize);
		addressText = new ArrayList<>();
		addressText = addresstext.getText();
	}

	public void setVehicleTextInput(int vehicleSize) {
		vehiclesText.textInput(vehicleSize);
		vehicleText = new ArrayList<>();
		vehicleText = vehiclesText.getText();
	}
	
	public boolean getCloseInput() {
		return closeInput;
	}

		
}