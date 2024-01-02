package GarageElements;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import Database.DatabaseConnection;
import Database.InputToDatabaseOrdersCreate;
import GuiElements.CButton;
import GuiElements.CLabel;
import GuiElements.CTextField;
import GuiElements.Window;
import Listener.OrdersListener;

public class OrdersCreateFrame extends Window{
	protected static int weight = 500;
	protected static int height = 500;
	
	private CButton menueButton;
	private OrdersListener ordersListener;
	private DatabaseConnection connectionData;
	private InputToDatabaseOrdersCreate inputToDatatbase;
	private CTextField inputFields;
	private CLabel descriptionLabel;
	private JTextArea descriptionInput;
	
	
	protected String[] labelNames = {"Mitarbeiternummer", "Kundennummer", "Fehler beschreibung"};
	protected String[] menueButtonNames = {"hinzufügen", "zurück"};
	protected String[] menueButtonID = {"add", "backOrdersCreate"};
	
	protected int ordersTextFieldSize = 2;
	
	public OrdersCreateFrame(String windowName) {
		super(weight, height, windowName);
		
		connectionData = new DatabaseConnection();
		
		inputFields = new CTextField(ordersTextFieldSize);
		descriptionInput = new JTextArea();
		inputToDatatbase = new InputToDatabaseOrdersCreate(connectionData, inputFields, descriptionInput);
		ordersListener = new OrdersListener(this, inputToDatatbase);
		
		menueButton = new CButton(ordersListener, 2);
		descriptionLabel = new CLabel(3);
		
		inputFields.createTextFields(50, 50, 100, 30, 60, "posY");
		for(JTextField field : inputFields.getFields()) {
			add(field);
		}
		
		descriptionLabel.createLabels(50, 25, 150, 30, 60, "posY", labelNames);
		for(JLabel label : descriptionLabel.getLabels()) {
			add(label);
		}
		
		menueButton.createButtonsOrders(50, 400, 150, 30, 160, "posX", menueButtonID, menueButtonNames);
		for(JButton button : menueButton.getButtons()) {
			add(button);
		}
		
		descriptionInput.setBounds(50, 170, 300, 200);
		add(descriptionInput);
			
		setVisible(true);
	}

	public int getOrdersFieldSize() {
		return ordersTextFieldSize;
	}
}