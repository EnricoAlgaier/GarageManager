package WarehouseElements;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import Database.DatabaseConnection;
import Database.WarehouseDeleteIntoDatabase;
import GuiElements.CTextField;

public class ServicePartIDCheck {
	private CTextField idCheckField;
	private DatabaseConnection connection;
	private WarehouseDeleteIntoDatabase deleteServicePart;
	private int customerID;
	
	private List<String> id;
	private boolean idValueTrue;
	
	public ServicePartIDCheck(DatabaseConnection connection, CTextField idCheckField) {
		this.idCheckField = idCheckField;
		this.connection = connection;
	}
	
	public boolean checkID() {
		for (String value : id) {
			if (!isNumeric(value)) {
				JOptionPane.showMessageDialog(null, "Die Teilenummer darf nur aus Zahlen bestehen.", "Achtung",
						JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
		}
		customerID = Integer.parseInt(id.get(0));
		new WarehouseDeleteIntoDatabase(customerID, connection);
		return true;
	}

	private boolean isNumeric(String check) {
		try {
			Integer.parseInt(check);
			idValueTrue = true;
			return true;

		} catch (NumberFormatException e) {
			return false;
		}
	}

	public boolean getIdTrue() {
		return idValueTrue;
	}

	public void setId(int number) {
		idCheckField.textInput(number);
		id = new ArrayList<>();
		id = idCheckField.getText();
	}
}