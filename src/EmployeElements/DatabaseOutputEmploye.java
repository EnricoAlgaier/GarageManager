package EmployeElements;

import java.sql.ResultSet;
import java.sql.SQLException;

import Database.DatabaseConnection;
import GuiElements.ScrollBar;

public class DatabaseOutputEmploye {
	private DatabaseConnection connectionData;
	private ScrollBar scrollBar;

	public DatabaseOutputEmploye(ScrollBar scrollBar, DatabaseConnection connectionData) {
		this.scrollBar = scrollBar;
		this.connectionData = connectionData;
	}

	public void queryData() {
		try {
			
			ResultSet resultSet = connectionData.statement.executeQuery("SELECT * " + "FROM mitarbeiter ");

			scrollBar.getTableModel().setRowCount(0);

			while (resultSet.next()) {
				int employeID = resultSet.getInt("mitarbeiter.mitarbeiterID");
				String firstname = resultSet.getString("mitarbeiter.vorname");
				String lastname = resultSet.getString("mitarbeiter.nachname");
				String phonennumber = resultSet.getString("mitarbeiter.telefonnummer");

				scrollBar.getTableModel().addRow(new Object[] { employeID, firstname, lastname, phonennumber });
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}