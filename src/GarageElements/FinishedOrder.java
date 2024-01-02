package GarageElements;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import Database.DatabaseConnection;

public class FinishedOrder {
	private DatabaseConnection connectionData;
	private int orderID;
	private LocalDateTime localDateTime;
	private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
	private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:m");
	
	protected String finishedValue = "Fahrzeug abholbereit";
	
	public FinishedOrder(DatabaseConnection connectionData) {
		this.connectionData = connectionData;
		this.localDateTime = LocalDateTime.now();
	}
	
	public void setFinishedOrder() {
		connectionData.connectDatabase();
		
		 try {
	            String selectQuery = "SELECT mitarbeiterID, kd_nr FROM auftrag WHERE Auftrags_NR = ?";
	            PreparedStatement selectStatement = connectionData.connection.prepareStatement(selectQuery);
	            selectStatement.setInt(1, orderID);

	            ResultSet resultSet = selectStatement.executeQuery();

	            if (resultSet.next()) {
	            	int mitarbeiterID = resultSet.getInt("mitarbeiterID");
	                int kd_nr = resultSet.getInt("kd_nr");

	                String updateQuery = "UPDATE auftrag SET end_datum = ?, end_uhrzeit = ? WHERE Auftrags_NR = " + orderID;
	                PreparedStatement updateStatement = connectionData.connection.prepareStatement(updateQuery);
	                java.sql.Date sqlDate = java.sql.Date.valueOf(localDateTime.toLocalDate());

	                updateStatement.setDate(1, sqlDate);
	                updateStatement.setTime(2, java.sql.Time.valueOf(localDateTime.toLocalTime()));
	                updateStatement.executeUpdate();

	                System.out.println("Daten in der Tabelle 'auftrag' erfolgreich aktualisiert.");

	                String insertQuery = "UPDATE repstatus SET status = '" + finishedValue + "' WHERE Auftrags_NR = " + orderID;
	                PreparedStatement insertStatement = connectionData.connection.prepareStatement(insertQuery);
	                insertStatement.executeUpdate();

	                System.out.println("Daten in der Tabelle 'repstatus' erfolgreich eingefügt.");
	                
	            } else {
	                JOptionPane.showMessageDialog(null, "Auftrag nicht gefunden", "Fehler", JOptionPane.ERROR_MESSAGE);
	            }

	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

}