package Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class EmployeDeleteFromDatabase {
	private DatabaseConnection connection;

	private int confirm;
	private boolean close;

	public EmployeDeleteFromDatabase(int employeID, DatabaseConnection connection) {
		this.connection = connection;
		deleteCustomer(employeID);
	}

	public void deleteCustomer(int employeID) {
		confirm = JOptionPane.showConfirmDialog(null, "Möchten Sie den Mitarbeiter wirklich löschen?", "Bestätigung",
				JOptionPane.YES_NO_OPTION);

		if (confirm == JOptionPane.YES_OPTION) {
			try {
				connection.connectDatabase();

	            String deleteCustomerQuery = "DELETE FROM mitarbeiter_anschrift WHERE mitarbeiterID = ?";
	            try (PreparedStatement deleteCustomerStatement = connection.connection.prepareStatement(deleteCustomerQuery)) {
	                deleteCustomerStatement.setInt(1, employeID);
	                deleteCustomerStatement.executeUpdate();
	            }

	            String deleteAddressQuery = "DELETE FROM mitarbeiter WHERE mitarbeiterID = ?";
	            try (PreparedStatement deleteAddressStatement = connection.connection.prepareStatement(deleteAddressQuery)) {
	                deleteAddressStatement.setInt(1, employeID);
	                deleteAddressStatement.executeUpdate();
	            }
	            
	            
				
				close = true;
				System.out.println("Kunde mit der ID " + employeID + " wurde erfolgreich gelöscht.");

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else {
			JOptionPane.showMessageDialog(null, "Löschen abgebrochen.");
		}
	}
	
	public boolean getClose() {
		return close;
	}
}