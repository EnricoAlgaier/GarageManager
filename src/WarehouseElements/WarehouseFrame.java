package WarehouseElements;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JTextField;

import Database.DatabaseConnection;
import Database.SearchDatabaseContentWarehouse;
import GuiElements.CButton;
import GuiElements.CTextField;
import GuiElements.ScrollBar;
import GuiElements.Window;
import Listener.WarehouseListener;

public class WarehouseFrame extends Window {
	protected static int weight = 1000;
	protected static int height = 500;

	private WarehouseListener actionListener;
	private DatabaseConnection connectionData;
	private DatabaseOutputWarehouse databaseOutput;
	private SearchDatabaseContentWarehouse search;

	private CButton menueButton, backButton;
	private ScrollBar scrollBar;
	private CTextField searchField;

	private String[] columnNames = { "Teilenummer", "Ersatzteil", "Menge", "Preis" };
	private String[] menueButtonNames = { "Alle Ersatzteile anzeigen", "Erstzteile hinzufügen", "Ersatzteile ändern",
			"Ersatzteile löschen", "Suchen" };
	private String[] backButtonNames = { "zurück" };
	private String[] backButtonID = { "backWarehouseFrame" };

	private int warehouseID;

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
		for (JButton button : menueButton.getButtons()) {
			add(button);
		}

		backButton.createButtonsWarehouse(20, 350, 120, 30, 0, "", backButtonID, backButtonNames);
		for (JButton button : backButton.getButtons()) {
			add(button);
		}

		searchField.createTextFields(20, 230, 180, 30, 0, "");
		for (JTextField field : searchField.getFields()) {
			add(field);
		}

		createTabel();

		scrollBar.getDataTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = scrollBar.getDataTable().getSelectedRow();
				if (selectedRow != -1) {
					warehouseID = (int) scrollBar.getDataTable().getValueAt(selectedRow, 0);
					System.out.println("Teilenummer: " + warehouseID);
				}
			}
		});

		close();
		setVisible(true);
	}

	public void createTabel() {
		connectionData.connectDatabase();
		scrollBar.createTableWarehouse(4, columnNames);
		databaseOutput.queryData();
	}

	public void removeScrollBarWarehouse() {
		scrollBar.removeScrollbarWarehouse();
		repaint();
	}
	
	public int getWarehouseID() {
		return warehouseID;
	}
}