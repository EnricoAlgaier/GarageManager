package Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.AbstractProcessor;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import EmployeElements.ChangeEmployeeFrame;
import GuiElements.CTextField;

public class UpdateDatabase {
	private DatabaseConnection connection;
	private ChangeEmployeeFrame changeFrame;
	private JTextField[] changeFields;
	private CTextField newCustomerValue, newAddressValue, newVehicleValue, customerIdValue;

	private List<String> customerText;
	private List<String> addressText;
	private List<String> vehicleText;
	private List<String> customerId;
	
	private boolean isUsed;

	public UpdateDatabase(DatabaseConnection connection, CTextField newCustomerValue, CTextField newAddressValue,
			CTextField newVehicleValue, CTextField customerIdValue) {
		this.connection = connection;
		this.newCustomerValue = newCustomerValue;
		this.newAddressValue = newAddressValue;
		this.newVehicleValue = newVehicleValue;
		this.customerIdValue = customerIdValue;	
		
		isUsed = false;
	}

	public void update(int customerID) {
	    try {
	        connection.connectDatabase();
	        
	        String updateCustomer = "UPDATE kunden SET vorname = ?, nachname = ?, telefonnummer = ?, email_address = ? WHERE kd_nr = " + customerID;
	        String updateAddress = "UPDATE anschrift SET plz = ?, ort = ?, straße = ?, hausnummer = ? WHERE kd_nr = " + customerID;
	        String updateVehicle = "UPDATE kundenfahrzeug SET fahrzeug = ?, tüv = ?, km_stand = ?, kennzeichen = ? WHERE kd_nr = " + customerID;

	        String vorname = customerText.get(0);
	        String nachname = customerText.get(1);
	        String telefonnummer = customerText.get(2);
	        String email = customerText.get(3);

	        String plz = addressText.get(0);
	        String ort = addressText.get(1);
	        String straße = addressText.get(2);
	        String hausnummer = addressText.get(3);

	        String fahrzeugmodell = vehicleText.get(0);
	        String tüv = vehicleText.get(1);
	        int km_stand = Integer.parseInt(vehicleText.get(2));
	        String plate = vehicleText.get(3);


	        PreparedStatement updateCustomerStatement = connection.connection.prepareStatement(updateCustomer);
	        updateCustomerStatement.setString(1, vorname);
	        updateCustomerStatement.setString(2, nachname);
	        updateCustomerStatement.setString(3, telefonnummer);
	        updateCustomerStatement.setString(4, email);
	        updateCustomerStatement.executeUpdate();

	        PreparedStatement updateAddressStatement = connection.connection.prepareStatement(updateAddress);
	        updateAddressStatement.setString(1, plz);
	        updateAddressStatement.setString(2, ort);
	        updateAddressStatement.setString(3, straße);
	        updateAddressStatement.setString(4, hausnummer);
	        updateAddressStatement.executeUpdate();

	        PreparedStatement updateVehicleStatement = connection.connection.prepareStatement(updateVehicle);
	        updateVehicleStatement.setString(1, fahrzeugmodell);
	        updateVehicleStatement.setString(2, tüv);
	        updateVehicleStatement.setInt(3, km_stand);
	        updateVehicleStatement.setString(4, plate);
	        updateVehicleStatement.executeUpdate();

	        updateCustomerStatement.close();
	        updateAddressStatement.close();
	        updateVehicleStatement.close();
	       
	        isUsed = true;

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public void setChangeField(JTextField[] changeFields) {
		this.changeFields = changeFields;
	}

	public void setNameTextInput(int nameSize) {
		newCustomerValue.textInput(nameSize);
		customerText = new ArrayList<>();
		customerText = newCustomerValue.getText();
	}

	public void setAddressTextInput(int addressSize) {
		newAddressValue.textInput(addressSize);
		addressText = new ArrayList<>();
		addressText = newAddressValue.getText();
	}

	public void setVehicleTextInput(int vehicleSize) {
		newVehicleValue.textInput(vehicleSize);
		vehicleText = new ArrayList<>();
		vehicleText = newVehicleValue.getText();
	}

	public void setCustomerIdTextInput(int customerIdSize) {
		customerIdValue.textInput(customerIdSize);
		customerId = new ArrayList<>();
		customerId = customerIdValue.getText();
	}

	public void setCustomerID(int number) {
		customerIdValue.textInput(number);
		customerId = new ArrayList<>();
		customerId = customerIdValue.getText();
	}
	
	public boolean getUsed() {
		return isUsed;
	}
}