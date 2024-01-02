package Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import Database.DatabaseConnection;
import Database.EmployeDeleteFromDatabase;
import Database.InputToDatabaseEmploye;
import EmployeElements.EmployeCreateFrame;
import EmployeElements.EmployeFrame;
import MainComponents.MainInterface;

public class EmployeListener implements ActionListener {
	private EmployeFrame employeFrame;
	private MainInterface mainInterface;
	private EmployeCreateFrame employeCreateFrame;
	private InputToDatabaseEmploye inputToDatabase;
	private EmployeDeleteFromDatabase deleteFromDatabase;
	private DatabaseConnection connectionData;

	public EmployeListener(EmployeFrame employeFrame, EmployeDeleteFromDatabase deleteFromDatabase,
			DatabaseConnection connectionData) {
		this.employeFrame = employeFrame;
		this.deleteFromDatabase = deleteFromDatabase;
		this.connectionData = connectionData;
	}

	public EmployeListener(EmployeCreateFrame employeCreateFrame, InputToDatabaseEmploye inputToDatabase,
			EmployeFrame employeFrame) {
		this.employeCreateFrame = employeCreateFrame;
		this.inputToDatabase = inputToDatabase;
		this.employeFrame = employeFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonID = ((JButton) e.getSource()).getActionCommand();

		// employeFrame
		if ("Mitarbeiter hinzufügen".equals(buttonID)) {
			new EmployeCreateFrame(buttonID);

			if (inputToDatabase.getCloseInput() == true) {
				employeFrame.createOrderList();
			}

		} else if ("Mitarbeiterdaten ändern".equals(buttonID)) {
			System.out.println(buttonID);

		} else if ("Mitarbeiter entfernen".equals(buttonID)) {
			deleteFromDatabase = new EmployeDeleteFromDatabase(employeFrame.getEmployeID(), connectionData);

			if (deleteFromDatabase.getClose() == true) {
				employeFrame.createOrderList();
			}

		} else if ("backToMain".equals(buttonID)) {
			employeFrame.dispose();
			new MainInterface();
		}

		// employeCreateFrame
		if ("add".equals(buttonID)) {
			inputToDatabase.setAddressTextInput(employeCreateFrame.getAddressSize());
			inputToDatabase.setNameTextInput(employeCreateFrame.getNameSize());
			inputToDatabase.insertData();

			if (inputToDatabase.getCloseInput() == true) {
				employeCreateFrame.dispose();
			}

		} else if ("close".equals(buttonID)) {
			employeCreateFrame.dispose();
		}
	}
}