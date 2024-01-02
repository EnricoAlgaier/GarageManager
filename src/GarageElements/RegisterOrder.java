package GarageElements;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import Database.DatabaseConnection;

public class RegisterOrder {
	private DatabaseConnection connectionData;
	private int orderID;
	private LocalDateTime localDateTime;
	private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
	private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:m");
	
	protected String registerValue = "Fahrzeug in reparatur";
	
	public RegisterOrder(DatabaseConnection connectionData) {
		this.connectionData = connectionData;
		this.localDateTime = LocalDateTime.now();
	}
	
	public void setRegisterOrder() {
		connectionData.connectDatabase();
		
		 try {
	            String selectQuery = "SELECT mitarbeiterID, kd_nr FROM auftrag WHERE Auftrags_NR = ?";
	            PreparedStatement selectStatement = connectionData.connection.prepareStatement(selectQuery);
	            selectStatement.setInt(1, orderID);

	            ResultSet resultSet = selectStatement.executeQuery();

	            if (resultSet.next()) {
	            	int mitarbeiterID = resultSet.getInt("mitarbeiterID");
	                int kd_nr = resultSet.getInt("kd_nr");

	                String updateQuery = "UPDATE auftrag SET start_datum = ?, start_uhrzeit = ? WHERE Auftrags_NR = " + orderID;
	                PreparedStatement updateStatement = connectionData.connection.prepareStatement(updateQuery);
	                java.sql.Date sqlDate = java.sql.Date.valueOf(localDateTime.toLocalDate());

	                updateStatement.setDate(1, sqlDate);
	                updateStatement.setTime(2, java.sql.Time.valueOf(localDateTime.toLocalTime()));
	                updateStatement.executeUpdate();

	                System.out.println("Daten in der Tabelle 'auftrag' erfolgreich aktualisiert.");

	                String insertQuery = "INSERT INTO repstatus (mitarbeiterID, kd_nr, status, auftrags_nr) VALUES (?, ?, ?, ?)";
	                PreparedStatement insertStatement = connectionData.connection.prepareStatement(insertQuery);
	                insertStatement.setInt(1, mitarbeiterID);
	                insertStatement.setInt(2, kd_nr);
	                insertStatement.setString(3, registerValue);
	                insertStatement.setInt(4, orderID);
	                insertStatement.executeUpdate();

	                System.out.println("Daten in der Tabelle 'repstatus' erfolgreich eingefügt.");
	                
	            } else {
	                JOptionPane.showMessageDialog(null, "Auftrag nicht gefunden", "Fehler", JOptionPane.ERROR_MESSAGE);
	            }

	            connectionData.closeConnection();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
}