package WarehouseElements;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import Database.DatabaseConnection;
import GuiElements.CButton;
import GuiElements.CLabel;
import GuiElements.CTextField;
import GuiElements.Window;
import Listener.WarehouseListener;

public class ServicePartDeleteFrame extends Window{
	protected static int weight = 250;
	protected static int height = 250;
	protected static String windowName = "Ersatzteilnummer";
	
	private WarehouseListener actionListener;
	private CLabel servicePartIDLabel;
	private CTextField servicePartIdField;
	private CButton confirmButton;
	private DatabaseConnection connectionData;
	private ServicePartIDCheck servicePartCheck;
	
	protected String[] servicePartIdLabelName = {"Ersatzteilnummer"};
	protected String[] confirmButtonName = {"bestätigen", "zurück"};
	protected String[] confirmButtonID = {"confirm", "back"};
	
	public ServicePartDeleteFrame() {
		super(weight, height, windowName);
		
		connectionData = new DatabaseConnection();
		servicePartIdField = new CTextField(1);
		servicePartCheck = new ServicePartIDCheck(connectionData, servicePartIdField);
		actionListener = new WarehouseListener(this, servicePartCheck); 
		servicePartIDLabel = new CLabel(1);
		
		confirmButton = new CButton(actionListener, 2);
		
		servicePartIDLabel.createLabels(40, 20, 120, 30, 0, "", servicePartIdLabelName);
		for(JLabel label : servicePartIDLabel.getLabels()) {
			add(label);
		}
		
		servicePartIdField.createTextFields(40, 50, 125, 30, 0, "");
		for(JTextField field : servicePartIdField.getFields()) {
			add(field);
		}
		
		confirmButton.createButtonsWarehouse(55, 90, 100, 30, 40, "posY", confirmButtonID, confirmButtonName);
		for(JButton button : confirmButton.getButtons()) {
			add(button);
		}
		
		setVisible(true);
	}
}