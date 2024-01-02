package WarehouseElements;

import java.sql.ResultSet;
import java.sql.SQLException;

import Database.DatabaseConnection;
import GuiElements.ScrollBar;

public class DatabaseOutputWarehouse {
	private DatabaseConnection connectionData;
	private ScrollBar scrollBar;

	public DatabaseOutputWarehouse(ScrollBar scrollBar, DatabaseConnection connectionData) {
		this.scrollBar = scrollBar;
		this.connectionData = connectionData;
	}

	public void queryData() {
		try {
			ResultSet resultSet = connectionData.statement
					.executeQuery("SELECT lager.teileID, lager.teil, lager.bestand, lager.preis " + "FROM lager ");

			scrollBar.getTableModel().setRowCount(0);

			while (resultSet.next()) {
				int servicePartID = resultSet.getInt("teileID");
				String servicePart = resultSet.getString("teil");
				String amount = resultSet.getString("bestand");
				float price = resultSet.getFloat("preis");

				scrollBar.getTableModel().addRow(new Object[] { servicePartID, servicePart, amount, price });
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}