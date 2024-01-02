package WarehouseElements;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import Database.DatabaseConnection;
import Database.ServicePartInputToDatabase;
import GuiElements.CButton;
import GuiElements.CLabel;
import GuiElements.CTextField;
import GuiElements.Window;
import Listener.WarehouseListener;

public class CreateServicePart extends Window{
	protected static int weight = 800;
	protected static int height = 500;

	private DatabaseConnection connectionData;
	private WarehouseListener actionListener;

	private CTextField nameField;
	private CButton menueButton;
	private CLabel servicePartLabel;
	private ServicePartInputToDatabase insertDatabase;
	
	protected String[] menueButtonName = { "Eintragen", "zurück" };
	protected String[] servicePartLabelName = {"Ersatzteilenummer", "Teilename", "Menge", "Preis"};
	
	protected int servicePartTextFieldSize = 4;

	
	
	public CreateServicePart() {
		super(weight, height, "Ersatzteil hinzufügen");
		connectionData = new DatabaseConnection();
		
		nameField = new CTextField(servicePartTextFieldSize);
		
		insertDatabase = new ServicePartInputToDatabase(connectionData, nameField);
		actionListener = new WarehouseListener(this, insertDatabase);
		
		servicePartLabel = new CLabel(servicePartTextFieldSize);
		
		menueButton = new CButton(actionListener, 2);

		nameField.createTextFields(40, 40, 120, 30, 130, "posX");
		for (JTextField textField : nameField.getFields()) {
			add(textField);
		}
		
		servicePartLabel.createLabels(40, 20, 120, 20, 130, "posX", servicePartLabelName);
		for(JLabel label : servicePartLabel.getLabels()) {
			add(label);
		}
	
		menueButton.createButtonsWarehouse(40, 250, 120, 30, 130, "posX", menueButtonName, menueButtonName);
		for (JButton button : menueButton.getButtons()) {
			add(button);
		}

		setVisible(true);
	}
	
	public int getServicePartFieldSize() {
		return servicePartTextFieldSize;
	}
	
}