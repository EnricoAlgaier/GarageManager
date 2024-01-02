package GuiElements;

import java.awt.Color;

import javax.swing.JLabel;

public class CLabel {
	private int number;
	private JLabel[] labels;
	
	public CLabel(int number) {
		this.number = number;
		labels = new JLabel[number];
	}
	
	public void createLabels(int posX, int posY, int weight, int height, int distance, String position, String[] labelName) {
		for(int create=0; create<number; create++) {
			labels[create] = new JLabel(labelName[create]);
			labels[create].setBounds(posX, posY, weight, height);
			labels[create].setForeground(Color.WHITE);
			
			if(position == "posX") {
				posX += distance;
				
			}else if(position == "posY") {
				posY += distance;
				
			}
		}
	}
	
	public JLabel[] getLabels() {
		return labels;
	}
}