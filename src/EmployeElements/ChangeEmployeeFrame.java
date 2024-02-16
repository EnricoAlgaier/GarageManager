package EmployeElements;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import Database.DatabaseConnection;
import Database.UpdateEmployeeDatabase;
import GuiElements.CButton;
import GuiElements.CLabel;
import GuiElements.CTextField;
import GuiElements.Window;
import Listener.EmployeListener;

public class ChangeEmployeeFrame extends Window {
	protected static int weight = 800;
	protected static int height = 500;

	private DatabaseConnection connectionData;
	private EmployeListener employeListener;
	private FillChangeFrame fillChangeFrame;
	private UpdateEmployeeDatabase updateEmployee;
	private CTextField nameField, addressField, idField;
	private CButton menueButton;
	private CLabel customerLabel, addresslabel;

	private String[] menueButtonName = { "ändern", "zurück" };
	private String[] menueButtonID = { "change", "back" };
	private String[] customerLabelname = { "Vorname", "Nachname", "Telefonnummer" };
	private String[] addressLabelName = { "PLZ", "Ort", "Straße", "Hausnummer" };

	private int nameTextFieldSize = 3;
	private int addressTextFieldSize = 4;
	private String emplyoeeID;

	public ChangeEmployeeFrame(FillChangeFrame fillChangeFrame, String employeID) {
		super(weight, height, "Mitarbeiter ändern von Mitarbeiter: " + employeID);

		this.fillChangeFrame = fillChangeFrame;
		this.emplyoeeID = employeID;

		connectionData = new DatabaseConnection();

		nameField = new CTextField(nameTextFieldSize);
		addressField = new CTextField(addressTextFieldSize);
		idField = new CTextField(1);

		updateEmployee = new UpdateEmployeeDatabase(connectionData, nameField, addressField);
		employeListener = new EmployeListener(this, updateEmployee);

		customerLabel = new CLabel(nameTextFieldSize);
		addresslabel = new CLabel(addressTextFieldSize);

		menueButton = new CButton(employeListener, 2);
		

		nameField.createTextFields(40, 40, 120, 30, 130, "posX");
		for (JTextField textField : nameField.getFields()) {
			add(textField);
		}

		addressField.createTextFields(40, 110, 120, 30, 130, "posX");
		for (JTextField textField : addressField.getFields()) {
			add(textField);
		}

		customerLabel.createLabels(40, 20, 120, 20, 130, "posX", customerLabelname);
		for (JLabel label : customerLabel.getLabels()) {
			add(label);
		}

		addresslabel.createLabels(40, 90, 120, 20, 130, "posX", addressLabelName);
		for (JLabel label : addresslabel.getLabels()) {
			add(label);
		}

		menueButton.createButtonsEmploye(40, 250, 120, 30, 130, "posX", menueButtonID, menueButtonName);
		for (JButton button : menueButton.getButtons()) {
			add(button);
		}
		

		setNameText();
		setAddressText();

		setVisible(true);
	}

	public void setNameText() {
		for (int input = 0; input < fillChangeFrame.getcustomerText().size(); input++) {
			nameField.getFields()[input].setText(fillChangeFrame.getcustomerText().get(input));
		}
	}

	public void setAddressText() {
		for (int input = 0; input < fillChangeFrame.getaddressText().size(); input++) {
			addressField.getFields()[input].setText(fillChangeFrame.getaddressText().get(input));
		}
	}
	

	public int getNameSize() {
		return nameTextFieldSize;
	}

	public int getAddressSize() {
		return addressTextFieldSize;
	}
	
	public String getIdText() {
		return emplyoeeID;
	}
}