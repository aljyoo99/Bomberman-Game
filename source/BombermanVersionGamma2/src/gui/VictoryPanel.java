package gui;

import gui.Tutorial.BackToMenu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

import javax.swing.*;

import gameEntities.Player;

public class VictoryPanel extends JPanel{

	private JButton goToMenu;
	private GameWindow window;
	private Player winner;
	
	public VictoryPanel(GameWindow gw, DrawingPanel dp) {
		super();
		setBackground(Color.cyan);
		goToMenu = new JButton("Back To Menu");
		add(goToMenu);
		window = gw;
		winner = dp.returnWinner();
		
		goToMenu.addActionListener(new BackToMenu());
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);

	    int width = getWidth();
	    int height = getHeight();
	    
	    double ratioX = (double)width/DrawingPanel.DRAWING_WIDTH;
	    double ratioY = (double)height/DrawingPanel.DRAWING_HEIGHT;
	    
		ImageIcon victoryScreenIcon = new ImageIcon("victoryscreen.jpg");
		Image victoryScreenImage = victoryScreenIcon.getImage();
		
		Graphics2D g2 = (Graphics2D)g;
		AffineTransform at = g2.getTransform();
		g2.scale(ratioX,ratioY);
		
		g2.drawImage(victoryScreenImage, 0, 0, 650, 650, this);
		Font font = new Font("", Font.BOLD, 40);
		g2.setFont(font);
		g2.setColor(Color.BLUE);
		g2.drawString(winner+" wins the game!", DrawingPanel.DRAWING_WIDTH/4, 550);
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
