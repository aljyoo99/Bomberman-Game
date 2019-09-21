package gameEntities;
import java.awt.Color;
import java.awt.Graphics;

import java.awt.Point;

import java.awt.image.ImageObserver;
import java.util.ArrayList;

/**
 * A <code>Explosion</code> is a representation of an explosion. 
 * It is constructed from several smaller explosionComponent objects, as 
 * GameEntity objects are restricted with a pre-defined width and height.
 * @TODO The explosion object will destroy non-obstacle objects(exluding breakableObstacle)
 * in its explosion range.
 * <br />
 */
public class Explosion{
	private int posX;
	private int posY;
	private int power;
	//private ImageObserver io;
	private ArrayList<ExplosionComponent> explosion = new ArrayList<ExplosionComponent>();
	
	/**Constructs an explosion object with given x and y coordinate
	 * @param x x coordinate
	 * @param y y coordinate
	 */
	public Explosion(int x, int y, int p, GameEntity[][] obstacles, Point[][] points)
	{
		
		posX = x;
		posY = y;
		power = p;
		initiateExplosions(obstacles, points);
		
	}
	
	public void initiateExplosions(GameEntity[][] obstacles, Point[][] points)
	{
		int expcol = 1;
		int exprow = 1;
		
		for(int i = 0; i < points.length; i++) {
			for(int j = 0; j < points[0].length; j++) {
				Point p = points[i][j];
				int px = (int)(p.getX());
				int py = (int)(p.getY());
				if(py == posY && px == posX) {
					expcol = j;
					exprow = i;
				}
			}
		}
		
		//centerpiece
		explosion.add(new ExplosionComponent(posX, posY));
		
		//vertical direction
		int i = exprow;
		boolean stop = false;
		while(i >= exprow - power && !stop) {
			
			GameEntity ge = obstacles[i][expcol];
			if(ge == null || !(ge instanceof Obstacle)) {
				Point p = points[i][expcol];
				int x = (int)(p.getX());
				int y = (int)(p.getY());
				explosion.add(new ExplosionComponent(x, y));
			} else {
				if(ge instanceof BreakableObstacle) {
					Point p = points[i][expcol];
					int x = (int)(p.getX());
					int y = (int)(p.getY());
					explosion.add(new ExplosionComponent(x, y));
				}
				stop = true;
			}
			i--;
		}
		
		i = exprow;
		stop = false;
		while(i <= exprow + power && !stop) {
			GameEntity ge = obstacles[i][expcol];
			if(ge == null || !(ge instanceof Obstacle)) {
				Point p = points[i][expcol];
				int x = (int)(p.getX());
				int y = (int)(p.getY());
				explosion.add(new ExplosionComponent(x, y));
			} else {
				if(ge instanceof BreakableObstacle) {
					Point p = points[i][expcol];
					int x = (int)(p.getX());
					int y = (int)(p.getY());
					explosion.add(new ExplosionComponent(x, y));
				}
				stop = true;
			}
			i++;
		}
		
		//horizontal direction
		int j = expcol;
		stop = false;
		while(j >= expcol - power && !stop) {
			GameEntity ge = obstacles[exprow][j];
			if(!(ge instanceof Obstacle)) {
				Point p = points[exprow][j];
				int x = (int)(p.getX());
				int y = (int)(p.getY());
				explosion.add(new ExplosionComponent(x, y));
			} else {
				if(ge instanceof BreakableObstacle) {
					Point p = points[exprow][j];
					int x = (int)(p.getX());
					int y = (int)(p.getY());
					explosion.add(new ExplosionComponent(x, y));
				}
				stop = true;
			}
			j--;
		}
		
		j = expcol;
		stop = false;
		while(j <= expcol + power && !stop) {
			GameEntity ge = obstacles[exprow][j];
			if(!(ge instanceof Obstacle)) {
				Point p = points[exprow][j];
				int x = (int)(p.getX());
				int y = (int)(p.getY());
				explosion.add(new ExplosionComponent(x, y));
			} else {
				if(ge instanceof BreakableObstacle) {
					Point p = points[exprow][j];
					int x = (int)(p.getX());
					int y = (int)(p.getY());
					explosion.add(new ExplosionComponent(x, y));
				}
				stop = true;
			}
			j++;
		}
	}	
	
	/**draws this object with specified Graphics component
	 */
	public void draw(Graphics g, ImageObserver io)
	{
		Color temp = g.getColor();
		g.setColor(Color.YELLOW);
		for(ExplosionComponent e : explosion)
			if(e!=null)
				e.draw(g, io);
		g.setColor(temp);
	}
	
	/**@return true if touching other object
	 */
	public boolean isTouching(GameEntity other)
	{	
		boolean isTouching=false;
		for(ExplosionComponent e : explosion)
			if(e!=null && e.isTouching(other))
				isTouching=true;
		return isTouching;
	}
}