package gui;
/**
	 * This interface links key movements that are done in ControlPanel to the
	 * DrawingPanel where the entities are drawn. It has five location String constants
	 * which are used by DrawingPanel to adjust the movement of the Player.
	 * 
	 */
public interface ControlListener {
	
	
	
	public static final String UP = "up";
	public static final String DOWN = "down";
	public static final String LEFT = "left";
	public static final String RIGHT = "right";
	public static final String NO_MOVEMENT = "";
	
	/**
	 * 
	 * @param direction String
	 */
	public void moveCharacter(int player, String direction);
	public void createBomb(int player);
}
