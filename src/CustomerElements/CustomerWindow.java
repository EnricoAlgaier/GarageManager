package CustomerElements;

import java.awt.Component;

import javax.swing.JButton;
import Database.DatabaseConnection;
import Database.DatabaseOutput;
import GuiElements.CButton;
import GuiElements.ScrollBar;
import GuiElements.Window;
import Listener.ActionListenerMain;

public class CustomerWindow {
	protected static int weight = 1000;
	protected static int height = 500;
	private Window frame;
	private CButton menuButton;
	private ActionListenerMain actionListener;
	protected String[] columnNames = {"Kd-Nr.", "Vorname", "Nachname"};
	private ScrollBar scrollBar;
	private DatabaseOutput output;
	private DatabaseConnection connectionData;
	private String[] buttonNames = {"Kunde hinzufügen", "Kundendaten", "Kunde löschen", "Kunde suchen"};
	
	public CustomerWindow(Window frame) {
		this.frame = frame;
	}
	
	public void createCustomerWindow() {
		actionListener = new ActionListenerMain(null);
		menuButton = new CButton(actionListener, 4);
		connectionData = new DatabaseConnection();
		scrollBar = new ScrollBar(frame, connectionData);
		output = new DatabaseOutput(scrollBar, connectionData);
		
		connectionData.connectDatabase();
		scrollBar.createTable(3, columnNames);
		output.queryData();
		menuButton.createButtons(820, 20, 150, 30, 40, "posY", buttonNames, buttonNames);
		for(JButton button : menuButton.getButtons()) {
			frame.add(button);
		}
		frame.repaint();
	}
}