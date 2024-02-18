package Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;
import EmployeElements.ChangeEmployeeFrame;
import GuiElements.CTextField;

public class UpdateWarehouseDatabase {
	private DatabaseConnection connection;
	private CTextField newWarehouseValue;

	private List<String> warehouseText;
	
	private boolean windowClose;

	public UpdateWarehouseDatabase(DatabaseConnection connection, CTextField newWarehouseValue) {
		this.connection = connection;
		this.newWarehouseValue = newWarehouseValue;
		
		windowClose = false;
	}

	public void update(int warehouseID) {
		try {
			connection.connectDatabase();

			String updateEmployee = "UPDATE lager SET teil = ?, bestand = ?, preis = ? WHERE teileID = "
					+ warehouseID;
			
			String servicePart = warehouseText.get(0);
			String inventory = warehouseText.get(1);
			String price = warehouseText.get(2);

			PreparedStatement updateCustomerStatement = connection.connection.prepareStatement(updateEmployee);
			updateCustomerStatement.setString(1, servicePart);
			updateCustomerStatement.setString(2, inventory);
			updateCustomerStatement.setString(3, price);
			updateCustomerStatement.executeUpdate();

			updateCustomerStatement.close();
			
			windowClose = true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setWarehouseValues(int nameSize) {
		newWarehouseValue.textInput(nameSize);
		warehouseText = new ArrayList<>();
		warehouseText = newWarehouseValue.getText();
	}
	
	public boolean getWindowclose() {
		return windowClose;
	}
}