package WarehouseElements;

import javax.swing.JButton;
import javax.swing.JTextField;

import Database.DatabaseConnection;
import Database.SearchDatabaseContentWarehouse;
import GuiElements.CButton;
import GuiElements.CTextField;
import GuiElements.ScrollBar;
import GuiElements.Window;
import Listener.WarehouseListener;

public class WarehouseFrame extends Window{
	protected static int weight = 1000;
	protected static int height = 500;
	
	private WarehouseListener actionListener;
	private DatabaseConnection connectionData;
	private DatabaseOutputWarehouse databaseOutput;
	private SearchDatabaseContentWarehouse search;
	
	private CButton menueButton, backButton;
	private ScrollBar scrollBar;
	private CTextField searchField;
	
	protected String[] columnNames = {"Teilenummer", "Ersatzteil", "Menge", "Preis"};
	protected String[] menueButtonNames = {"Alle Ersatzteile anzeigen", "Erstzteile hinzufügen", "Ersatzteile ändern", "Ersatzteile löschen", "Suchen"};
	protected String[] backButtonNames = {"zurück"};
	protected String[] backButtonID = {"backWarehouseFrame"};
	
	public WarehouseFrame(String windowName) {
		super(weight, height, windowName);
		
		connectionData = new DatabaseConnection();
		searchField = new CTextField(1);
		scrollBar = new ScrollBar(this, connectionData);
		search = new SearchDatabaseContentWarehouse(connectionData, scrollBar, searchField);
		actionListener = new WarehouseListener(this, search);
		menueButton = new CButton(actionListener, 5);
		backButton = new CButton(actionListener, 1);
		databaseOutput = new DatabaseOutputWarehouse(scrollBar, connectionData);
		
		menueButton.createButtonsWarehouse(20, 20, 180, 40, 40, "posY", menueButtonNames, menueButtonNames);
		for(JButton button : menueButton.getButtons()) {
			add(button);
		}
		
		backButton.createButtonsWarehouse(20, 350, 120, 30, 0, "", backButtonID, backButtonNames);
		for(JButton button : backButton.getButtons()) {
			add(button);
		}
		
		searchField.createTextFields(20, 230, 180, 30, 0, "");
		for(JTextField field : searchField.getFields()) {
			add(field);
		}
		
		createTabel();
		
		close();
		setVisible(true);
	}
	
	public void createTabel() {
		connectionData.connectDatabase();
		scrollBar.createTableWarehouse(4, columnNames);
		databaseOutput.queryData();
	}
}