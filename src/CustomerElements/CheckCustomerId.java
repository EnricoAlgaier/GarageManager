package CustomerElements;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import Database.DatabaseConnection;
import GuiElements.CTextField;

public class CheckCustomerId {
	private CTextField idCheckField;
	private DatabaseConnection connection;
	private ChangeCustomer customerChangeFrame;

	private int customerID;
	private boolean idValueTrue;

	private List<String> id;
	private List<String> customerText;
	private List<String> addressText;
	private List<String> vehicleText;
	private List<String> customerId;

	public CheckCustomerId(CTextField idCheckField, DatabaseConnection connection, ChangeCustomer customerChangeFrame) {
		this.idCheckField = idCheckField;
		this.connection = connection;
		this.customerChangeFrame = customerChangeFrame;

		customerText = new ArrayList<>();
		addressText = new ArrayList<>();
		vehicleText = new ArrayList<>();
		customerId = new ArrayList<>();
	}

	public void getDatabaseValueToTextFields() {
		checkID();

		if (idValueTrue == true) {
			customerID = Integer.parseInt(id.get(0));

			try {
				connection.connectDatabase();

				String customer = "SELECT kunden.kd_nr, kunden.vorname, kunden.nachname, kunden.telefonnummer, anschrift.plz, anschrift.ort, "
						+ "anschrift.straße, anschrift.hausnummer, kundenfahrzeug.fahrzeug, kundenfahrzeug.tüv, kundenfahrzeug.km_stand, kundenfahrzeug.kennzeichen "
						+ "FROM kunden " + "LEFT JOIN anschrift ON kunden.kd_nr = anschrift.kd_nr "
						+ "LEFT JOIN kundenFahrzeug kundenfahrzeug ON kunden.kd_nr = kundenfahrzeug.kd_nr "
						+ "WHERE kunden.kd_nr = ?";

				PreparedStatement preparedStatement = connection.connection.prepareStatement(customer);
				preparedStatement.setInt(1, customerID);

				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						resultSet.getInt("kd_nr");
						String vorname = resultSet.getString("vorname");
						String nachname = resultSet.getString("nachname");
						String telefonnummer = resultSet.getString("telefonnummer");
						String plz = resultSet.getString("plz");
						String ort = resultSet.getString("ort");
						String straße = resultSet.getString("straße");
						String hausnummer = resultSet.getString("hausnummer");
						String fahrzeugmodell = resultSet.getString("fahrzeug");
						String tüv = resultSet.getString("tüv");
						int km_stand = resultSet.getInt("km_stand");
						String license = resultSet.getString("kennzeichen");

						customerText.clear();
						customerText.add(vorname);
						customerText.add(nachname);
						customerText.add(telefonnummer);

						addressText.clear();
						addressText.add(plz);
						addressText.add(ort);
						addressText.add(straße);
						addressText.add(hausnummer);

						vehicleText.clear();
						vehicleText.add(fahrzeugmodell);
						vehicleText.add(tüv);
						vehicleText.add(String.valueOf(km_stand));
						vehicleText.add(license);

						customerId.clear();
						customerId.add(id.get(0));

						customerChangeFrame = new ChangeCustomer(this, id.get(0));

					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean checkID() {
		for (String value : id) {
			if (!isNumeric(value)) {
				JOptionPane.showMessageDialog(null, "Die ID darf nur aus Zahlen bestehen.", "Achtung",
						JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
		}
		return true;
	}

	private boolean isNumeric(String check) {
		try {
			Integer.parseInt(check);
			idValueTrue = true;
			return true;

		} catch (NumberFormatException e) {
			return false;
		}
	}

	public boolean getIdTrue() {
		return idValueTrue;
	}

	public List<String> getcustomerText() {
		return customerText;
	}

	public List<String> getaddressText() {
		return addressText;
	}

	public List<String> getvehicleText() {
		return vehicleText;
	}

	public List<String> getCustomerIdText() {
		return customerId;
	}

	public void setId(int number) {
		idCheckField.textInput(number);
		id = new ArrayList<>();
		id = idCheckField.getText();
	}

	public ChangeCustomer getClose() {
		return customerChangeFrame;
	}
}