package CustomerElements;

import javax.swing.*;
import Database.DatabaseConnection;
import Database.InputToDatabaseCustomer;
import GuiElements.CButton;
import GuiElements.CLabel;
import GuiElements.CTextField;
import GuiElements.Window;
import Listener.CustomerListener;

public class CreateCustomer extends Window {
	protected static int weight = 600;
	protected static int height = 360;

	private DatabaseConnection connectionData;
	private CustomerListener actionListener;
	private MainCustomerFrame mainCustomerFrame;

	private CTextField nameField, addressField, carField;
	private CButton menueButton;
	private CLabel customerLabel, addresslabel, vehicleLabel;
	private InputToDatabaseCustomer insertDatabase;
	
	protected String[] menueButtonName = { "Eintragen", "zurück" };
	protected String[] customerLabelname = {"Vorname", "Nachname", "Telefonnummer", "E-Mail"};
	protected String[] addressLabelName = {"PLZ", "Ort", "Straße", "Hausnummer"};
	protected String[] veicleLabelName = {"Fahrzeugmodell", "Tüv", "KM-Stand", "Kennzeichen"};
	
	protected int nameTextFieldSize = 4;
	protected int addressTextFieldSize = 4;
	protected int vehicleTextFieldSize = 4;
	
	
	public CreateCustomer(MainCustomerFrame mainCustomerFrame) {
		super(weight, height, "Kunde Anlegen");
		this.mainCustomerFrame = mainCustomerFrame;
		
		connectionData = new DatabaseConnection();
		
		nameField = new CTextField(nameTextFieldSize);
		addressField = new CTextField(addressTextFieldSize);
		carField = new CTextField(vehicleTextFieldSize);
		
		insertDatabase = new InputToDatabaseCustomer(connectionData, nameField, addressField, carField);
		actionListener = new CustomerListener(mainCustomerFrame, insertDatabase, null, null);
		
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