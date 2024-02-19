package Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;
import EmployeElements.ChangeEmployeeFrame;
import GuiElements.CTextField;

public class UpdateEmployeeDatabase {
	private DatabaseConnection connection;
	private CTextField newEmployeeValue, newAddressValue;

	private List<String> employeeNameText;
	private List<String> addressText;
	
	private boolean windowClose;

	public UpdateEmployeeDatabase(DatabaseConnection connection, CTextField newCustomerValue,
			CTextField newAddressValue) {
		this.connection = connection;
		this.newEmployeeValue = newCustomerValue;
		this.newAddressValue = newAddressValue;
		
		windowClose = false;
	}

	public void update(int employeID) {
		try {
			connection.connectDatabase();

			String updateEmployee = "UPDATE mitarbeiter SET vorname = ?, nachname = ?, telefonnummer = ?, email_address = ? WHERE mitarbeiterID = "
					+ employeID;
			String updateEmplyeeAddress = "UPDATE mitarbeiter_anschrift SET plz = ?, ort = ?, straße = ?, hausnummer = ? WHERE mitarbeiterID = "
					+ employeID;

			String vorname = employeeNameText.get(0);
			String nachname = employeeNameText.get(1);
			String telefonnummer = employeeNameText.get(2);
			String email = employeeNameText.get(3);

			String plz = addressText.get(0);
			String ort = addressText.get(1);
			String straße = addressText.get(2);
			String hausnummer = addressText.get(3);

			PreparedStatement updateCustomerStatement = connection.connection.prepareStatement(updateEmployee);
			updateCustomerStatement.setString(1, vorname);
			updateCustomerStatement.setString(2, nachname);
			updateCustomerStatement.setString(3, telefonnummer);
			updateCustomerStatement.setString(4, email);
			updateCustomerStatement.executeUpdate();

			PreparedStatement updateAddressStatement = connection.connection.prepareStatement(updateEmplyeeAddress);
			updateAddressStatement.setString(1, plz);
			updateAddressStatement.setString(2, ort);
			updateAddressStatement.setString(3, straße);
			updateAddressStatement.setString(4, hausnummer);
			updateAddressStatement.executeUpdate();

			updateCustomerStatement.close();
			updateAddressStatement.close();
			
			windowClose = true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setNameTextInput(int nameSize) {
		newEmployeeValue.textInput(nameSize);
		employeeNameText = new ArrayList<>();
		employeeNameText = newEmployeeValue.getText();
	}

	public void setAddressTextInput(int addressSize) {
		newAddressValue.textInput(addressSize);
		addressText = new ArrayList<>();
		addressText = newAddressValue.getText();
	}
	
	public boolean getWindowclose() {
		return windowClose;
	}
}