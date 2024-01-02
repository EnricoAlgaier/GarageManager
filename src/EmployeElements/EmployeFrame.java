package EmployeElements;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import Database.DatabaseConnection;
import Database.EmployeDeleteFromDatabase;
import GuiElements.CButton;
import GuiElements.ScrollBar;
import GuiElements.Window;
import Listener.EmployeListener;

public class EmployeFrame extends Window{
	protected static int weight = 1000;
	protected static int height = 500;
	
	private DatabaseConnection connectionData;
	private EmployeListener emplyoeListener;
	private ScrollBar scrollBarList;
	private DatabaseOutputEmploye databaseOutput;
	private EmployeDeleteFromDatabase deleteFromDatabase;
	
	private CButton menuButton, backButton;
	
	protected String[] columnNames = {"Personalnummer", "Vorname", "Nachname", "Telefonnummer"};
	protected String[] menuButtonName = {"Mitarbeiter hinzufügen", "Mitarbeiterdaten ändern", "Mitarbeiter entfernen"};
	protected String[] backButtonName = {"zurück"};
	protected String[] backButtonID = {"backToMain"};
	
	private  int employeID;
	
	public EmployeFrame(String windowName) {
		super(weight, height, windowName);
		
		connectionData = new DatabaseConnection();
		emplyoeListener = new EmployeListener(this, deleteFromDatabase, connectionData);
		scrollBarList = new ScrollBar(this, connectionData);
		databaseOutput = new DatabaseOutputEmploye(scrollBarList, connectionData);
		menuButton = new CButton(emplyoeListener, 3);
		backButton = new CButton(emplyoeListener, 1);
		
		menuButton.createButtonsEmploye(20, 20, 180, 40, 40, "posY", menuButtonName, menuButtonName);
		for(JButton button : menuButton.getButtons()) {
			add(button);
		}
		
		backButton.createButtonsEmploye(20, 400, 100, 30, 0, "", backButtonID, backButtonName);
		for(JButton button : backButton.getButtons()) {
			add(button);
		}
		
		createOrderList();
		
		
		scrollBarList.getDataTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = scrollBarList.getDataTable().getSelectedRow();
                if (selectedRow != -1) { 
                	employeID = (int) scrollBarList.getDataTable().getValueAt(selectedRow, 0); 
                    System.out.println("Auftragsnummer der ausgewählten Zeile: " + employeID);  
                }
            }
        });
		
		close();
		setVisible(true);
	}
	
	public void createOrderList() {
		connectionData.connectDatabase();
		scrollBarList.createEmployeList(4, columnNames);
		databaseOutput.queryData();
	}
	
	public int getEmployeID() {
		return employeID;
	}
	
	public void delete() {
		deleteFromDatabase = new EmployeDeleteFromDatabase(employeID, connectionData);
	}
}