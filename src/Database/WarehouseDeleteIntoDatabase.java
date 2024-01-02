package Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import CustomerElements.MainCustomerFrame;
import WarehouseElements.WarehouseFrame;

public class WarehouseDeleteIntoDatabase {
	private DatabaseConnection connection;
	private WarehouseFrame frame;

	private int confirm;

	public WarehouseDeleteIntoDatabase(int servicePartNumber, DatabaseConnection connection) {
		this.connection = connection;
		deleteCustomer(servicePartNumber);
	}

	public WarehouseDeleteIntoDatabase(WarehouseFrame frame) {
		this.frame = frame;
	}

	public void deleteCustomer(int customerID) {
		confirm = JOptionPane.showConfirmDialog(null, "Möchten Sie das Ersatzteil wirklich löschen?", "Bestätigung",
				JOptionPane.YES_NO_OPTION);

		if (confirm == JOptionPane.YES_OPTION) {
			try {
				connection.connectDatabase();

				String deleteVehicleQuery = "DELETE FROM lager WHERE teileID = ?";
				try (PreparedStatement deleteVehicleStatement = connection.connection
						.prepareStatement(deleteVehicleQuery)) {
					deleteVehicleStatement.setInt(1, customerID);
					deleteVehicleStatement.executeUpdate();
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