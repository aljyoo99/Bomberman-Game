package gameEntities;


import java.awt.Point;


/**
 * A <code>Bomb</code> is an object that can explode, leaving 
 * behind an explosion object that covers a 2 by 2 cross surrounding and including
 * the tile this bomb was located on. <br />
 */
public class Bomb extends GameEntity{
	private int timer;
	
	/**Constructs a bomb object with given x and y and initializes the timer to 0
	 * When timer reaches 3, the bomb should detonate with the next tick of the timer
	 * @param x x coordinate
	 * @param y y coordinate
	 */
	public Bomb(int x, int y)
	{
		super("bomb.png", x, y);
		timer=0;
				
	}
	
	/**increments the timer, returning a boolean telling whether or not to explode
	 * @return a boolean explaining whether or not this object should detonate the next tick
	 * of the timer
	 */
	public boolean incrementTimer()
	{
		if(timer < 3) {
			timer++;
			return true;
		} else {
			return false;
		}
	}
	
	/**Draws this object with g
	 * @param g the graphics object used to draw this bomb
	 */
	/*public void draw(Graphics g, ImageObserver io)
	{
		Color temp = g.getColor();
		g.setColor(Color.ORANGE);
		g.drawOval(	getX(), getY(), getSideLength(), getSideLength());
		g.setColor(temp);
		
	}*/
	
	public Explosion explode(GameEntity[][] obstacles, Point[][] points){
	
		return new Explosion(getX(),getY(),2,obstacles, points);
	}
}
