package gui;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

import javax.swing.*;

public class Tutorial extends JPanel{
	
	private GameWindow window;
	private JButton goToMenu;
	
	public Tutorial(GameWindow gw) {
		super();
		setBackground(Color.WHITE);
		goToMenu = new JButton("Back To Menu");
		add(goToMenu);
		window = gw;
		goToMenu.addActionListener(new BackToMenu());
		
	}
	
public void paintComponent(Graphics g) {
		
		super.paintComponent(g);

	    int width = getWidth();
	    int height = getHeight();
	    
	    double ratioX = (double)width/DrawingPanel.DRAWING_WIDTH;
	    double ratioY = (double)height/DrawingPanel.DRAWING_HEIGHT;
	    
		
		
		Graphics2D g2 = (Graphics2D)g;
		AffineTransform at = g2.getTransform();
		g2.scale(ratioX,ratioY);
		
		
		Font font = new Font("", Font.BOLD, 40);
		g2.setFont(font);
		g2.setColor(Color.BLUE);
		g2.drawString("", DrawingPanel.DRAWING_WIDTH/4, 550);
		g2.setTransform(at);
	}
	
	public class BackToMenu implements ActionListener{
		
		
		public void actionPerformed(ActionEvent e) {
			
			Object source = e.getSource();
			if(source == goToMenu){
				window.changePanel(GameWindow.MENU_ID);
			}
		}
	}
}
