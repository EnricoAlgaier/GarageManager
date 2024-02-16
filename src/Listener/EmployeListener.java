package Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import Database.DatabaseConnection;
import Database.EmployeDeleteFromDatabase;
import Database.InputToDatabaseEmploye;
import Database.UpdateEmployeeDatabase;
import EmployeElements.ChangeEmployeeFrame;
import EmployeElements.EmployeCreateFrame;
import EmployeElements.EmployeFrame;
import EmployeElements.FillChangeFrame;
import MainComponents.MainInterface;

public class EmployeListener implements ActionListener {
	private EmployeFrame employeFrame;
	private EmployeCreateFrame employeCreateFrame;
	private InputToDatabaseEmploye inputToDatabase;
	private EmployeDeleteFromDatabase deleteFromDatabase;
	private DatabaseConnection connectionData;
	private FillChangeFrame fillvalueEmployee;
	private ChangeEmployeeFrame changeEmplyoeeFrame;
	private UpdateEmployeeDatabase updateEmployee;
	
	private String messageDialog = "kein Mitarbeiter ausgewählt";

	public EmployeListener(EmployeFrame employeFrame, EmployeDeleteFromDatabase deleteFromDatabase,
			DatabaseConnection connectionData, FillChangeFrame fillvalueEmployee) {
		this.employeFrame = employeFrame;
		this.deleteFromDatabase = deleteFromDatabase;
		this.connectionData = connectionData;
		this.fillvalueEmployee = fillvalueEmployee;
	}

	public EmployeListener(EmployeCreateFrame employeCreateFrame, InputToDatabaseEmploye inputToDatabase,
			EmployeFrame employeFrame) {
		this.employeCreateFrame = employeCreateFrame;
		this.inputToDatabase = inputToDatabase;
		this.employeFrame = employeFrame;
	}

	public EmployeListener(ChangeEmployeeFrame changeEmplyoeeFrame, UpdateEmployeeDatabase updateEmployee) {
		this.changeEmplyoeeFrame = changeEmplyoeeFrame;
		this.updateEmployee = updateEmployee;
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
			if(employeFrame.getEmployeID() == 0) {
				JOptionPane.showMessageDialog(null, messageDialog, "Info", JOptionPane.INFORMATION_MESSAGE);
			}
			fillvalueEmployee = new FillChangeFrame(connectionData, null);
			fillvalueEmployee.getDatabaseValueToTextFields(employeFrame.getEmployeID());

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

		// ChangeEmployee
		if ("change".equals(buttonID)) {
			int employeID = Integer.parseInt(changeEmplyoeeFrame.getIdText());
			
			updateEmployee.setNameTextInput(changeEmplyoeeFrame.getNameSize());
			updateEmployee.setAddressTextInput(changeEmplyoeeFrame.getAddressSize());
			updateEmployee.update(employeID);
			
			if(updateEmployee.getWindowclose() == true) {
				changeEmplyoeeFrame.dispose();
			}

		} else if ("back".equals(buttonID)) {
			changeEmplyoeeFrame.dispose();
		}
	}
}