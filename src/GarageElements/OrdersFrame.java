package GarageElements;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JTextArea;
import Database.DatabaseConnection;
import Database.DetailQuery;
import GuiElements.CButton;
import GuiElements.ScrollBar;
import GuiElements.Window;
import Listener.OrdersListener;

public class OrdersFrame extends Window {
	protected static int weight = 1000;
	protected static int height = 600;

	private OrdersListener ordersListener;
	private DatabaseOutputOrders databaseOutput;
	private DatabaseConnection connectionData;
	private DetailQuery detailQuery;
	private RegisterOrder registerOrder;
	private FinishedOrder finishedOrder;
	private CButton menuButton, backButton;
	private ScrollBar scrollBar;
	private JTextArea detailWindow;

	protected String[] columnNames = { "Auftragsnummer", "Mitarbeiter", "Fahrzeug", "Kundennummer" };
	protected String[] menuButtonName = { "Auftrag anmelden", "Auftrag erstellen", "Auftrag beenden" };
	protected String[] backButtonName = { "zurück" };
	protected String[] backButtonID = { "backOrders" };

	private String descriptionText;
	private int orderNumber;

	public OrdersFrame(String windowName) {
		super(weight, height, windowName);

		connectionData = new DatabaseConnection();
		registerOrder = new RegisterOrder(connectionData);
		finishedOrder = new FinishedOrder(connectionData);
		ordersListener = new OrdersListener(this, registerOrder, finishedOrder);
		detailQuery = new DetailQuery(connectionData);
		menuButton = new CButton(ordersListener, 3);
		backButton = new CButton(ordersListener, 1);
		scrollBar = new ScrollBar(this, connectionData);
		databaseOutput = new DatabaseOutputOrders(scrollBar, connectionData);

		menuButton.createButtonsOrders(20, 20, 150, 30, 160, "posX", menuButtonName, menuButtonName);
		for (JButton button : menuButton.getButtons()) {
			add(button);
		}

		backButton.createButtonsOrders(20, 500, 100, 30, 0, "", backButtonID, backButtonName);
		for (JButton button : backButton.getButtons()) {
			add(button);
		}

		detailWindow = new JTextArea();
		detailWindow.setBounds(670, 70, 300, 350);
		detailWindow.setEnabled(false);
		detailWindow.setBackground(Color.LIGHT_GRAY);
		detailWindow.setDisabledTextColor(Color.BLACK);
		add(detailWindow);

		createOrderList();

		scrollBar.getDataTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = scrollBar.getDataTable().getSelectedRow();
				if (selectedRow != -1) {
					orderNumber = (int) scrollBar.getDataTable().getValueAt(selectedRow, 0);
					System.out.println("Auftragsnummer der ausgewählten Zeile: " + orderNumber);

					detailQuery.getOrdersText(orderNumber);
					descriptionText = detailQuery.getValue();
					setTextToDetailWindow();
				}
			}
		});
		
		close();
		setVisible(true);
	}

	public void createOrderList() {
		connectionData.connectDatabase();
		scrollBar.createTableOrdersList(3, columnNames);
		databaseOutput.queryData();
	}

	public void setTextToDetailWindow() {
		detailWindow.setText(descriptionText);
	}

	public int getOrdersID() {
		return orderNumber;
	}
}