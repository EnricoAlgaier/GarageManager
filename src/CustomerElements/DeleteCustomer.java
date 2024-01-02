package CustomerElements;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import Database.DatabaseConnection;
import Database.CustomerDeleteFromDatabase;
import GuiElements.CTextField;

public class DeleteCustomer {
	private CTextField idCheckField;
	private DatabaseConnection connection;
	private CustomerDeleteFromDatabase deleteCustomer;
	private int customerID;
	
	private List<String> id;
	private boolean idValueTrue;
	private boolean close;
	
	public DeleteCustomer(DatabaseConnection connection, CTextField idCheckField) {
		this.idCheckField = idCheckField;
		this.connection = connection;
	}
	
	public boolean checkID() {
		for (String value : id) {
			if (!isNumeric(value)) {
				JOptionPane.showMessageDialog(null, "Die ID darf nur aus Zahlen bestehen.", "Achtung",
						JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
		}
		customerID = Integer.parseInt(id.get(0));
		new CustomerDeleteFromDatabase(customerID, connection);
		close = true;
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

	public void setId(int number) {
		idCheckField.textInput(number);
		id = new ArrayList<>();
		id = idCheckField.getText();
	}
	
	public boolean getClose() {
		return close;
	}
}