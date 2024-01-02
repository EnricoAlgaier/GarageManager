package GarageElements;

import javax.swing.JTextArea;
import Database.DatabaseConnection;
import javax.swing.JScrollPane;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DetailTextWindow {
    private JTextArea text;
    private DatabaseConnection connectionData;
    private OrdersFrame ordersFrame;

    public DetailTextWindow(DatabaseConnection connectionData, OrdersFrame ordersFrame) {
        this.connectionData = connectionData;
        this.ordersFrame = ordersFrame;
    }
    
    public void loadDataFromDatabase() {
        try {
            ResultSet resultSet = connectionData.statement.executeQuery("SELECT beschreibung " + "FROM auftrag");

            StringBuilder stringBuilder = new StringBuilder();

            while (resultSet.next()) {
                String description = resultSet.getString("beschreibung");

                stringBuilder.append("Beschreibung: ").append(description).append("\n");
            }

            text.setText(stringBuilder.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTextArea() {
    	text = new JTextArea();
        text.setBounds(100, 100, 100, 100);
        
        JScrollPane scrollPane = new JScrollPane(text);
        ordersFrame.add(scrollPane);

        loadDataFromDatabase();
    }
    

}