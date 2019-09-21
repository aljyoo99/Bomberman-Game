package gameEntities;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.*;
import java.awt.image.ImageObserver;
import java.util.*;

import javax.swing.ImageIcon;

/**
	 * A <code>GameEntity</code> is an object that can be drawn on the Gameboard, having
	 * an x,y coord and set width and height. <br />
	 */
public abstract class GameEntity{
	private int yCoord;
	private int xCoord;
	private ArrayList<Image> images;
	public static int entity_dimension = 50;
	private Rectangle2D.Double rectangle;
	
	
	
	/**Constructs a GameEntity with given x,y coords and a width and height set to 50
	 * @param x x coordinate
	 * @param y y coordinate
	 */
	
	public GameEntity(String filename, int x, int y) {
		this((new ImageIcon(filename)).getImage(), x, y);
		
	}
	
	public GameEntity(Image i, int x, int y)
	{
		images = new ArrayList<Image>();
		xCoord = x;
		yCoord = y;
		images.add(i);
		rectangle = new Rectangle2D.Double(x, y, entity_dimension, entity_dimension);
	}
	
	public GameEntity(int x, int y)
	{
		xCoord = x;
		yCoord = y;
		rectangle = new Rectangle2D.Double(x, y, entity_dimension, entity_dimension);
	}
	
	/**Unimplemented draw method which uses a graphics object to draw
	 *  a colored rectangle(soon to be images) as a representation for each object
	*/
	public void draw(Graphics g, ImageObserver io){
		g.drawImage(images.get(0), xCoord, yCoord, entity_dimension, entity_dimension, io);
	}
	
	
	public int getX()
	{
		return xCoord;
	}
	public int getY()
	{
		return yCoord;
	}
	
	public int getSideLength()
	{
		return entity_dimension;
	}
	
	public Rectangle2D.Double getRectangle()
	{
		return rectangle;
	}
	
	
	public void setX(int x)
	{
		xCoord=x;
	}
	public void setY(int y)
	{
		yCoord=y;
	}
	
	public void setRectangle(int x, int y, int width, int height)
	{
		rectangle.setRect(x, y, width, height);
	}
	
	public void moveToLocation(int x, int y)
	{
		xCoord = x;
		yCoord = y;
	}
	
	/**@param other another gameEntity to compare to, cannot be null
	 * @return a boolean representing whether or not this GameEntity intersects/collides with the other GameEntity
	 */
	public boolean isTouching(GameEntity other)
	{	
		Rectangle2D.Double otherR = other.getRectangle();
		return rectangle.intersects(otherR);
	}
}
