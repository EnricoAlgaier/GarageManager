package Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import GuiElements.CTextField;

public class InputToDatabaseEmploye {
	private DatabaseConnection connectionData;
	private CTextField employeNameField, employeAddressField;
	private List<String> employeName;
	private List<String> employeAddress;
	private boolean closeInput;

	public InputToDatabaseEmploye(DatabaseConnection connectionData, CTextField employeNameField, CTextField employeAddressField) {
		this.connectionData = connectionData;
		this.employeNameField = employeNameField;
		this.employeAddressField = employeAddressField;
	}

	public void insertData() {
		
		try {
			connectionData.connectDatabase();

			String insertQuery1 = "INSERT INTO mitarbeiter (mitarbeiterID, vorname, nachname, telefonnummer) VALUES (?, ?, ?, ?)";
			String insertQuery2 = "INSERT INTO mitarbeiter_anschrift (mitarbeiterID, plz, ort, straße, hausnummer) VALUES (?, ?, ?, ?, ?)";

			PreparedStatement preparedStatement1 = connectionData.connection.prepareStatement(insertQuery1);
			PreparedStatement preparedStatement2 = connectionData.connection.prepareStatement(insertQuery2);

			try {
				if (isDuplicateCustomer(employeName.get(0))) {
					JOptionPane.showMessageDialog(null, "Personalnummer bereits vergeben", "Abbruch",
									JOptionPane.WARNING_MESSAGE);
					return;
				}
				int employeID = Integer.parseInt(employeName.get(0));
				
				preparedStatement1.setInt(1, employeID);
				preparedStatement1.setString(2, employeName.get(1));
				preparedStatement1.setString(3, employeName.get(2));
				preparedStatement1.setString(4, employeName.get(3));
				preparedStatement1.executeUpdate();

				preparedStatement2.setInt(1, employeID);
				preparedStatement2.setString(2, employeAddress.get(0));
				preparedStatement2.setString(3, employeAddress.get(1));
				preparedStatement2.setString(4, employeAddress.get(2));
				preparedStatement2.setString(5, employeAddress.get(3));
				preparedStatement2.executeUpdate();

				closeInput = true;

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Nicht alle Felder ausgefüllt", "Abbruch",
						JOptionPane.WARNING_MESSAGE);
				System.err.println("Ein Fehler ist aufgetreten: " + e.getMessage());
			}
		} catch (SQLException e) {

		}
	}

	private boolean isDuplicateCustomer(String employeID) throws SQLException {
	    String query = "SELECT mitarbeiterID FROM mitarbeiter WHERE mitarbeiterID =" + employeID;

	    try (PreparedStatement preparedStatement = connectionData.connection.prepareStatement(query)) {
	        
	        ResultSet resultSet = preparedStatement.executeQuery();	        
	        return resultSet.next();
	    }
	}
	

	public void setNameTextInput(int nameSize) {
		employeNameField.textInput(nameSize);
		employeName = new ArrayList<>();
		employeName = employeNameField.getText();
	}

	public void setAddressTextInput(int addressSize) {
		employeAddressField.textInput(addressSize);
		employeAddress = new ArrayList<>();
		employeAddress = employeAddressField.getText();
	}

	public boolean getCloseInput() {
		return closeInput;
	}
}