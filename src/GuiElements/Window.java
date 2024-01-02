package GuiElements;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Window extends JFrame{
	protected Color color = new Color(10,28,43);
	protected ImageIcon icon = new ImageIcon("img\\icon3.png");
	
	public Window(int weight, int height, String windowName) {
		super(windowName);
		setSize(weight, height);
		getContentPane().setBackground(color);
		setIconImage(icon.getImage());
		setLocationRelativeTo(null);
		setLayout(null);
		
	}
	
	public void close() {
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(Window.this, "Möchten Sie das Programm wirklich beenden?", "Beenden", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    System.exit(0); 
                }
            }
        });
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
	}
}