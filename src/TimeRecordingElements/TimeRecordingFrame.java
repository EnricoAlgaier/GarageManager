package TimeRecordingElements;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Database.DatabaseConnection;
import GuiElements.CButton;
import GuiElements.ScrollBar;
import GuiElements.Window;
import Listener.TimeListener;

public class TimeRecordingFrame extends Window {
	private static int weight = 1000;
	private static int height = 500;

	private CButton menueButton, backButton;
	private TimeListener timeIstener;
	private ScrollBar scrollBar;
	private DatabaseConnection connectionData;
	private DatabaseOutputTime databaseOutput;

	protected String[] menueButtonNames = { "starten/stoppen"};
	protected String[] backButtonName = {"zurück"};
	protected String[] columnNames = { "Personalnummer", "Start", "Ende", "Datum", "Gesamtzeit" };

	public TimeRecordingFrame(String windowName) {
		super(weight, height, windowName);
		
		timeIstener = new TimeListener(this);
		connectionData = new DatabaseConnection();
		scrollBar = new ScrollBar(this, connectionData);
		databaseOutput = new DatabaseOutputTime(scrollBar, connectionData);

		menueButton = new CButton(timeIstener, 1);
		backButton = new CButton(timeIstener, 1);

		menueButton.createButtonsTimeRecording(20, 20, 180, 40, 40, "posY", menueButtonNames, menueButtonNames);
		for (JButton button : menueButton.getButtons()) {
			add(button);
		}
		
		backButton.createButtonsTimeRecording(20, 400, 120, 30, 0, "", backButtonName, backButtonName);
		for (JButton button : backButton.getButtons()) {
			add(button);
		}

		createScrollBar();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public void createScrollBar() {
		connectionData.connectDatabase();
		scrollBar.createTableTime(5, columnNames);
		databaseOutput.queryData();
	}
	
}