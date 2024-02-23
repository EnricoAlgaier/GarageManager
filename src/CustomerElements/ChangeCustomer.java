package CustomerElements;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import Database.DatabaseConnection;
import Database.UpdateDatabase;
import GuiElements.CButton;
import GuiElements.CLabel;
import GuiElements.CTextField;
import GuiElements.Window;
import Listener.CustomerListener;

public class ChangeCustomer extends Window {
	private final static int weight = 800;
	private final static int height = 500;

	private DatabaseConnection connectionData;
	private CustomerListener actionListener;
	private FillCustomerValues fillCustomerValues;
	private UpdateDatabase updateValues;
	private MainCustomerFrame mainCustomerFrame;

	private CTextField nameField, addressField, vehicleField, customerIDField;
	private CButton menueButton;
	private CLabel customerLabel, addresslabel, vehicleLabel;
	private String customerID;

	private String[] menueButtonName = { "ändern", "zurück" };
	private String[] menueButtonID = { "ändern", "zurück change" };
	private String[] customerLabelname = { "Vorname", "Nachname", "Telefonnummer", "E-Mail" };
	private String[] addressLabelName = { "PLZ", "Ort", "Straße", "Hausnummer" };
	private String[] veicleLabelName = { "Fahrzeugmodell", "Tüv", "KM-Stand", "Kennzeichen" };

	private int nameTextFieldSize = 4;
	private int addressTextFieldSize = 4;
	private int vehicleTextFieldSize = 4;
	private int customerIDFieldSize = 1;

	public ChangeCustomer(FillCustomerValues fillCustomerValues, String customerID, MainCustomerFrame mainCustomerFrame) {
		super(weight, height, "Kundendaten ändern von Kundennummer: " + customerID);
		
		this.customerID = customerID;
		this.fillCustomerValues = fillCustomerValues;
		this.mainCustomerFrame = mainCustomerFrame;

		connectionData = new DatabaseConnection();

		nameField = new CTextField(nameTextFieldSize);
		addressField = new CTextField(addressTextFieldSize);
		vehicleField = new CTextField(vehicleTextFieldSize);
		customerIDField = new CTextField(customerIDFieldSize);

		updateValues = new UpdateDatabase(connectionData, nameField, addressField, vehicleField, customerIDField);
		actionListener = new CustomerListener(mainCustomerFrame, null, this, updateValues);

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


		setVisible(true);
	}

	public void setNameText() {
		for (int input = 0; input < fillCustomerValues.getcustomerText().size(); input++) {
			nameField.getFields()[input].setText(fillCustomerValues.getcustomerText().get(input));
		}
	}

	public void setAddressText() {
		for (int input = 0; input < fillCustomerValues.getaddressText().size(); input++) {
			addressField.getFields()[input].setText(fillCustomerValues.getaddressText().get(input));
		}
	}

	public void setVehicleText() {
		for (int input = 0; input < fillCustomerValues.getvehicleText().size(); input++) {
			vehicleField.getFields()[input].setText(fillCustomerValues.getvehicleText().get(input));
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
	
	public String getIdText() {
		return customerID;
	}

}