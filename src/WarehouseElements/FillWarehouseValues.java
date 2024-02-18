package WarehouseElements;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import CustomerElements.ChangeCustomer;
import Database.DatabaseConnection;
import GuiElements.CTextField;

public class FillWarehouseValues {

	private DatabaseConnection connection;
	private ChangeWarehouseFrame warehouseChangeFrame;

	private int warehouseID;
	private boolean idValueTrue;

	private List<String> warehouseText;

	public FillWarehouseValues() {
		warehouseText = new ArrayList<>();
	}

	public void getDatabaseValueToTextFields(int warehouseID) {
		try {
			connection = new DatabaseConnection();
			connection.connectDatabase();
			
			System.out.println("id ist: " + warehouseID);

			String warehouse = "SELECT lager.teileID, lager.teil, lager.bestand, lager.preis " + "FROM lager "
					+ "WHERE lager.teileID = " + warehouseID;

			PreparedStatement preparedStatement = connection.connection.prepareStatement(warehouse);
			

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					
					resultSet.getInt("teileID");
					String servicePart = resultSet.getString("teil");
					String inventory = resultSet.getString("bestand");
					String price = resultSet.getString("preis");

					warehouseText.clear();
					warehouseText.add(servicePart);
					warehouseText.add(inventory);
					warehouseText.add(price);
					
					//idText.add(String.valueOf(warehouseID));
					
					for(int i=0;i<warehouseText.size(); i++) {
						System.out.println(warehouseText.get(i));
					}
					warehouseChangeFrame = new ChangeWarehouseFrame(this, String.valueOf(warehouseID));

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<String> getWarehouseText() {
		return warehouseText;
	}

}