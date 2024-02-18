package Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import CustomerElements.CustomerDeleteIDFrame;
import CustomerElements.DeleteCustomer;
import Database.SearchDatabaseContentWarehouse;
import Database.ServicePartInputToDatabase;
import Database.UpdateWarehouseDatabase;
import MainComponents.MainInterface;
import WarehouseElements.ChangeWarehouseFrame;
import WarehouseElements.CreateServicePart;
import WarehouseElements.FillWarehouseValues;
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
	private ChangeWarehouseFrame changeFrame;
	private FillWarehouseValues fillWarehouseValues;
	private UpdateWarehouseDatabase updateWarehouseDatabase;

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
	
	public WarehouseListener(ChangeWarehouseFrame changeFrame, UpdateWarehouseDatabase updateWarehouseDatabase) {
		this.changeFrame = changeFrame;
		this.updateWarehouseDatabase = updateWarehouseDatabase;
		
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
			fillWarehouseValues = new FillWarehouseValues();
			fillWarehouseValues.getDatabaseValueToTextFields(warehouseFrame.getWarehouseID());

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
		
		//changeWarehouse
		if("change".equals(buttonID)) {
			int warehouseID = Integer.parseInt(changeFrame.getWarehouseID());
			
			updateWarehouseDatabase.setWarehouseValues(changeFrame.getNameSize());
			updateWarehouseDatabase.update(warehouseID);
			
			if(updateWarehouseDatabase.getWindowclose() == true) {
				JOptionPane.showMessageDialog(null, "Erfolgreich geändert", "Info", JOptionPane.INFORMATION_MESSAGE);
				changeFrame.dispose();
			}
			
		} else if("cancel".equals(buttonID)) {
			changeFrame.dispose();
		}
	}

}