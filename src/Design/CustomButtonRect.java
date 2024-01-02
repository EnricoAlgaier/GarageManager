package Design;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class CustomButtonRect extends JButton {
	private String colorNumber1;
	private String colorNumber2;
	private String buttonName;
	private Point pressedLocation;
	
	private final Timer timer;
	private final Timer timerPressed;
	private float alpha = 0.3f;
	private boolean mouseOver;
	private boolean pressed;
	private float pressedSize;
	private float sizeSpeed = 15f;
	private float alphaPressed = 0.5f;

	public CustomButtonRect(String colorNumber1, String colorNumber2, String buttonName) {
		this.colorNumber1 = colorNumber1;
		this.colorNumber2 = colorNumber2;
		this.buttonName = buttonName;

		setText(buttonName);
		setContentAreaFilled(false);
		setForeground(Color.white);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setBorder(new EmptyBorder(10, 20, 10, 20));

		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent me) {
				mouseOver = true;
				timer.start();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				pressedSize = 0;
				alphaPressed = 0.5f;
				pressed = true;
				pressedLocation = e.getPoint();
				timerPressed.setDelay(0);
				timerPressed.start();
			}

			@Override
			public void mouseExited(MouseEvent me) {
				mouseOver = false;
				timer.start();
			}
		});
		timer = new Timer(40, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (mouseOver) {
					if (alpha < 0.6f) {
						alpha += 0.05f;
						repaint();
					} else {
						alpha = 0.6f;
						timer.stop();
						repaint();
					}
				} else {
					if (alpha > 0.3f) {
						alpha -= 0.05f;
						repaint();
					} else {
						alpha = 0.3f;
						timer.start();
						repaint();
					}
				}
			}
		});
		timerPressed = new Timer(0, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pressedSize += sizeSpeed;
				if (alphaPressed <= 0) {
					pressed = false;
					timerPressed.stop();
				} else {
					repaint();
				}
			}
		});
	}

	@Override
	protected void paintComponent(Graphics buttGraph) {
		Color color1 = Color.decode(colorNumber1);
		Color color2 = Color.decode(colorNumber2);

		int width = getWidth();
		int height = getHeight();

		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = img.createGraphics();
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		// Create Gradients Color
		GradientPaint gra = new GradientPaint(0, 0, color1, width, 0, color2);
		g2.setPaint(gra);
		g2.fillRect(0, 0, width, height);

		// Add Style
		createStyle(g2);
		if (pressed) {
			paintPressed(g2);
		}
		g2.dispose();

		buttGraph.drawImage(img, 0, 0, null);
		super.paintComponent(buttGraph);
	}

	private void createStyle(Graphics2D g2) {
		int width = getWidth();
		int height = getHeight();
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
		GradientPaint gra = new GradientPaint(0, 0, Color.white, 0, height, new Color(255, 255, 255, 60));
		g2.setPaint(gra);
		Path2D.Float f = new Path2D.Float();
		f.moveTo(0, 0);
		int control = height + height / 2;
		
		g2.setColor(new Color(0, 0, 0, 50));
		g2.fillRect(5, 5, width, height);
		
		g2.setColor(new Color(100, 100, 100)); 
	    g2.drawRect(0, 0, width - 1, height - 1);
		
		g2.setColor(new Color(255, 255, 255, 60));
		g2.fillRect(-3, -3, width, height);

	}

	private void paintPressed(Graphics2D g2) {
		if (pressedLocation.x - (pressedSize / 2) < 0 && pressedLocation.x + (pressedSize / 2) > getWidth()) {
			timerPressed.setDelay(20);
			alphaPressed -= 0.05f;
			if (alphaPressed < 0) {
				alphaPressed = 0;
			}
		}

		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alphaPressed));
		g2.setColor(Color.WHITE);
		float x = pressedLocation.x - (pressedSize / 2);
		float y = pressedLocation.y - (pressedSize / 2);
		g2.fillRect((int) x, (int) y, (int) pressedSize, (int) pressedSize);
	}
}