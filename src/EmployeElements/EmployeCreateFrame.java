package EmployeElements;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Database.DatabaseConnection;
import Database.InputToDatabaseCustomer;
import Database.InputToDatabaseEmploye;
import GuiElements.CButton;
import GuiElements.CLabel;
import GuiElements.CTextField;
import GuiElements.Window;
import Listener.EmployeListener;

public class EmployeCreateFrame extends Window{
	protected static int weight = 700;
	protected static int height = 300;
	
	private DatabaseConnection connectionData;
	private EmployeListener actionListener;
	
	private CButton menuButton, backButton;
	private CTextField nameField, addressField;
	private CLabel employeLabel, addresslabel;
	private InputToDatabaseEmploye inputToDatabase;
	
	protected String[] menueButtonName = { "Eintragen", "zurück" };
	protected String[] menueButtonID = { "add", "close" };
	protected String[] customerLabelname = {"Personalnummer", "Vorname", "Nachname", "Telefonnummer", "E-Mail"};
	protected String[] addressLabelName = {"PLZ", "Ort", "Straße", "Hausnummer"};
	protected String[] veicleLabelName = {"Fahrzeugmodell", "Tüv", "KM-Stand"};
	
	protected int nameTextFieldSize = 5;
	protected int addressTextFieldSize = 4;
	
	public EmployeCreateFrame(String windowName) {
		super(weight, height, windowName);
		
		connectionData = new DatabaseConnection();
		
		nameField = new CTextField(nameTextFieldSize);
		addressField = new CTextField(addressTextFieldSize);
		
		inputToDatabase = new InputToDatabaseEmploye(connectionData, nameField, addressField);
		actionListener = new EmployeListener(this, inputToDatabase, null);
		menuButton = new CButton(actionListener, 2);
		
		employeLabel = new CLabel(nameTextFieldSize);
		addresslabel = new CLabel(addressTextFieldSize);
		
		nameField.createTextFields(40, 40, 120, 30, 130, "posX");
		for (JTextField textField : nameField.getFields()) {
			add(textField);
		}

		addressField.createTextFields(40, 110, 120, 30, 130, "posX");
		for (JTextField textField : addressField.getFields()) {
			add(textField);
		}
		
		employeLabel.createLabels(40, 20, 120, 20, 130, "posX", customerLabelname);
		for(JLabel label : employeLabel.getLabels()) {
			add(label);
		}
		
		addresslabel.createLabels(40, 90, 120, 20, 130, "posX", addressLabelName);
		for(JLabel label : addresslabel.getLabels()) {
			add(label);
		}
		
		menuButton.createButtonsEmploye(40, 180, 120, 30, 130, "posX", menueButtonID, menueButtonName);
		for (JButton button : menuButton.getButtons()) {
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

}