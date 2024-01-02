package CustomerElements;

import java.sql.ResultSet;
import java.sql.SQLException;

import Database.DatabaseConnection;
import GuiElements.ScrollBar;

public class DatabaseOutputCustomer{
	private DatabaseConnection connectionData;
	private ScrollBar scrollBar;

	public DatabaseOutputCustomer(ScrollBar scrollBar, DatabaseConnection connectionData) {
		this.scrollBar = scrollBar;
		this.connectionData = connectionData;
	}

	public void queryData() {
		try {
			ResultSet resultSet = connectionData.statement.executeQuery("SELECT * FROM kunden");
			
			scrollBar.getTableModel().setRowCount(0);
			
			
			while (resultSet.next()) {
				int id = resultSet.getInt("kd_nr");
				String firstname = resultSet.getString("vorname");
				String lastname = resultSet.getString("nachname");
				String phoneNumber = resultSet.getString("telefonnummer");

				scrollBar.getTableModel().addRow(new Object[] { id, firstname, lastname, phoneNumber});
			}

		} catch (SQLException e) {
			e.printStackTrace();

		}
	}
}