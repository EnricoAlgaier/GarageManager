package Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import GuiElements.CTextField;

public class ServicePartInputToDatabase {
    private DatabaseConnection connectionData;
    private CTextField serviceText;
    private List<String> servicePartText;

    public ServicePartInputToDatabase(DatabaseConnection connectionData, CTextField serviceText) {
        this.connectionData = connectionData;
        this.serviceText = serviceText;
    }

    public void insertData() {
        try {
            connectionData.connectDatabase();

            String insertQuery1 = "INSERT INTO lager (teileID, teil, bestand, preis) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement1 = connectionData.connection.prepareStatement(insertQuery1);

            try {
                if (isDuplicatePart(servicePartText.get(0))) {
                    JOptionPane.showMessageDialog(null, "Ersatzteil existiert bereits", "Abbruch",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                preparedStatement1.setInt(1, Integer.parseInt(servicePartText.get(0)));
                preparedStatement1.setString(2, servicePartText.get(1));
                preparedStatement1.setInt(3, Integer.parseInt(servicePartText.get(2)));
                preparedStatement1.setFloat(4, Float.parseFloat(servicePartText.get(3)));
                preparedStatement1.executeUpdate();
                
                System.out.println("Datensatz erfolgreich eingefügt.");

                connectionData.closeConnection();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Nicht alle Felder ausgefüllt", "Abbruch",
                        JOptionPane.WARNING_MESSAGE);
                System.err.println("Ein Fehler ist aufgetreten: " + e.getMessage());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean isDuplicatePart(String partID) throws SQLException {
        String query = "SELECT * FROM lager " + "WHERE teileID = ?";

        try (PreparedStatement preparedStatement = connectionData.connection.prepareStatement(query)) {
            preparedStatement.setInt(1, Integer.parseInt(partID));

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }
    }

    public void setNameTextInput(int nameSize) {
        serviceText.textInput(nameSize);
        servicePartText = new ArrayList<>();
        servicePartText = serviceText.getText();
    }
}