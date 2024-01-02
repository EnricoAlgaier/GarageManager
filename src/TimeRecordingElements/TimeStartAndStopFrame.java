package TimeRecordingElements;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTextField;

import Database.DatabaseConnection;
import Database.TimeRecording;
import GuiElements.CButton;
import GuiElements.CTextField;
import GuiElements.Window;
import Listener.TimeListener;

public class TimeStartAndStopFrame extends Window{
	private static int weight = 300;
	private static int height = 250;
	
	private CTextField employeIDField;
	private CButton okButton;
	private TimeListener timeListener;
	private TimeRecording startTime;
	private DatabaseConnection connectionData;
	
	private List<String> employeID;
	
	protected String[] okButonName = {"starten", "beenden"};
	
	public TimeStartAndStopFrame(String windowName) {
		super(weight, height, windowName);
		
		connectionData = new DatabaseConnection();
		employeIDField = new CTextField(1);
		startTime = new TimeRecording(connectionData, employeIDField);
		timeListener = new TimeListener(this, startTime);
		okButton = new CButton(timeListener, 2);
		employeID = new ArrayList<>();
		
		employeIDField.createTextFields(80, 60, 100, 30, 0, "");
		for(JTextField field : employeIDField.getFields()) {
			add(field);
		}
		
		okButton.createButtonsTimeRecording(20, 100, 100, 30, 110, "posX", okButonName, okButonName);
		for(JButton button : okButton.getButtons()) {
			add(button);
		}
		
		setVisible(true);
	}
	
}