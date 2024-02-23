package DatabaseLogin;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import GuiElements.CButton;
import GuiElements.CLabel;
import GuiElements.CTextField;
import GuiElements.Window;
import Listener.LoginListener;

public class DatabaseLoginFrame extends Window{
	private final static int weight = 500;
	private final static int height = 300;
	private final static String windowName = "Anmeldung"; 
	
	private CButton menuButton;
	private CTextField inputFields, passwordField;
	private CLabel inputLabel;
	private LoginListener loginListener;
	private LoginInformation loginInformation;
	
	private String[] buttonNames = {"Login", "Abbrechen"};
	private String[] buttonID = {"login", "cancel"};
	private String[] labelNames = {"Datenbankname", "Benutzername", "Password"};
	
	private int inputfieldSize = 2;
	
	public DatabaseLoginFrame() {
		super(weight, height, windowName);
		
		inputFields = new CTextField(inputfieldSize);
		passwordField = new CTextField(1);
		loginInformation = new LoginInformation(inputFields);
		loginListener = new LoginListener(this, loginInformation);
		
		menuButton = new CButton(loginListener, 2);
		inputLabel = new CLabel(3);
		
		menuButton.createButtonsLogin(100, 120, 120, 40, 130, "posX", buttonID, buttonNames);
		for(JButton buttons : menuButton.getButtons()) {
			add(buttons);
		}
		
		inputFields.createTextFields(30, 60, 100, 30, 120, "posX");
		for(JTextField fields : inputFields.getFields()) {
			add(fields);
		}
		
		passwordField.createPasswordField(270, 60, 100, 30, 0, "");
		for(JPasswordField fields : passwordField.getPasswordField()) {
			add(fields);
		}
		
		inputLabel.createLabels(30, 35, 100, 30, 120, "posX", labelNames);
		for(JLabel labels : inputLabel.getLabels()) {
			add(labels);
		}
		
		setVisible(true);
	}
	
	public int getInputFieldSize() {
		return inputfieldSize;
	}
}