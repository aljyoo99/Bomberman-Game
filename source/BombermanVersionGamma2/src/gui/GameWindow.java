package gui;
import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.*;



	/**
	 * This class integrates the graphical components and the logical components of the game into a
	 * JFrame
	 */
public class GameWindow extends JFrame{

	
	private JPanel cardPanel;
	private JPanel startPanel;
	private JPanel tutorialPanel;
	
	private JPanel gamePanel;
	private JPanel victoryPanel;
	private DrawingPanel gameActionPanel;
	private ControlPanel controlPanel;
	
	public static final String MENU_ID = "1";
	public static final String GAME_ID = "2";
	public static final String TUTORIAL_ID = "3";
	public static final String VICTORY_ID = "4";
	
	/**
	 * @post DrawingPanel which contains graphical components and ControlPanel which manages
	 * key movements are added to the JFrame window in a border layout
	 */
	public GameWindow(){
		super("Bomberman");
		
		setBounds(0, 0, 650, 650);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cardPanel = new JPanel();
	    CardLayout cl = new CardLayout();
	    cardPanel.setLayout(cl);
		
		
		setUpGame();
		setResizable(true);
	}
	
	public void changePanel(String id) {
		CardLayout cl = (CardLayout)cardPanel.getLayout();
		cl.show(cardPanel,id);
		requestFocus();
		
		if(id.equals(GAME_ID)) {
			Thread gameThread = new Thread(gameActionPanel);
			
			gameThread.start();
			
		} else if(id.equals(VICTORY_ID)) {
			setUpGame();
			
		}
		
	}
	
	private void setUpGame() {
		
		
		startPanel = new StartMenu(this);
	    tutorialPanel = new Tutorial(this);
	    
		gamePanel = new JPanel();
		BorderLayout layout = new BorderLayout();
		gamePanel.setLayout(layout);
		
		gameActionPanel = new DrawingPanel(this);
		controlPanel = new ControlPanel();
		controlPanel.addControlListener(gameActionPanel);
		addKeyListener(controlPanel.new KeyMovementHandler());
		
		gamePanel.add((JPanel)gameActionPanel, BorderLayout.CENTER);
		gamePanel.add(controlPanel, BorderLayout.EAST);
		add(gamePanel);
		
		
		victoryPanel = new VictoryPanel(this, gameActionPanel);
		
		cardPanel.add(startPanel, MENU_ID);
	    cardPanel.add(gamePanel, GAME_ID);
		cardPanel.add(tutorialPanel, TUTORIAL_ID);
		cardPanel.add(victoryPanel, VICTORY_ID);
	    add(cardPanel);
	}
}
