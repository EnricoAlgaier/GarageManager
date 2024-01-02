package Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;

import Database.DatabaseConnection;
import Database.InputToDatabaseOrdersCreate;
import GarageElements.DetailTextWindow;
import GarageElements.FinishedOrder;
import GarageElements.GarageFrame;
import GarageElements.LiftStatus;
import GarageElements.OrdersFrame;
import GarageElements.OrdersCreateFrame;
import GarageElements.OrdersToDatabaseInput;
import GarageElements.RegisterOrder;
import GuiElements.CTextField;
import MainComponents.MainInterface;

public class OrdersListener implements ActionListener {
	private GarageFrame garageFrame;
	private DatabaseConnection connectionData;
	private DetailTextWindow detailtext;
	private OrdersCreateFrame ordersCreateWindow;
	private OrdersToDatabaseInput ordersInput;
	private CTextField fieldValue;
	private OrdersFrame ordersFrame;
	private InputToDatabaseOrdersCreate inputToDatatbase;
	private RegisterOrder registerOrder;
	private FinishedOrder finishedOrder;
	private LiftStatus liftsStatusFrame;

	public OrdersListener(GarageFrame garageFrame) {
		this.garageFrame = garageFrame;
	}

	public OrdersListener(OrdersFrame ordersFrame, RegisterOrder registerOrder, FinishedOrder finishedOrder) {
		this.ordersFrame = ordersFrame;
		this.registerOrder = registerOrder;
		this.finishedOrder = finishedOrder;
	}

	public OrdersListener(OrdersCreateFrame ordersCreateWindow, InputToDatabaseOrdersCreate inputToDatatbase) {
		this.ordersCreateWindow = ordersCreateWindow;
		this.inputToDatatbase = inputToDatatbase;
	}

	public OrdersListener(RegisterOrder registerOrder) {
		this.registerOrder = registerOrder;
	}
	
	public OrdersListener(LiftStatus liftsStatusFrame) {
		this.liftsStatusFrame = liftsStatusFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonID = ((JButton) e.getSource()).getActionCommand();

		if ("Aufträge".equals(buttonID)) {
			new OrdersFrame(buttonID);
			garageFrame.dispose();

		} else if("Hebebühnen belegung".equals(buttonID)) {
			System.out.println(buttonID);
			new LiftStatus(buttonID);
		}
		
		
		if ("Auftrag anmelden".equals(buttonID)) {
			new RegisterOrder(connectionData);
			int orderID = ordersFrame.getOrdersID();

			registerOrder.setOrderID(ordersFrame.getOrdersID());
			registerOrder.setRegisterOrder();

		} else if ("Details".equals(buttonID)) {
			

		} else if ("Auftrag erstellen".equals(buttonID)) {
			ordersCreateWindow = new OrdersCreateFrame(buttonID);

		} else if ("Auftrag beenden".equals(buttonID)) {
			new FinishedOrder(connectionData);
			int orderID = ordersFrame.getOrdersID();
			
			finishedOrder.setOrderID(orderID);
			finishedOrder.setFinishedOrder();

		} else if ("backOrders".equals(buttonID)) {
			new GarageFrame("Werkstatt");
			ordersFrame.dispose();
		}

		// Orders create window
		if ("backOrdersCreate".equals(buttonID)) {
			ordersCreateWindow.dispose();

		} else if ("add".equals(buttonID)) {
			inputToDatatbase.setOrdersTextInput(ordersCreateWindow.getOrdersFieldSize());
			inputToDatatbase.setDescriptionTextInput();
			inputToDatatbase.insertData();
			
			if(inputToDatatbase.getClose() == true) {
				ordersCreateWindow.dispose();
			}
		}
		
		//LiftStatusFrame
		if("schließen".equals(buttonID)) {
			liftsStatusFrame.dispose();
		}
	}
}