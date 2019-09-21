package gameboard;


import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import gameEntities.*;

public class GameBoard {

	private GameEntity [][] staticEntities;
	private Point [][] positions;
	
	private ArrayList<MovableGameEntity> moveEntities;
	private ArrayList<Explosion> exps;	
	private ArrayList<Bomb> bombs;
	
	private static int length = 650;
	private Player p, p2;
	
	public GameBoard() {
		staticEntities = new GameEntity[13][13];
		positions = new Point[13][13];
		generateLocations();
		
		moveEntities = new ArrayList<MovableGameEntity>();
		bombs = new ArrayList<Bomb>();
		exps = new ArrayList<Explosion>();
		
		
		createBoundaries();
		generateLevel();
		initiateTwoPlayer();
	}
	
	/**
	 * @param mEntities ArrayList<MovableGameEntity>
	 * @post ArrayList of MovableGameEntity and 2d arrays of Point and GameEntity are initialized
	 */
	public GameBoard(ArrayList<MovableGameEntity> mEntities) {
		staticEntities = new GameEntity[13][13];
		positions = new Point[13][13];
		generateLocations();
				
		moveEntities = mEntities;
		bombs = new ArrayList<Bomb>();
		exps = new ArrayList<Explosion>();
		
		
		createBoundaries();
		generateLevel();
		initiateTwoPlayer();
	}
	
	private void generateLocations() {
		int sideLength = length/positions.length;
		for(int row = 0; row < positions.length; row++) {
			for(int col = 0; col < positions.length; col++) {
				Point point = new Point(col*sideLength,row*sideLength);
				positions[row][col] = point;
				
			}
			
		}
	}
	
	private void createBoundaries() {
		for(int i = 0; i < staticEntities.length; i++){
			Point top = positions[0][i];
			int x = (int)top.getX();
			int y = (int)top.getY();
			
			staticEntities[0][i] = new Obstacle(x,y);
		}
		
		for(int i = 1; i < staticEntities.length-1; i++){
			Point left = positions[i][0];
			int x1 = (int)left.getX();
			int y1 = (int)left.getY();
			Point right = positions[i][staticEntities.length-1];
			int x2 = (int)right.getX();
			int y2 = (int)right.getY();
			staticEntities[i][0] = new Obstacle(x1, y1);
			staticEntities[i][staticEntities.length-1] = new Obstacle(x2, y2);
			
		}
		
		for(int i = 0; i < staticEntities.length; i++){
			Point bottom = positions[staticEntities.length-1][i];
			int x = (int)bottom.getX();
			int y = (int)bottom.getY();
			staticEntities[staticEntities.length-1][i] = new Obstacle(x, y);
		}
		
	}
	
	private void generateLevel() {
		for(int row = 2; row < positions.length-2; row++) {
			for(int col = 2; col < positions.length-2; col++) {
					Point top = positions[row][col];
					int x = (int)top.getX();
					int y = (int)top.getY();
				if(row % 3 == 0 && col % 3 == 0){	
					staticEntities[row][col] = new Obstacle(x,y);
					
				} else {
					staticEntities[row][col] = new BreakableObstacle(x,y);
				}
			}
		}
	}
	
	
	
	private void initiateTwoPlayer() {
		//moveEntities.add(new Player(50, 50));
		//firstPlayerIndex = 0;
		p = new Player(50, 50, "player 1");
		p2 = new Player(550, 550, "player 2");
	}
	
	
	
	public void addMovableGameEntity(MovableGameEntity mentity) {
		moveEntities.add(mentity);
	}
	
	public void addBomb(Bomb b) {
		
		bombs.add(b);
	}
	
	
	/**
	 * 
	 * @param g Graphics
	 * @param io ImageObserver
	 * 
	 * @post draw method of GameEntities are called
	 */
	public void drawEntities(Graphics g, ImageObserver io) {
		for(int i = 0; i < staticEntities.length; i++) {
			for(int j = 0; j < staticEntities.length; j++) {
				GameEntity entity = staticEntities[i][j];
				if(entity != null)
					entity.draw(g, io);
			}
		}
		/*for(int i = 0; i < moveEntities.size(); i++){
			MovableGameEntity mentity = moveEntities.get(i);
			if(mentity != null) {
				mentity.draw(g, io);
			}
		}*/
		if(p != null)
			p.draw(g, io);
		if(p2 != null)
			p2.draw(g, io);
	}
	
	
	
	public GameEntity[][] getEntities() {
		return staticEntities;
	}
	
	public ArrayList<Bomb> getBombs() {
		
		return bombs;
	}
	
	public ArrayList<Explosion> getExplosions() {
		
		return exps;
	}
	
	public Player getFirstPlayer() {
		
		return p;//(Player)moveEntities.get(firstPlayerIndex);
	}
	public Player getSecondPlayer() {
		
		return p2;//(Player)moveEntities.get(firstPlayerIndex);
	}
	
	
	
	
	/**
	 * @post Bombs are removed from the GameBoard
	 * Explosions are created
	 */
	public void checkForExplosions() {
		
		int i = 0;
		while(i < bombs.size()) {
			Bomb bomb = bombs.get(i);
			bomb.incrementTimer();
			if(!bomb.incrementTimer()) {
				Explosion e = bomb.explode(staticEntities, positions);
				exps.add(e);
				bombs.remove(i);
				
			} else {
				i++;
			}
				
		}
	}
	/**
	 * 
	 * @param bo
	 * @post indicated BreakableObstacle is removed from the 2d array
	 * 
	 */
	public void removeBreakableObstacle(BreakableObstacle bo)
	{
		for(int x=0; x < staticEntities.length; x++)
		{
			for(int y=0; y < staticEntities[x].length; y++)
			{
				if (staticEntities[x][y] != null && staticEntities[x][y].equals(bo))
				{
					staticEntities[x][y]=null;
					break;
				}
			}
		}
	}
	
	/**
	 * @post first player p is removed
	 * 
	 */
	public void removeFirstPlayer()
	{
		/*if(moveEntities.size() > 0 && moveEntities.get(firstPlayerIndex) != null)
			moveEntities.remove(firstPlayerIndex);
		*/
		p = null;
	}
	
	/**
	 * @post second player p2 is removed
	 * 
	 */
	
	public void removeSecondPlayer()
	{
		/*if(moveEntities.size() > 0 && moveEntities.get(firstPlayerIndex) != null)
			moveEntities.remove(firstPlayerIndex);
		*/
		p2 = null;
	}
	
	public void removeExplosions() {
		while(exps.size() > 0)
			exps.remove(exps.size()-1);
	}
}



