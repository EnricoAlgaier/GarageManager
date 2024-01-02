package CustomerElements;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Database.DatabaseConnection;
import GuiElements.CButton;
import GuiElements.CLabel;
import GuiElements.CTextField;
import GuiElements.Window;
import Listener.CustomerListener;

public class CustomerDeleteIDFrame extends Window{
	protected static int weight = 300;
	protected static int height = 200;
	protected static String windowName = "Kunden ID";
	
	private CustomerListener actionListener;
	private CLabel customerId;
	private CTextField customerIdField;
	private CButton confirmButton;
	private DatabaseConnection connectionData;
	private DeleteCustomer deleteCustomerIdCheck;
	
	protected String[] customerIdLabelName = {"Kundennummer"};
	protected String[] confirmButtonName = {"bestätigen"};
	protected String[] confirmButtonId = {"confirm"};
	
	
	public CustomerDeleteIDFrame() {
		super(weight, height, windowName);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		connectionData = new DatabaseConnection();
		customerIdField = new CTextField(1);
		deleteCustomerIdCheck= new DeleteCustomer(connectionData, customerIdField);
		actionListener = new CustomerListener(this, deleteCustomerIdCheck);
		customerId = new CLabel(1);
		
		confirmButton = new CButton(actionListener, 1);
		
		customerId.createLabels(20, 20, 120, 30, 0, "", customerIdLabelName);
		for(JLabel label : customerId.getLabels()) {
			add(label);
		}
		
		customerIdField.createTextFields(20, 50, 120, 30, 0, "");
		for(JTextField field : customerIdField.getFields()) {
			add(field);
		}
		
		confirmButton.createButtonsCustomer(20, 90, 100, 30, 0, "", confirmButtonId, confirmButtonName);
		for(JButton button : confirmButton.getButtons()) {
			add(button);
		}
		
		setVisible(true);
	}
}