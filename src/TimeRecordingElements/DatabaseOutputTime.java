package TimeRecordingElements;

import java.sql.ResultSet;
import java.sql.SQLException;

import Database.DatabaseConnection;
import GuiElements.ScrollBar;

public class DatabaseOutputTime{
	private DatabaseConnection connectionData;
	private ScrollBar scrollBar;

	public DatabaseOutputTime(ScrollBar scrollBar, DatabaseConnection connectionData) {
		this.scrollBar = scrollBar;
		this.connectionData = connectionData;
	}

	public void queryData() {
		try {
			ResultSet resultSet = connectionData.statement.executeQuery("SELECT * FROM zeiterfassung");
			
			scrollBar.getTableModel().setRowCount(0);
			
			
			while (resultSet.next()) {
				int employeID = resultSet.getInt("mitarbeiterID");
				String starTime = resultSet.getString("StartZeit");
				String endTime = resultSet.getString("EndZeit");
				String date = resultSet.getString("datum");
				String entire = resultSet.getString("gesamt");

				scrollBar.getTableModel().addRow(new Object[] { employeID, starTime, endTime, date, entire});
			}

		} catch (SQLException e) {
			e.printStackTrace();

		}
	}
}