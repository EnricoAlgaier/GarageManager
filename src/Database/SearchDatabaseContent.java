package Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import CustomerElements.DatabaseOutputCustomer;
import GuiElements.CTextField;
import GuiElements.ScrollBar;

public class SearchDatabaseContent {
	private DatabaseConnection connectionData;
	private ScrollBar scrollBar;
	private CTextField searchValue;
	private String searchTerm;
	private DatabaseOutputCustomer databseOutput;
	
	private List<String>searchText;
	
	protected String[] columnNames = {"Kd-Nr.", "Vorname", "Nachname", "Telefonnummer"};

	public SearchDatabaseContent(DatabaseConnection connectionData, ScrollBar scrollBar, CTextField searchValue) {
		this.connectionData = connectionData;
		this.scrollBar = scrollBar;
		this.searchValue = searchValue;
		databseOutput = new DatabaseOutputCustomer(scrollBar, connectionData);
	}

	public void searchDatabase() {
		searchTerm = searchText.get(0);
		
		if(searchTerm.isEmpty()) {
			databseOutput.queryData();
		}
		
		try {    
			connectionData.connectDatabase();
	        String query = "SELECT * FROM kunden WHERE vorname LIKE ? OR nachname LIKE ? ";
	        PreparedStatement preparedStatement = connectionData.connection.prepareStatement(query);

	        for (int i = 1; i <= 2; i++) {
	            preparedStatement.setString(i, "%" + searchTerm + "%");
	        }

	        ResultSet resultSet = preparedStatement.executeQuery();

	        scrollBar.getTableModel().setRowCount(0);

	        while (resultSet.next()) {
	            
	        	int kundenNr = resultSet.getInt("kd_nr");
	            String firstname = resultSet.getString("vorname");
	            String lastname = resultSet.getString("nachname");
	            String phonenumber = resultSet.getString("telefonnummer");


	            scrollBar.getTableModel().addRow(new Object[]{kundenNr, firstname, lastname, phonenumber});
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