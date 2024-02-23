package Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import CustomerElements.ChangeCustomer;
import CustomerElements.CreateCustomer;
import CustomerElements.CustomerDeleteIDFrame;
import CustomerElements.MainCustomerFrame;
import CustomerElements.DeleteCustomer;
import CustomerElements.FillCustomerValues;
import Database.DatabaseConnection;
import Database.InputToDatabaseCustomer;
import Database.SearchDatabaseContent;
import Database.UpdateDatabase;
import MainComponents.MainInterface;

public class CustomerListener implements ActionListener {
	private InputToDatabaseCustomer inputDatabase;
	private CreateCustomer createCustomer;
	private DeleteCustomer deleteCustomerIdCheck;
	private ChangeCustomer changeCustomerFrame;
	private UpdateDatabase updateValues;
	private CustomerDeleteIDFrame customerDeleteFrame;
	private MainCustomerFrame customerMainFrame;
	private SearchDatabaseContent searchDatabase;
	private FillCustomerValues fillCustomerValues;
	private DatabaseConnection connectionData;
	
	private String informationText = "Keine Kunde ausgewählt";

	protected int textFieldLength = 1;

	public CustomerListener(MainCustomerFrame customerMainFrame, InputToDatabaseCustomer inputDatabase,
			ChangeCustomer changeCustomerFrame, UpdateDatabase updateValues) {

		this.customerMainFrame = customerMainFrame;
		this.inputDatabase = inputDatabase;
		this.updateValues = updateValues;
		this.changeCustomerFrame = changeCustomerFrame;
	}

	public CustomerListener(FillCustomerValues fillCustomerValues) {
		this.fillCustomerValues = fillCustomerValues;
	}

	public CustomerListener(CustomerDeleteIDFrame customerDeleteFrame, DeleteCustomer deleteCustomerIdCheck) {
		this.deleteCustomerIdCheck = deleteCustomerIdCheck;
		this.customerDeleteFrame = customerDeleteFrame;
	}

	public CustomerListener(MainCustomerFrame customerMainFrame, SearchDatabaseContent searchDatabase) {
		this.customerMainFrame = customerMainFrame;
		this.searchDatabase = searchDatabase;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonID = ((JButton) e.getSource()).getActionCommand();

		// Customer button
		if ("Kunde hinzufügen".equals(buttonID)) {
			customerMainFrame.createCustomerCreateFrame();

		} else if ("Alles anzeigen".equals(buttonID)) {
			if (customerMainFrame.getVisible() == true) {
				customerMainFrame.removeScrollBar();
			}

			customerMainFrame.createScrollBar();

		} else if ("Kunde suchen".equals(buttonID)) {
			searchDatabase.setTextInList(1);
			searchDatabase.searchDatabase();

		} else if ("Kunde entfernen".equals(buttonID)) {
			new CustomerDeleteIDFrame();

		} else if ("Kundendaten ändern".equals(buttonID)) {
			if(customerMainFrame.getCustomerID() == 0) {
				JOptionPane.showMessageDialog(null, informationText, "Info", JOptionPane.INFORMATION_MESSAGE);
			}
			
			fillCustomerValues = new FillCustomerValues(changeCustomerFrame);
			fillCustomerValues.getDatabaseValueToTextFields(customerMainFrame.getCustomerID());

		} else if ("backCustomer".equals(buttonID)) {
			new MainInterface();
			customerMainFrame.dispose();
		}

		// Customer Create Frame
		if ("Eintragen".equals(buttonID)) {

			inputDatabase.setNameTextInput(customerMainFrame.getCreateFrame().getNameSize());
			inputDatabase.setAddressTextInput(customerMainFrame.getCreateFrame().getAddressSize());
			inputDatabase.setVehicleTextInput(customerMainFrame.getCreateFrame().getVehicleSize());

			inputDatabase.insertData();

			if (inputDatabase.getCloseInput() == true) {
				customerMainFrame.removeScrollBar();
				customerMainFrame.createScrollBar();
				customerMainFrame.getCreateFrame().dispose();
			}

		} else if ("zurück".equals(buttonID)) {
			System.out.println(buttonID);
			createCustomer.dispose();
		}

		// CustomerDelete
		if ("confirm".equals(buttonID)) {
			deleteCustomerIdCheck.setId(textFieldLength);
			deleteCustomerIdCheck.checkID();

			if (deleteCustomerIdCheck.getClose() == true) {
				customerDeleteFrame.dispose();
			}
		}

		// ChangeCustomerFrame
		if ("ändern".equals(buttonID)) {
			int customerID = Integer.parseInt(changeCustomerFrame.getIdText());
			
			updateValues.setNameTextInput(changeCustomerFrame.getNameSize());
			updateValues.setAddressTextInput(changeCustomerFrame.getAddressSize());
			updateValues.setVehicleTextInput(changeCustomerFrame.getVehicleSize());
			updateValues.update(customerID);

			if (updateValues.getUsed() == true) {
				JOptionPane.showMessageDialog(null, "Erfolgreich geändert", "Info", JOptionPane.INFORMATION_MESSAGE);
				changeCustomerFrame.dispose();
			}

		} else if ("zurück change".equals(buttonID)) {
			changeCustomerFrame.dispose();
		}
	}
}