package WarehouseElements;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Database.DatabaseConnection;
import Database.UpdateWarehouseDatabase;
import GuiElements.CButton;
import GuiElements.CLabel;
import GuiElements.CTextField;
import GuiElements.Window;
import Listener.WarehouseListener;

public class ChangeWarehouseFrame extends Window{
	private final static int width = 500;
	private final static int height = 500;
	private final static String windowName = "Warenname ändern von: ";
	
	private FillWarehouseValues fillWarehouseValues;
	private CButton menuButton;
	private CTextField inputField; 
	private CLabel columnName;
	private WarehouseListener warehouseListener;
	private UpdateWarehouseDatabase updateWarehouseDatabase;
	private DatabaseConnection databaseConnection;
	
	
	private String[] columnNames = {"Ersatzteil", "Menge", "Preis"};
	private String[] buttonNames = {"ändern", "zurück"};
	private String[] buttonID = {"change", "cancel"};
	
	private int columnsSize = 3;
	private String warehouseID;
	
	public ChangeWarehouseFrame(FillWarehouseValues fillWarehouseValues, String warehouseID) {
		super(width, height, windowName + warehouseID);
		
		this.fillWarehouseValues = fillWarehouseValues;
		this.warehouseID = warehouseID;
		
		databaseConnection = new DatabaseConnection();
		
		inputField = new CTextField(columnsSize);
		updateWarehouseDatabase = new UpdateWarehouseDatabase(databaseConnection, inputField);
		warehouseListener = new WarehouseListener(this, updateWarehouseDatabase);
		menuButton = new CButton(warehouseListener, 2);
		
		columnName = new CLabel(3);
		
		menuButton.createButtonsWarehouse(20, 150, 100, 30, 110, "posX", buttonID, buttonNames);
		for(JButton buttons : menuButton.getButtons()) {
			add(buttons);
		}
		
		inputField.createTextFields(40, 40, 100, 30, 120, "posX");
		for(JTextField fields : inputField.getFields()) {
			add(fields);
		}
		
		columnName.createLabels(40, 15, 100, 30, 120, "posX", columnNames);
		for(JLabel labels : columnName.getLabels()) {
			add(labels);
		}
		
		setInputText();
		
		setVisible(true);
	}
	
	public void setInputText() {
		for (int input = 0; input < fillWarehouseValues.getWarehouseText().size(); input++) {
			inputField.getFields()[input].setText(fillWarehouseValues.getWarehouseText().get(input));
		}
	}
	
	public int getNameSize() {
		return columnsSize;
	}
	
	public String getWarehouseID() {
		return warehouseID;
	}
}