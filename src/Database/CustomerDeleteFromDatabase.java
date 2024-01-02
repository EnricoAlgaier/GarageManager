package Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import CustomerElements.MainCustomerFrame;

public class CustomerDeleteFromDatabase {
	private DatabaseConnection connection;
	private MainCustomerFrame frame;

	private int confirm;

	public CustomerDeleteFromDatabase(int customerID, DatabaseConnection connection) {
		this.connection = connection;
		deleteCustomer(customerID);
	}

	public CustomerDeleteFromDatabase(MainCustomerFrame frame) {
		this.frame = frame;
	}

	public void deleteCustomer(int customerID) {
		confirm = JOptionPane.showConfirmDialog(null, "Möchten Sie den Kunden wirklich löschen?", "Bestätigung",
				JOptionPane.YES_NO_OPTION);

		if (confirm == JOptionPane.YES_OPTION) {
			try {
				connection.connectDatabase();

				String deleteVehicleQuery = "DELETE FROM kundenfahrzeug WHERE kd_nr = ?";
				try (PreparedStatement deleteVehicleStatement = connection.connection
						.prepareStatement(deleteVehicleQuery)) {
					deleteVehicleStatement.setInt(1, customerID);
					deleteVehicleStatement.executeUpdate();
				}

				String deleteAddressQuery = "DELETE FROM anschrift WHERE kd_nr = ?";
				try (PreparedStatement deleteAddressStatement = connection.connection
						.prepareStatement(deleteAddressQuery)) {
					deleteAddressStatement.setInt(1, customerID);
					deleteAddressStatement.executeUpdate();
				}

				String deleteCustomerQuery = "DELETE FROM kunden WHERE kd_nr = ?";
				try (PreparedStatement deleteCustomerStatement = connection.connection
						.prepareStatement(deleteCustomerQuery)) {
					deleteCustomerStatement.setInt(1, customerID);
					deleteCustomerStatement.executeUpdate();
				}

				connection.closeConnection();


				System.out.println("Kunde mit der ID " + customerID + " wurde erfolgreich gelöscht.");

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else {
			JOptionPane.showMessageDialog(null, "Löschen abgebrochen.");
		}
	}
}