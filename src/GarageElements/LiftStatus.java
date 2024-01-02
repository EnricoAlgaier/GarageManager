package GarageElements;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import GuiElements.CButton;
import GuiElements.CLabel;
import GuiElements.CTextField;
import GuiElements.Window;
import Listener.OrdersListener;

public class LiftStatus extends Window {
	protected static int weight = 550;
	protected static int height = 500;
	
	private OrdersListener actionListener;

	private CTextField liftFieldLeft, liftFieldRight;
	private CLabel liftLeftLabel, liftRightLabel;
	private CButton close;

	protected String[] liftLeftLabelNames = {"Bühne 1", "Bühne 2", "Bühne 3", "Bühne 4", "Bühne 5"};
	protected String[] liftRightLabelNames = {"Bühne 6", "Bühne 7", "Bühne 8", "Bühne 9", "Bühne 10"};
	protected String[] closeButtonName = {"schließen"};

	public LiftStatus(String windowName) {
		super(weight, height, windowName);
		
		liftFieldLeft = new CTextField(5);
		liftFieldRight = new CTextField(5);
		liftLeftLabel = new CLabel(5);
		liftRightLabel = new CLabel(5);
		
		actionListener = new OrdersListener(this);
		close = new CButton(actionListener, 1); 
		
		liftFieldLeft.createTextFields(150, 20, 40, 40, 80, "posY");
		for(JTextField fields : liftFieldLeft.getFields()) {
			add(fields);
			fields.setEnabled(false);
		}
		
		liftFieldRight.createTextFields(350, 20, 40, 40, 80, "posY");
		for(JTextField fields : liftFieldRight.getFields()) {
			add(fields);
			fields.setEnabled(false);
		}
		

		liftLeftLabel.createLabels(90, 20, 70, 30, 80, "posY", liftLeftLabelNames);
		for(JLabel label  : liftLeftLabel.getLabels()) {
			add(label);
		}
		
		liftRightLabel.createLabels(400, 20, 70, 30, 80, "posY", liftRightLabelNames);
		for(JLabel label : liftRightLabel.getLabels()) {
			add(label);
		}
		
		close.createButtonsOrders(20, 420, 100, 30, 0, "", closeButtonName, closeButtonName);
		for(JButton button : close.getButtons()) {
			add(button);
		}
		
		setVisible(true);
	}

}