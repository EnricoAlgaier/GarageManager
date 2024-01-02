package GarageElements;

import java.sql.ResultSet;
import java.sql.SQLException;

import Database.DatabaseConnection;
import GuiElements.ScrollBar;

public class DatabaseOutputGarage {
	private DatabaseConnection connectionData;
	private ScrollBar scrollBar;

	public DatabaseOutputGarage(ScrollBar scrollBar, DatabaseConnection connectionData) {
		this.scrollBar = scrollBar;
		this.connectionData = connectionData;
	}

	public void queryData() {
	    try {
	        ResultSet resultSet = connectionData.statement.executeQuery(
	                "SELECT mitarbeiter.nachname, kundenfahrzeug.fahrzeug, repstatus.status, kunden.nachname, auftrag.start_uhrzeit, auftrag.end_uhrzeit "
	                        + "FROM mitarbeiter "
	                        + "JOIN repstatus ON mitarbeiter.mitarbeiterID = repstatus.mitarbeiterID "
	                        + "JOIN kundenfahrzeug ON repstatus.kd_nr = kundenfahrzeug.kd_nr "
	                        + "JOIN kunden ON kundenfahrzeug.kd_nr = kunden.kd_nr "
	                        + "JOIN auftrag ON kunden.kd_nr = auftrag.kd_nr");

	        scrollBar.getTableModel().setRowCount(0);

	        while (resultSet.next()) {
	            String lastname = resultSet.getString("mitarbeiter.nachname");
	            String vehicle = resultSet.getString("fahrzeug");
	            String status = resultSet.getString("status");
	            String customerName = resultSet.getString("kunden.nachname"); 
	            String startTime = resultSet.getString("auftrag.start_uhrzeit");
	            String endTime = resultSet.getString("auftrag.end_uhrzeit");

	            scrollBar.getTableModel().addRow(new Object[]{lastname, customerName, vehicle, status, startTime, endTime});
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}