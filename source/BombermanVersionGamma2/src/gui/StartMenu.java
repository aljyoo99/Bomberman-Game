package gui;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.*;
import java.awt.geom.AffineTransform;

import javax.swing.*;

public class StartMenu extends JPanel{
	
	private GameWindow window;
	private JPanel optionButtons, bottomPanel;
	private JButton playButton, tutorialButton;
	
	public StartMenu (GameWindow gw){
		super();
		
		window = gw;
		playButton = new JButton("Play Multi Player");
		tutorialButton = new JButton("Tutorial");
		
		setLayout(new BorderLayout());
		
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1,3));
		
		optionButtons = new JPanel();
		optionButtons.setLayout(new GridLayout(2,1));
		optionButtons.add(playButton);
		optionButtons.add(tutorialButton);
		playButton.addActionListener(new OptionHandler());
		tutorialButton.addActionListener(new OptionHandler());
		
		JPanel bottomLeft = new JPanel();
		
		JPanel bottomRight = new JPanel();
		
		bottomPanel.add(bottomLeft);
		bottomPanel.add(optionButtons);
		bottomPanel.add(bottomRight);
		add(bottomPanel, BorderLayout.SOUTH);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

	    int width = getWidth();
	    int height = getHeight();
	    
	    double ratioX = (double)width/DrawingPanel.DRAWING_WIDTH;
	    double ratioY = (double)height/DrawingPanel.DRAWING_HEIGHT;
	    
		ImageIcon titleScreenIcon = new ImageIcon("bombermanTitleScreen.jpg");
		Image titleScreenImage = titleScreenIcon.getImage();
		
		ImageIcon titleIcon = new ImageIcon("BombermanTitle.png");
		Image titleImage = titleIcon.getImage();
		
		Graphics2D g2 = (Graphics2D)g;
		AffineTransform at = g2.getTransform();
		g2.scale(ratioX,ratioY);
		
		g2.drawImage(titleScreenImage, 0, 0, 650, 650, this);
		g2.drawImage(titleImage, 0, 0, 320, 100, this);
		g2.setTransform(at);
		
	}
	
	public class OptionHandler implements ActionListener{
		
		
		public void actionPerformed(ActionEvent e) {
			
			Object source = e.getSource();
			if(source == playButton){
				window.changePanel(GameWindow.GAME_ID);
			} else if(source == tutorialButton) {
				window.changePanel(GameWindow.TUTORIAL_ID);
			}
		}
	}
	
	
}
