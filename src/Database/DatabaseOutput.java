package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import GuiElements.ScrollBar;
 
public class DatabaseOutput{
	private DatabaseConnection connectionData;
	private ScrollBar scrollBar;

	public DatabaseOutput(ScrollBar scrollBar, DatabaseConnection connectionData) {
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

				scrollBar.getTableModel().addRow(new Object[] { id, firstname, lastname});
			}

		} catch (SQLException e) {
			e.printStackTrace();

		}
	}
}