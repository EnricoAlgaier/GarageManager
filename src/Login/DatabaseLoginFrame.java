package Login;

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
	private CTextField databseValue, userNameValue, passwordField;
	private CLabel inputLabel;
	private LoginListener loginListener;
	private LoginInformation loginInformation;
	
	private String[] buttonNames = {"Login", "Abbrechen"};
	private String[] buttonID = {"login", "cancel"};
	private String[] labelNames = {"Benutzername", "Password"};
	
	private int fieldSize = 1;
	
	public DatabaseLoginFrame() {
		super(weight, height, windowName);
		
		userNameValue = new CTextField(fieldSize);
		passwordField = new CTextField(fieldSize);
		
		loginInformation = new LoginInformation(databseValue, userNameValue, passwordField);
		loginListener = new LoginListener(this, loginInformation);
		
		menuButton = new CButton(loginListener, 2);
		inputLabel = new CLabel(2);
		
		menuButton.createButtonsLogin(100, 120, 120, 40, 130, "posX", buttonID, buttonNames);
		for(JButton buttons : menuButton.getButtons()) {
			add(buttons);
		}
		
		userNameValue.createTextFields(30, 60, 100, 30,0, "");
		for(JTextField fields : userNameValue.getFields()) {
			add(fields);
		}
		
		for(JPasswordField fields : passwordField.getPasswordField()) {
			add(fields);
		}
		
		inputLabel.createLabels(30, 35, 100, 30, 120, "posX", labelNames);
		for(JLabel labels : inputLabel.getLabels()) {
			add(labels);
		}
		
		setVisible(true);
	}
	
	public int getFieldSize() {
		return fieldSize;
	}

}