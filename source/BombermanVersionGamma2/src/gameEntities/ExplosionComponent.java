package gameEntities;



/**
 * A <code>ExplosionComponent</code> is a game entity object representing an
 * explosion on a single tile. <br />
*/
public class ExplosionComponent extends GameEntity{
	
	/**Constructs with given x,y
	 * @param x x coord
	 * @param y y coord
	 */
	public ExplosionComponent(int x, int y) {
		super("explosiontile.jpg",x, y);
	}
	
	
}
