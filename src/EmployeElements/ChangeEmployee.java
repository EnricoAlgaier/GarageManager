package EmployeElements;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import CustomerElements.CheckCustomerId;
import Database.DatabaseConnection;
import Database.UpdateDatabase;
import GuiElements.CButton;
import GuiElements.CLabel;
import GuiElements.CTextField;
import GuiElements.Window;
import Listener.CustomerListener;

public class ChangeEmployee extends Window {
	protected static int weight = 800;
	protected static int height = 500;

	private DatabaseConnection connectionData;
	private CustomerListener actionListener;
	private CheckCustomerId checkCustomerID;
	private UpdateDatabase updateValues;

	private CTextField nameField, addressField, vehicleField, customerIDField;
	private CButton menueButton;
	private CLabel customerLabel, addresslabel, vehicleLabel;

	protected String[] menueButtonName = { "ändern", "zurück" };
	protected String[] menueButtonID = { "ändern", "zurück change" };
	protected String[] customerLabelname = { "Vorname", "Nachname", "Telefonnummer" };
	protected String[] addressLabelName = { "PLZ", "Ort", "Straße", "Hausnummer" };
	protected String[] veicleLabelName = { "Fahrzeugmodell", "Tüv", "KM-Stand", "Kennzeichen" };

	protected int nameTextFieldSize = 3;
	protected int addressTextFieldSize = 4;
	protected int vehicleTextFieldSize = 4;
	protected int customerIDFieldSize = 1;
	

	public ChangeEmployee(CheckCustomerId checkCustomerID, String customerID) {
		super(weight, height, "Kundendaten ändern von Kundennummer: " + customerID);
		
		this.checkCustomerID = checkCustomerID;
		
		connectionData = new DatabaseConnection();

		nameField = new CTextField(nameTextFieldSize);
		addressField = new CTextField(addressTextFieldSize);
		vehicleField = new CTextField(vehicleTextFieldSize);
		customerIDField = new CTextField(customerIDFieldSize);

		updateValues = new UpdateDatabase(connectionData, nameField, addressField, vehicleField, customerIDField);
		actionListener = new CustomerListener(null, null, this, null, checkCustomerID, updateValues);

		customerLabel = new CLabel(nameTextFieldSize);
		addresslabel = new CLabel(addressTextFieldSize);
		vehicleLabel = new CLabel(vehicleTextFieldSize);

		menueButton = new CButton(actionListener, 2);

		nameField.createTextFields(40, 40, 120, 30, 130, "posX");
		for (JTextField textField : nameField.getFields()) {
			add(textField);
		}

		addressField.createTextFields(40, 110, 120, 30, 130, "posX");
		for (JTextField textField : addressField.getFields()) {
			add(textField);
		}

		vehicleField.createTextFields(40, 180, 120, 30, 130, "posX");
		for (JTextField textField : vehicleField.getFields()) {
			add(textField);
		}
		
		customerIDField.createTextFields(100, 300, 120, 30, 130, "posX");
		for (JTextField textField : customerIDField.getFields()) {
			add(textField);
			textField.setVisible(false);
		}

		customerLabel.createLabels(40, 20, 120, 20, 130, "posX", customerLabelname);
		for (JLabel label : customerLabel.getLabels()) {
			add(label);
		}

		addresslabel.createLabels(40, 90, 120, 20, 130, "posX", addressLabelName);
		for (JLabel label : addresslabel.getLabels()) {
			add(label);
		}

		vehicleLabel.createLabels(40, 160, 120, 20, 130, "posX", veicleLabelName);
		for (JLabel label : vehicleLabel.getLabels()) {
			add(label);
		}

		menueButton.createButtonsCustomer(40, 250, 120, 30, 130, "posX", menueButtonID, menueButtonName);
		for (JButton button : menueButton.getButtons()) {
			add(button);
		}
		
		setNameText();
		setAddressText();
		setVehicleText();
		setCustomerIdText();
		
		setVisible(true);
	}
	
	public void setNameText() {
		for (int input = 0; input < checkCustomerID.getcustomerText().size(); input++) {
			nameField.getFields()[input].setText(checkCustomerID.getcustomerText().get(input));
		}
	}
	
	public void setAddressText() {
		for (int input = 0; input < checkCustomerID.getaddressText().size(); input++) {
			addressField.getFields()[input].setText(checkCustomerID.getaddressText().get(input));
		}
	}
	
	public void setVehicleText() {
		for (int input = 0; input < checkCustomerID.getvehicleText().size(); input++) {
			vehicleField.getFields()[input].setText(checkCustomerID.getvehicleText().get(input));
		}
	}
	
	public void setCustomerIdText() {
		for (int input = 0; input < checkCustomerID.getCustomerIdText().size(); input++) {
			customerIDField.getFields()[input].setText(checkCustomerID.getCustomerIdText().get(input));
		}
	}

	public int getNameSize() {
		return nameTextFieldSize;
	}

	public int getAddressSize() {
		return addressTextFieldSize;
	}

	public int getVehicleSize() {
		return vehicleTextFieldSize;
	}
	
	public int getCustomerSize() {
		return customerIDFieldSize;
	}

}