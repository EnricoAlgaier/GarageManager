package Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import CustomerElements.DatabaseOutputCustomer;
import GuiElements.CTextField;
import GuiElements.ScrollBar;
import WarehouseElements.DatabaseOutputWarehouse;

public class SearchDatabaseContentWarehouse {
	private DatabaseConnection connectionData;
	private ScrollBar scrollBar;
	private CTextField searchValue;
	private String searchTerm;
	private DatabaseOutputWarehouse databaseOutput;
	
	private List<String>searchText;
	
	public SearchDatabaseContentWarehouse(DatabaseConnection connectionData, ScrollBar scrollBar, CTextField searchValue) {
		this.connectionData = connectionData;
		this.scrollBar = scrollBar;
		this.searchValue = searchValue;
		databaseOutput = new DatabaseOutputWarehouse(scrollBar, connectionData);
	}

	public void searchDatabase() {
		searchTerm = searchText.get(0);
		
		if(searchTerm.isEmpty()) {
			databaseOutput.queryData();
		}
		
		try {    
			connectionData.connectDatabase();
	        String query = "SELECT * FROM lager WHERE teil LIKE ? OR teileID LIKE ? ";
	        PreparedStatement preparedStatement = connectionData.connection.prepareStatement(query);

	        for (int i = 1; i <= 2; i++) {
	            preparedStatement.setString(i, "%" + searchTerm + "%");
	        }

	        ResultSet resultSet = preparedStatement.executeQuery();

	        scrollBar.getTableModel().setRowCount(0);

	        while (resultSet.next()) {
	            
	        	int servicePartID = resultSet.getInt("teileID");
	            String servicePart = resultSet.getString("teil");
	            String amount = resultSet.getString("bestand");
	            float price = resultSet.getFloat("preis");

	            scrollBar.getTableModel().addRow(new Object[]{servicePartID, servicePart, amount, price});
	        }

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setTextInList(int number) {
		searchValue.textInput(number);
		searchText = new ArrayList<>();
		searchText = searchValue.getText();	
	}

}