package gameEntities;


import java.util.ArrayList;

public abstract class MovableGameEntity extends GameEntity{
	private int velX, velY;
	private int dirX, dirY;
	
	public MovableGameEntity(String filename, int x, int y) {
		super(filename, x, y);
		// TODO Auto-generated constructor stub
		velX=0;
		velY=0;
		dirX = 0;
		dirY = 0;
	}
	
	public MovableGameEntity(ArrayList<String> filename, int x, int y){
		super(filename.get(0), x, y);
		
	}
	
	public void setVelocity(int xAmt, int yAmt) {
		
		velX=5*xAmt;
		velY=5*yAmt;
		
		dirX = xAmt;
		dirY = yAmt;
	}
	
		
	public void resetVelocity() {
		
		velX = 0;
		velY = 0;
		
	}
	
	public int getVelX() {
		return velX;
	}
	
	public void move(GameEntity[][] obstacles) {
		
		setRectangle((int)(getX()+velX), (int)(getY()+velY), getSideLength(), getSideLength());
		
		for(GameEntity[] entities: obstacles) {
			for(GameEntity g: entities) {
				
				if(g != null) {
					if(isTouching(g) && g instanceof Obstacle) {
						
						resetVelocity();						
						if(dirX < 0) {
							moveToLocation(getX()-velX,getY());
							
						} else if(dirX > 0) {
							moveToLocation(getX()+velX,getY());
							
						} else if(dirY < 0) {
							moveToLocation(getX(),getY()-velY);
							
						} else if(dirY > 0) {
							moveToLocation(getX(),getY()+velY);
							
						}
					} else {
						
					}
					
				}
				
				
			}
		}
		
		moveToLocation((int)(getX()+velX), (int)(getY()+velY));
		
	}

	
}
