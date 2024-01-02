package Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import GarageElements.OrdersCreateFrame;
import GuiElements.CTextField;

public class InputToDatabaseOrdersCreate {
	private DatabaseConnection connectionData;
	private OrdersCreateFrame ordersCreateFrame;
	private CTextField ordersText;
	private JTextArea descriptionText;
	private String descriptionTextValue;
	private List<String> ordersValues;
	private boolean close;

	public InputToDatabaseOrdersCreate(DatabaseConnection connectionData, CTextField ordersText, JTextArea descriptionText) {
		this.connectionData = connectionData;
		this.ordersText = ordersText;
		this.descriptionText = descriptionText;
	}


	public void insertData() {
        try {
            connectionData.connectDatabase();

            String insertQuery1 = "INSERT INTO auftrag (mitarbeiterID, kd_nr, beschreibung) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement1 = connectionData.connection.prepareStatement(insertQuery1);

            try {
                if (isDuplicatePart(ordersValues.get(0))) {
                    JOptionPane.showMessageDialog(null, "Auftrag existiert bereits", "Abbruch",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                preparedStatement1.setInt(1, Integer.parseInt(ordersValues.get(0)));
                preparedStatement1.setString(2, ordersValues.get(1));
                preparedStatement1.setString(3, descriptionTextValue);
                preparedStatement1.executeUpdate();

                System.out.println("Datensatz erfolgreich eingefügt.");
                close = true;
                connectionData.closeConnection();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Nicht alle Felder ausgefüllt", "Abbruch",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean isDuplicatePart(String partID) throws SQLException {
        String query = "SELECT * FROM auftrag WHERE auftrags_NR = ?";

        try (PreparedStatement preparedStatement = connectionData.connection.prepareStatement(query)) {
            preparedStatement.setInt(1, Integer.parseInt(partID));

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }
    }

	public void setOrdersTextInput(int nameSize) {
		ordersText.textInput(nameSize);
		ordersValues = new ArrayList<>();
		ordersValues = ordersText.getText();
	}

	public void setDescriptionTextInput() {
		descriptionTextValue = descriptionText.getText();
	}
	
	public boolean getClose() {
		return close;
	}
}