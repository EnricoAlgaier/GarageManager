package GarageElements;

import java.sql.ResultSet;
import java.sql.SQLException;

import Database.DatabaseConnection;
import GuiElements.ScrollBar;

public class DatabaseOutputOrders {
	private DatabaseConnection connectionData;
	private ScrollBar scrollBar;

	public DatabaseOutputOrders(ScrollBar scrollBar, DatabaseConnection connectionData) {
		this.scrollBar = scrollBar;
		this.connectionData = connectionData;
	}

	public void queryData() {
	    try {
	        ResultSet resultSet = connectionData.statement.executeQuery(
	                "SELECT auftrag.Auftrags_NR, mitarbeiter.Nachname, kundenfahrzeug.Fahrzeug, auftrag.kd_nr "
	                        + "FROM auftrag "
	                        + "JOIN mitarbeiter ON auftrag.mitarbeiterID = mitarbeiter.mitarbeiterID "
	                        + "JOIN kundenfahrzeug ON auftrag.kd_nr = kundenfahrzeug.kd_nr");

	        scrollBar.getTableModel().setRowCount(0);

	        while (resultSet.next()) {
	            int ordersNumber = resultSet.getInt("Auftrags_NR");
	            String employeeLastName = resultSet.getString("Nachname");
	            String customerVehicle = resultSet.getString("Fahrzeug");
	            int customerID = resultSet.getInt("kd_nr");

	            scrollBar.getTableModel().addRow(new Object[]{ordersNumber, employeeLastName, customerVehicle, customerID});
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}