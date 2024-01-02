package GuiElements;

import Design.CustomButtonRect;
import Listener.ActionListenerMain;
import Listener.CustomerListener;
import Listener.EmployeListener;
import Listener.OrdersListener;
import Listener.TimeListener;
import Listener.WarehouseListener;

public class CButton {
	private ActionListenerMain actionListener;
	private OrdersListener ordersListener;
	private CustomerListener customerListener;
	private WarehouseListener warehouseListener;
	private EmployeListener employeListener;
	private TimeListener timeListener;
	private CustomButtonRect[] buttons;
	
	private int number;

	private String color1 = "#0a1c2b";
	private String color2 = "#c2c7dd";
	
	public CButton(ActionListenerMain actionListener,  int number) {
		this.actionListener = actionListener;
		this.number = number;
		buttons = new CustomButtonRect[number];
	}
	
	public CButton(OrdersListener ordersListener,  int number) {
		this.ordersListener = ordersListener;
		this.number = number;
		buttons = new CustomButtonRect[number];
	}
	
	public CButton(CustomerListener customerListener,  int number) {
		this.customerListener = customerListener;
		this.number = number;
		buttons = new CustomButtonRect[number];
	}
	
	public CButton(WarehouseListener warehouseListener,  int number) {
		this.warehouseListener = warehouseListener;
		this.number = number;
		buttons = new CustomButtonRect[number];
	}
	
	public CButton(EmployeListener employeListener,  int number) {
		this.employeListener = employeListener;
		this.number = number;
		buttons = new CustomButtonRect[number];
	}
	
	public CButton(TimeListener timeListener,  int number) {
		this.timeListener = timeListener;
		this.number = number;
		buttons = new CustomButtonRect[number];
	}

	
	public void createButtons(int posX, int posY, int weight, int height, int distance, String position, String[] buttonID, String[] buttonName){
		for(int create = 0; create < number; create++) {
			buttons[create] = new CustomButtonRect(color1, color2, buttonName[create]);
			buttons[create].setBounds(posX, posY, weight, height);
			buttons[create].setActionCommand(buttonID[create]);
			buttons[create].addActionListener(actionListener);
			buttons[create].setFocusPainted(false);
			
			if(position.equals("posX")) {
				posX += distance;
			}
			else if(position.equals("posY")) {
				posY += distance;
			}
		}
	}
	
	public void createButtonsOrders(int posX, int posY, int weight, int height, int distance, String position, String[] buttonID, String[] buttonName){
		for(int create = 0; create < number; create++) {
			buttons[create] = new CustomButtonRect(color1, color2, buttonName[create]);
			buttons[create].setBounds(posX, posY, weight, height);
			buttons[create].setActionCommand(buttonID[create]);
			buttons[create].addActionListener(ordersListener);
			buttons[create].setFocusPainted(false);
			
			if(position.equals("posX")) {
				posX += distance;
			}
			else if(position.equals("posY")) {
				posY += distance;
			}
		}
	}
	
	public void createButtonsCustomer(int posX, int posY, int weight, int height, int distance, String position, String[] buttonID, String[] buttonName){
		for(int create = 0; create < number; create++) {
			buttons[create] = new CustomButtonRect(color1, color2, buttonName[create]);
			buttons[create].setBounds(posX, posY, weight, height);
			buttons[create].setActionCommand(buttonID[create]);
			buttons[create].addActionListener(customerListener);
			buttons[create].setFocusPainted(false);
			
			if(position.equals("posX")) {
				posX += distance;
			}
			else if(position.equals("posY")) {
				posY += distance;
			}
		}
	}
	
	public void createButtonsWarehouse(int posX, int posY, int weight, int height, int distance, String position, String[] buttonID, String[] buttonName){
		for(int create = 0; create < number; create++) {
			buttons[create] = new CustomButtonRect(color1, color2, buttonName[create]);
			buttons[create].setBounds(posX, posY, weight, height);
			buttons[create].setActionCommand(buttonID[create]);
			buttons[create].addActionListener(warehouseListener);
			buttons[create].setFocusPainted(false);
			
			if(position.equals("posX")) {
				posX += distance;
			}
			else if(position.equals("posY")) {
				posY += distance;
			}
		}
	}
	
	public void createButtonsEmploye(int posX, int posY, int weight, int height, int distance, String position, String[] buttonID, String[] buttonName){
		for(int create = 0; create < number; create++) {
			buttons[create] = new CustomButtonRect(color1, color2, buttonName[create]);
			buttons[create].setBounds(posX, posY, weight, height);
			buttons[create].setActionCommand(buttonID[create]);
			buttons[create].addActionListener(employeListener);
			buttons[create].setFocusPainted(false);
			
			if(position.equals("posX")) {
				posX += distance;
			}
			else if(position.equals("posY")) {
				posY += distance;
			}
		}
	}
	
	public void createButtonsTimeRecording(int posX, int posY, int weight, int height, int distance, String position, String[] buttonID, String[] buttonName){
		for(int create = 0; create < number; create++) {
			buttons[create] = new CustomButtonRect(color1, color2, buttonName[create]);
			buttons[create].setBounds(posX, posY, weight, height);
			buttons[create].setActionCommand(buttonID[create]);
			buttons[create].addActionListener(timeListener);
			buttons[create].setFocusPainted(false);
			
			if(position.equals("posX")) {
				posX += distance;
			}
			else if(position.equals("posY")) {
				posY += distance;
			}
		}
	}
	
	public CustomButtonRect[] getButtons() {
		return buttons;
	}
}