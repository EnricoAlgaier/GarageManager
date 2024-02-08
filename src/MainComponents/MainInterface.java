package MainComponents;

import javax.swing.JButton;
import GuiElements.CButton;
import GuiElements.Window;
import Listener.ActionListenerMain;

public class MainInterface extends Window{
	private static int weight = 500;
	private static int height = 500;
	private static String windowName = "GarageManager";
	private CButton menueButtons;
	private ActionListenerMain actionListener;
	protected String[] buttonName = {"Kunden", "Werkstatt", "Lager", "Mitarbeiter", "Zeiterfassung", "Beenden"};
	
	public MainInterface() {
		super(weight, height, windowName);
		
		actionListener = new ActionListenerMain(this);
		menueButtons = new CButton(actionListener, 6);
		
		menueButtons.createButtons(180, 80, 120, 40, 40, "posY", buttonName, buttonName);
		for(JButton button : menueButtons.getButtons()) {
			add(button);
		}
		
		close();
		setVisible(true);
	}	
}