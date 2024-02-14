package Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import CustomerElements.ChangeCustomer;
import CustomerElements.CheckCustomerId;
import CustomerElements.CreateCustomerFrame;
import CustomerElements.CustomerDeleteIDFrame;
import CustomerElements.MainCustomerFrame;
import CustomerElements.CustomerIDQuery;
import CustomerElements.DeleteCustomer;
import Database.InputToDatabase;
import Database.SearchDatabaseContent;
import Database.UpdateDatabase;
import MainComponents.MainInterface;

public class CustomerListener implements ActionListener {
	private InputToDatabase inputDatabase;
	private CreateCustomerFrame createCustomerFrame;
	private CustomerIDQuery customerIdFrame;
	private CheckCustomerId customerIDCheck;
	private DeleteCustomer deleteCustomerIdCheck;
	private ChangeCustomer changeCustomerFrame;
	private UpdateDatabase updateValues;
	private CustomerDeleteIDFrame customerDeleteFrame;
	private MainCustomerFrame customerMainFrame;
	private SearchDatabaseContent searchDatabase;
	protected int textFieldLength = 1;

	public CustomerListener(CreateCustomerFrame createCustomerFrame, InputToDatabase inputDatabase,
			ChangeCustomer changeCustomerFrame, CustomerIDQuery customerIdFrame, CheckCustomerId customerIDCheck,
			UpdateDatabase updateValues) {

		this.createCustomerFrame = createCustomerFrame;
		this.inputDatabase = inputDatabase;
		this.customerIdFrame = customerIdFrame;
		this.customerIDCheck = customerIDCheck;
		this.updateValues = updateValues;
		this.changeCustomerFrame = changeCustomerFrame;

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
					new CreateCustomerFrame();

				} else if ("Alles anzeigen".equals(buttonID)) {
					if(customerMainFrame.getVisible() == true) {
						customerMainFrame.removeScrollBar();
					}

					customerMainFrame.createScrollBar();

				} else if ("Kunde suchen".equals(buttonID)) {
					searchDatabase.setTextInList(1);
					searchDatabase.searchDatabase();

				} else if ("Kunde entfernen".equals(buttonID)) {
					new CustomerDeleteIDFrame();

				} else if ("Kundendaten ändern".equals(buttonID)) {
					new CustomerIDQuery();

				} else if ("backCustomer".equals(buttonID)) {
					new MainInterface();
					customerMainFrame.dispose();

				} 
		
		// Customer Create Frame
		if ("Eintragen".equals(buttonID)) {

			inputDatabase.setNameTextInput(createCustomerFrame.getNameSize());
			inputDatabase.setAddressTextInput(createCustomerFrame.getAddressSize());
			inputDatabase.setVehicleTextInput(createCustomerFrame.getVehicleSize());

			inputDatabase.insertData();

			if (inputDatabase.getCloseInput() == true) {
				customerMainFrame.createScrollBar();
				createCustomerFrame.dispose();
			}

		} else if ("zurück".equals(buttonID)) {
			createCustomerFrame.dispose();
		}

		// CustomerIDFrame
		if ("bestätigen".equals(buttonID)) {

			customerIDCheck.setId(textFieldLength);
			customerIDCheck.getDatabaseValueToTextFields();

			if (customerIDCheck.getIdTrue() == true) {
				customerIdFrame.dispose();
			}
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
			System.out.println(buttonID);
			updateValues.setCustomerID(textFieldLength);
			updateValues.setNameTextInput(changeCustomerFrame.getNameSize());
			updateValues.setAddressTextInput(changeCustomerFrame.getAddressSize());
			updateValues.setVehicleTextInput(changeCustomerFrame.getVehicleSize());
			updateValues.update();
			changeCustomerFrame.dispose();

		} else if ("zurück change".equals(buttonID)) {
			changeCustomerFrame.dispose();
		}
	}
}