package AppointmentElements;

import GuiElements.Window;

public class AppointmentFrame extends Window{

	protected static int weight = 500;
	protected static int height = 500;
	
	public AppointmentFrame(String windowName) {
		super(weight, height, windowName);
		
		setVisible(true);
	}
}