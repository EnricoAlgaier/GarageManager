package GarageElements;

import javax.swing.JButton;

import Database.DatabaseConnection;
import GuiElements.CButton;
import GuiElements.CTextField;
import GuiElements.ScrollBar;
import GuiElements.Window;
import Listener.ActionListenerMain;
import Listener.OrdersListener;

public class GarageFrame extends Window{
	protected static int weight = 1000;
	protected static int height = 500;
	
	private CButton backButton, menueButton;
	private ActionListenerMain actionListener;
	private OrdersListener ordersListener;
	private CTextField fields;
	private ScrollBar scrollBar;
	private DatabaseOutputGarage output;
	private DatabaseConnection connectionData;
	
	protected String[] columnNames = {"Mitarbeiter", "Kunde Name", "Fahrzeug", "Status", "Startzeit", "Endzeit"};
	protected String[] backButtonName = {"zurück"};
	protected String[] backID = {"backGarage"};
	protected String[] menueButtonName = {"Aufträge", "Hebebühnen belegung"};
	
	public GarageFrame(String windowName) {
		super(weight, height, windowName);
		
		actionListener = new ActionListenerMain(this);
		ordersListener = new OrdersListener(this);
		
		backButton = new CButton(actionListener, 1);
		menueButton = new CButton(ordersListener, 2);
		fields = new CTextField(1);
		
		connectionData = new DatabaseConnection();
		scrollBar = new ScrollBar(this, connectionData);
		output = new DatabaseOutputGarage(scrollBar, connectionData);
		
		connectionData.connectDatabase();
		scrollBar.createTableGarage(6, columnNames);
		output.queryData();
		
		menueButton.createButtonsOrders(20, 20, 180, 40, 40, "posY", menueButtonName, menueButtonName);
		for(JButton button : menueButton.getButtons()) {
			add(button);
		}
		
		backButton.createButtons(20, 400, 100, 30, 0, "", backID, backButtonName);
		for(JButton button : backButton.getButtons()) {
			add(button);
		}
		
		close();
		setVisible(true);
	}
}