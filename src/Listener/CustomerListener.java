package Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import CustomerElements.ChangeCustomer;
import CustomerElements.CreateCustomer;
import CustomerElements.CustomerDeleteIDFrame;
import CustomerElements.MainCustomerFrame;
import CustomerElements.DeleteCustomer;
import CustomerElements.FillCustomerValues;
import Database.DatabaseConnection;
import Database.InputToDatabase;
import Database.SearchDatabaseContent;
import Database.UpdateDatabase;
import MainComponents.MainInterface;

public class CustomerListener implements ActionListener {
	private InputToDatabase inputDatabase;
	private CreateCustomer createCustomer;
	private DeleteCustomer deleteCustomerIdCheck;
	private ChangeCustomer changeCustomerFrame;
	private UpdateDatabase updateValues;
	private CustomerDeleteIDFrame customerDeleteFrame;
	private MainCustomerFrame customerMainFrame;
	private SearchDatabaseContent searchDatabase;
	private FillCustomerValues fillCustomerValues;
	private DatabaseConnection connectionData;

	protected int textFieldLength = 1;

	public CustomerListener(CreateCustomer createCustomer, InputToDatabase inputDatabase,
			ChangeCustomer changeCustomerFrame, UpdateDatabase updateValues) {

		this.createCustomer = createCustomer;
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
			new CreateCustomer();

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
			fillCustomerValues = new FillCustomerValues(changeCustomerFrame);
			fillCustomerValues.getDatabaseValueToTextFields(customerMainFrame.getCustomerID());

		} else if ("backCustomer".equals(buttonID)) {
			new MainInterface();
			customerMainFrame.dispose();
		}

		// Customer Create Frame
		if ("Eintragen".equals(buttonID)) {

			inputDatabase.setNameTextInput(createCustomer.getNameSize());
			inputDatabase.setAddressTextInput(createCustomer.getAddressSize());
			inputDatabase.setVehicleTextInput(createCustomer.getVehicleSize());

			inputDatabase.insertData();

			if (inputDatabase.getCloseInput() == true) {
				customerMainFrame.createScrollBar();
				createCustomer.dispose();
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

			changeCustomerFrame.dispose();

		} else if ("zurück change".equals(buttonID)) {
			changeCustomerFrame.dispose();
		}
	}
}