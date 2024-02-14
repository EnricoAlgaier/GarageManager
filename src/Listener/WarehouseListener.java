package Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import CustomerElements.CustomerDeleteIDFrame;
import CustomerElements.DeleteCustomer;
import Database.SearchDatabaseContentWarehouse;
import Database.ServicePartInputToDatabase;
import MainComponents.MainInterface;
import WarehouseElements.CreateServicePart;
import WarehouseElements.ServicePartDeleteFrame;
import WarehouseElements.ServicePartIDCheck;
import WarehouseElements.WarehouseFrame;

public class WarehouseListener implements ActionListener {
	private WarehouseFrame warehouseFrame;
	private SearchDatabaseContentWarehouse searchDatabase;
	private ServicePartDeleteFrame servicePartFrame;
	private ServicePartIDCheck servicePartDeleteCheck;
	private CreateServicePart createServiceFrame;
	private ServicePartInputToDatabase inputDatabase;

	public WarehouseListener(WarehouseFrame warehouseFrame, SearchDatabaseContentWarehouse searchDatabase) {
		this.warehouseFrame = warehouseFrame;
		this.searchDatabase = searchDatabase;
	}
	
	public WarehouseListener( ServicePartDeleteFrame servicePartFrame, ServicePartIDCheck servicePartDeleteCheck) {
		this.servicePartFrame = servicePartFrame;
		this.servicePartDeleteCheck = servicePartDeleteCheck;
	}
	
	public WarehouseListener(CreateServicePart createServiceFrame, ServicePartInputToDatabase inputDatabase) {
		this.createServiceFrame = createServiceFrame;
		this.inputDatabase = inputDatabase;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonID = ((JButton) e.getSource()).getActionCommand();

		if ("Alle Ersatzteile anzeigen".equals(buttonID)) {
			warehouseFrame.removeScrollBarWarehouse(); 
			warehouseFrame.createTabel();

		} else if ("Erstzteile hinzufügen".equals(buttonID)) {
			new CreateServicePart();
			

		} else if ("Ersatzteile ändern".equals(buttonID)) {

		} else if ("Ersatzteile löschen".equals(buttonID)) {
			new ServicePartDeleteFrame();
			
		} else if ("Suchen".equals(buttonID)) {
			searchDatabase.setTextInList(1);
			searchDatabase.searchDatabase();
			
		} else if("backWarehouseFrame".equals(buttonID)) {
			warehouseFrame.dispose();
			new MainInterface();
		}
		
		//servicePartFrame
		if("confirm".equals(buttonID)) {
			servicePartDeleteCheck.setId(1);
			servicePartDeleteCheck.checkID();
			
		} else if("back".equals(buttonID)) {
			servicePartFrame.dispose();
		}
		
		//createServiceFrame
		if("Eintragen".equals(buttonID)) {
			inputDatabase.setNameTextInput(createServiceFrame.getServicePartFieldSize());

			inputDatabase.insertData();
			
		} else if("zurück".equals(buttonID)) {
			createServiceFrame.dispose();
		}
	}

}