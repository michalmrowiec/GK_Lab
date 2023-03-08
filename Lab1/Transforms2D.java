package GK_Lab1_a;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Transforms2D extends JPanel {

	private class Display extends JPanel {
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			g2.translate(300,300);  // Moves (0,0) to the center of the display.
			int whichTransform = transformSelect.getSelectedIndex();

			// TODO Apply transforms here, depending on the value of whichTransform!
			
			switch(whichTransform)
			{
				case 1:
					g2.scale(0.5, 0.5);
					break;
				case 2:
					g2.rotate(Math.toRadians(45), 1, 1);
					break;
				case 3:
					g2.scale(-0.4, 0.8);
					g2.rotate(Math.toRadians(180), 1, 1);
					break;
				case 4:
					g2.shear(0.3, 0);
					break;
				case 5:
					g2.scale(1, 0.5);
					g2.translate(0, -450);
					break;
				case 6:
					g2.rotate(Math.toRadians(90), 1, 1);
					g2.shear(0.4, 0);
					break;
				case 7:
					g2.scale(0.4, 0.8);
					g2.rotate(Math.toRadians(180), 1, 1);
					break;
				case 8:
					g2.rotate(Math.toRadians(30), 1, 1);
					g2.scale(1, 0.5);
					g2.translate(0, 250);
					break;
				case 9:
					g2.shear(0.4, 0);
					g2.rotate(Math.toRadians(201.8), 1, 1);
					g2.translate(-76, 0);
					break;
				default:
					break;
			}

			//g2.drawImage(pic, -200, -150, null); // Draw image with center at (0,0).
			
			int n = 6;
			int R = 150;
			int[] xPoints = new int[n];
			int[] yPoints = new int[n];
			for (int i= 0; i < n; i++)
			{
				double angle = 2 * Math.PI / n * i;
				xPoints[i] = (int)(R*Math.cos(angle));
				yPoints[i] = (int)(R*Math.sin(angle));
			}
			//g2.drawPolygon(xPoints, yPoints, n);
			g2.setColor(Color.cyan);
			g2.fillPolygon(xPoints, yPoints, n);
		}
	}

	private Display display;
	private BufferedImage pic;
	private JComboBox<String> transformSelect;

	public Transforms2D() throws IOException {
		pic = ImageIO.read(getClass().getClassLoader().getResource("shuttle.jpg"));
		display = new Display();
		display.setBackground(Color.YELLOW);
		display.setPreferredSize(new Dimension(600,600));
		transformSelect = new JComboBox<String>();
		transformSelect.addItem("None");
		for (int i = 1; i < 10; i++) {
			transformSelect.addItem("No. " + i);
		}
		transformSelect.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display.repaint();
			}
		});
		setLayout(new BorderLayout(3,3));
		setBackground(Color.GRAY);
		setBorder(BorderFactory.createLineBorder(Color.GRAY,10));
		JPanel top = new JPanel();
		top.setLayout(new FlowLayout(FlowLayout.CENTER));
		top.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
		top.add(new JLabel("Transform: "));
		top.add(transformSelect);
		add(display,BorderLayout.CENTER);
		add(top,BorderLayout.NORTH);
	}

	public static void main(String[] args) throws IOException {
		JFrame window = new JFrame("2D Transforms");
		window.setContentPane(new Transforms2D());
		window.pack();
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		window.setLocation
			( (screen.width - window.getWidth())/2, (screen.height - window.getHeight())/2 );
		window.setVisible(true);
	}
}