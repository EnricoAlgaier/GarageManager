package MainComponents;

import javax.swing.JButton;
import GuiElements.CButton;
import GuiElements.Window;
import Listener.ActionListenerMain;

public class MainInterface extends Window{
	private static int weight = 500;
	private static int height = 500;
	private static String windowName = "GarageManager";
	private CButton menueButtons, loginButton;
	private ActionListenerMain actionListener;
	private String[] buttonName = {"Kunden", "Werkstatt", "Lager", "Mitarbeiter", "Zeiterfassung", "Beenden"};
	private String[] loginButtonName = {"Anmelden"};
	private String[] loginButtonID = {"login"};
	
	public MainInterface() {
		super(weight, height, windowName);
		
		actionListener = new ActionListenerMain(this);
		menueButtons = new CButton(actionListener, 6);
		loginButton = new CButton(actionListener, 1);
		
		menueButtons.createButtons(180, 80, 120, 40, 40, "posY", buttonName, buttonName);
		for(JButton button : menueButtons.getButtons()) {
			add(button);
		}
		
		loginButton.createButtons(10, 10, 120, 40, 0, "", loginButtonID, loginButtonName);
		for(JButton button : loginButton.getButtons()) {
			add(button);
		}
		
		close();
		setVisible(true);
	}	
}