package gui;
import java.awt.*;

import java.awt.geom.*;
import java.util.ArrayList;

import javax.swing.*;

import gameEntities.*;
import gameboard.*;

/**
	 * This class graphically represents the entities and displays the graphical motions of 
	 * the players and the bombs as the game moves along. It also implements ControlListener
	 * and whenever a key instruction is sent from ControlPanel, the DrawingPanel makes changes
	 * in the game based on it.
	 * 
	 */
public class DrawingPanel extends JPanel implements ControlListener, Runnable{

	
	public static final int DRAWING_WIDTH = 650;
	public static final int DRAWING_HEIGHT = 650;
	private Player bomberman;
	private Player bomberman2;
	private GameBoard gameBoard;
	private boolean gameFinished;
	private GameWindow window;
	
	
	public DrawingPanel(GameWindow gw) {
		super();
		setBackground(Color.WHITE);
		gameBoard = new GameBoard();
		bomberman = gameBoard.getFirstPlayer();
		bomberman2 = gameBoard.getSecondPlayer();
		window = gw;
		gameFinished = false;
		
	}
	/**
	 * @param g Graphics
	 * @post all GameEntities are drawn on JPanel
	 */
	public void paintComponent(Graphics g)
	{
	    super.paintComponent(g);  // Call JPanel's paintComponent method to paint the background

	    int width = getWidth();
	    int height = getHeight();
	    
	    double ratioX = (double)width/DRAWING_WIDTH;
	    double ratioY = (double)height/DRAWING_HEIGHT;
	    
	    Graphics2D g2 = (Graphics2D)g;
	    AffineTransform at = g2.getTransform();
	    g2.scale(ratioX,ratioY);
	    
	    gameBoard.drawEntities(g2, this);
	    
	    ArrayList<Bomb> bombs = gameBoard.getBombs();
	    
	    if(bombs.size() > 0){
	    	for(int i = 0; i <bombs.size(); i++)
	    		bombs.get(i).draw(g2, this);
	    }
	    
	    ArrayList<Explosion> exps = gameBoard.getExplosions();
	    
	    if(exps.size() > 0){
	    	for(int i = 0; i <exps.size(); i++)
	    		exps.get(i).draw(g2, this);
	    }
	    
	    
	    g2.setTransform(at);
	    //repaint();
	}
	/**
	 * @param direction String
	 * @pre direction is specified by ControlListener and is either left, right, up, down
	 * @post Player moves a certain amount in the left, right, up, down directions
	 */
	public void moveCharacter(int player, String direction){
		if(player==1 && bomberman != null) {
			if(direction.equals(ControlListener.LEFT)) {
				bomberman.setVelocity(-1, 0);
			} else if(direction.equals(ControlListener.RIGHT)) {
				bomberman.setVelocity(1, 0);
			} else if(direction.equals(ControlListener.UP)) {
				bomberman.setVelocity(0, -1);
			} else if(direction.equals(ControlListener.DOWN)) {
				bomberman.setVelocity(0, 1);
			} else {
				bomberman.setVelocity(0, 0);
			}
		} else if (bomberman2 != null){
			if(direction.equals(ControlListener.LEFT)) {
				bomberman2.setVelocity(-1, 0);
			} else if(direction.equals(ControlListener.RIGHT)) {
				bomberman2.setVelocity(1, 0);
			} else if(direction.equals(ControlListener.UP)) {
				bomberman2.setVelocity(0, -1);
			} else if(direction.equals(ControlListener.DOWN)) {
				bomberman2.setVelocity(0, 1);
			} else {
				bomberman2.setVelocity(0, 0);
			}
		}
		
		
	}
	
	/**Creates a bomb using the player
	 */
	public void createBomb(int player) {
		if(player==1 && bomberman != null) {
			gameBoard.addBomb(bomberman.setBomb());
			System.out.println("t");
		} else if(bomberman2 != null) {
			gameBoard.addBomb(bomberman2.setBomb());
			System.out.println("t2");
		}
	}
	
	public boolean gameFinished() {
		return gameFinished;
	}
	
	public Player returnWinner() {
		if(bomberman == null) {
			return bomberman2;
		} else {
			return bomberman;
		}
		
	}
	
	/** Updates the gameboard
	 */
	public void run() {
		
		int count = 0;
		
		int tempSecond = 0;
		int second = 0;
		long waitTime = 30;
		double p1Invuln=0;
		double p2Invuln=0;
		
		
		
		while (!gameFinished) {
			bomberman = gameBoard.getFirstPlayer();
			bomberman2 = gameBoard.getSecondPlayer();
			long startTime = System.currentTimeMillis();
			if(bomberman != null)
				bomberman.move(gameBoard.getEntities());
			if(bomberman2 != null)
				bomberman2.move(gameBoard.getEntities());
			
				
				
				ArrayList<BreakableObstacle> b = new ArrayList<BreakableObstacle>();
				for(GameEntity[] g : gameBoard.getEntities())
				{
					for(GameEntity bo : g)
					{
						if(bo instanceof BreakableObstacle)
						{
							b.add((BreakableObstacle) bo);
						}
					}
				}
				
				count++;
				second = (int)(waitTime*count/1000);
				
				//runs once every second
				if(second-tempSecond > 0) {		
					//after a second explosions are gone from the screen
					gameBoard.removeExplosions();
					
					if(gameBoard.getBombs().size() > 0) {
						gameBoard.checkForExplosions();
					}	
					if(bomberman != null)
						System.out.println(bomberman.getLives()+" "+bomberman);
					if(bomberman2 != null)
						System.out.println(bomberman2.getLives()+" "+bomberman2);
					tempSecond = second;
					
					
				}
				
				
				
				//explosion checks all squares that it hits every 50 milliseconds
				//after it is created
				for(Explosion e : gameBoard.getExplosions())
				{
					for(BreakableObstacle bo : b)
					{
						if(e.isTouching(bo))
							gameBoard.removeBreakableObstacle(bo);
					}
					if(bomberman != null && e.isTouching(bomberman))
					{
						boolean a=bomberman.damage(1);
						if(!a) {
							gameBoard.removeFirstPlayer();
							bomberman = null;
						} else{
							bomberman.setInvulnerability(true);
							p1Invuln=2;
						}
					}
					if(bomberman2 != null && e.isTouching(bomberman2))
					{
						boolean a=bomberman2.damage(1);
						if(!a) {
							gameBoard.removeSecondPlayer();
							bomberman2 = null;
						} else{
							bomberman2.setInvulnerability(true);
							p2Invuln=2;
						}
					}
				}		
				
			p1Invuln-=0.04;
			p2Invuln-=0.04;
			if(bomberman != null && p1Invuln<=0) {
				bomberman.setInvulnerability(false);
			} if(bomberman2 != null && p2Invuln<=0) {
				bomberman2.setInvulnerability(false);
			}
			repaint();
		  	waitTime = 35 - (System.currentTimeMillis() - startTime);  // Consistent frame rate of 50 frames/sec
		  	
		  	if(bomberman == null) {
		  		gameFinished = true;
		  		System.out.println(bomberman2);
		  	} else if (bomberman2 == null) {
		  		gameFinished = true;
		  		System.out.println(bomberman);
		  	}
		  	if (waitTime > 0) {
			  	try {
					Thread.sleep(waitTime); 
			  	} catch (InterruptedException e) {}
		  	} else
		  		Thread.yield();
		}

		
		if(gameFinished) {
			window.changePanel(GameWindow.VICTORY_ID);
			
		}
		
		
	}
	
}
