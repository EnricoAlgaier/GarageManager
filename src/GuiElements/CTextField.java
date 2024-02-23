package GuiElements;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CTextField{
	private JTextField[] fields;
	private JPasswordField[] password;
	private int number;
	private List<String> fieldValue;

	public CTextField(int number) {
		this.number = number;
		fields = new JTextField[number];
		password = new JPasswordField[number]; 
	}

	public void createTextFields(int posX, int posY, int weight, int height, int distance, String position) {
		fieldValue = new ArrayList<>();
		for (int create = 0; create < number; create++) {
			fields[create] = new JTextField();
			fields[create].setBounds(posX, posY, weight, height);

			if (position.equals("posX")) {
				posX += distance;

			} else if (position.equals("posY")) {
				posY += distance;
			}
		}
	}
	
	public void createPasswordField(int posX, int posY, int weight, int height, int distance, String position) {
		fieldValue = new ArrayList<>();
		for (int create = 0; create < number; create++) {
			password[create] = new JPasswordField();
			password[create].setBounds(posX, posY, weight, height);

			if (position.equals("posX")) {
				posX += distance;

			} else if (position.equals("posY")) {
				posY += distance;
			}
		}
	}

	public void textInput(int number) {
		fieldValue.clear();
		for (int index = 0; index < number; index++) {
			fieldValue.add(fields[index].getText());
		}
	}
	
	public JTextField[] getFields() {
		return fields;
	}
	
	public JPasswordField[] getPasswordField() {
		return password;
	}

	public List<String> getText() {
		return fieldValue;
	}
}