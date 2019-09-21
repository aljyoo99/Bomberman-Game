package gameEntities;



/**
 * A <code>Obstacle</code> is an object that can block other game entities. <br />
 */
public class Obstacle extends GameEntity{

	/**Contructs an obstacle with x,y coordinate
	 * @param x x coordinate
	 * @param y y coordinate
	 */
	public Obstacle(String filename, int x, int y)
	{
		super(filename, x, y);
	}
	
	public Obstacle(int x, int y)
	{
		super("obstacletile.jpg", x, y);
	}
	
	
}
