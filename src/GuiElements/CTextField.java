package GuiElements;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;

public class CTextField{
	private JTextField[] fields;
	private int number;
	private List<String> fieldValue;

	public CTextField(int number) {
		this.number = number;
		fields = new JTextField[number];
	}

	public void createTextFields(int posX, int posY, int weight, int height, int distacen, String position) {
		fieldValue = new ArrayList<>();
		for (int create = 0; create < number; create++) {
			fields[create] = new JTextField();
			fields[create].setBounds(posX, posY, weight, height);

			if (position.equals("posX")) {
				posX += distacen;

			} else if (position.equals("posY")) {
				posY += distacen;
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

	public List<String> getText() {
		return fieldValue;
	}
}