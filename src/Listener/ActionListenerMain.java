package Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import AppointmentElements.AppointmentFrame;
import CustomerElements.MainCustomerFrame;
import DatabaseLogin.DatabaseLoginFrame;
import EmployeElements.EmployeFrame;
import GarageElements.GarageFrame;
import MainComponents.MainInterface;
import TimeRecordingElements.TimeRecordingFrame;
import WarehouseElements.WarehouseFrame;

public class ActionListenerMain implements ActionListener {
	private MainInterface frame;
	private GarageFrame garageFrame;


	public ActionListenerMain(MainInterface frame) {
		this.frame = frame;
	}
	
	public ActionListenerMain(GarageFrame garageFrame) {
		this.garageFrame = garageFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonID = ((JButton) e.getSource()).getActionCommand();

		// Main Button
		if ("Kunden".equals(buttonID)) {
			new MainCustomerFrame(buttonID);
			frame.dispose();

		} else if ("Werkstatt".equals(buttonID)) {
			new GarageFrame(buttonID);
			frame.dispose();

		} else if ("Lager".equals(buttonID)) {
			new WarehouseFrame(buttonID);
			frame.dispose();

		} else if ("Termine".equals(buttonID)) {
			new AppointmentFrame(buttonID);
			frame.dispose();

		} else if ("Mitarbeiter".equals(buttonID)) {
			new EmployeFrame(buttonID);
			frame.dispose();

		} else if ("Zeiterfassung".equals(buttonID)) {
			new TimeRecordingFrame(buttonID);

		} else if ("Beenden".equals(buttonID)) {
			System.exit(0);
		} 
		
		if("login".equals(buttonID)) {
			new DatabaseLoginFrame();
			
		}
		
		if("backGarage".equals(buttonID)) {
			garageFrame.dispose();
			new MainInterface();
		}
		
	}
}