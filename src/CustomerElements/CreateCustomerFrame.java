package CustomerElements;

import javax.swing.*;
import Database.DatabaseConnection;
import Database.InputToDatabase;
import GuiElements.CButton;
import GuiElements.CLabel;
import GuiElements.CTextField;
import GuiElements.Window;
import Listener.CustomerListener;

public class CreateCustomerFrame extends Window {
	protected static int weight = 600;
	protected static int height = 360;

	private DatabaseConnection connectionData;
	private CustomerListener actionListener;

	private CTextField nameField, addressField, carField;
	private CButton menueButton;
	private CLabel customerLabel, addresslabel, vehicleLabel;
	private InputToDatabase insertDatabase;
	
	protected String[] menueButtonName = { "Eintragen", "zurück" };
	protected String[] customerLabelname = {"Vorname", "Nachname", "Telefonnummer"};
	protected String[] addressLabelName = {"PLZ", "Ort", "Straße", "Hausnummer"};
	protected String[] veicleLabelName = {"Fahrzeugmodell", "Tüv", "KM-Stand", "Kennzeichen"};
	
	protected int nameTextFieldSize = 3;
	protected int addressTextFieldSize = 4;
	protected int vehicleTextFieldSize = 4;
	
	
	public CreateCustomerFrame() {
		super(weight, height, "Kunde Anlegen");
		connectionData = new DatabaseConnection();
		
		nameField = new CTextField(nameTextFieldSize);
		addressField = new CTextField(addressTextFieldSize);
		carField = new CTextField(vehicleTextFieldSize);
		
		insertDatabase = new InputToDatabase(connectionData, nameField, addressField, carField);
		actionListener = new CustomerListener(this, insertDatabase, null, null, null, null);
		
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

		carField.createTextFields(40, 180, 120, 30, 130, "posX");
		for (JTextField textField : carField.getFields()) {
			add(textField);
		}
		
		customerLabel.createLabels(40, 20, 120, 20, 130, "posX", customerLabelname);
		for(JLabel label : customerLabel.getLabels()) {
			add(label);
		}
		
		addresslabel.createLabels(40, 90, 120, 20, 130, "posX", addressLabelName);
		for(JLabel label : addresslabel.getLabels()) {
			add(label);
		}
		
		vehicleLabel.createLabels(40, 160, 120, 20, 130, "posX", veicleLabelName);
		for(JLabel label : vehicleLabel.getLabels()) {
			add(label);
		}
		
		menueButton.createButtonsCustomer(40, 250, 120, 30, 130, "posX", menueButtonName, menueButtonName);
		for (JButton button : menueButton.getButtons()) {
			add(button);
		}

		setVisible(true);
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
}