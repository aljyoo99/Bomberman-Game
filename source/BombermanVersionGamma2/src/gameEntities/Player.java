package gameEntities;



public class Player extends MovableGameEntity{

	private int lives;
	private boolean invulnerable;
	private String name;
	
	
	public Player(int x, int y, String n) {
		super("Bomberman.png", x, y);
		this.lives=3;
		invulnerable=false;
		name = n;
	}
	
	public boolean damage(int damage)
	{
		if(lives > 1)
		{
			if(!invulnerable)
				lives-=damage;
			return true;
		}
		else if(invulnerable)
			return true;
		else
			return false;
	}
	public Bomb setBomb()
	{
		int xCoord=getX();
		int yCoord=getY();
		if(getX()%50==0)
			xCoord=getX();
		else{
			xCoord/=50;
			if(getX()-xCoord*50==25)//if equal distance to each nearby grid spot, choose randomly
				xCoord=((int)(Math.random()+1))+xCoord;
			else if(Math.abs(xCoord*50 - getX())  >  Math.abs((xCoord+1)*50) - getX())
			{
				xCoord=xCoord+1;
			}
			xCoord*=50;
		}
		if(getY()%50==0)
			yCoord=getY();
		else{
			yCoord/=50;
			if(getY()-yCoord*50==25)//if equal distance to each nearby grid spot, choose randomly
				yCoord=((int)(Math.random()+1))+yCoord;
			else if(Math.abs(yCoord*50 - getY())  >  Math.abs((yCoord+1)*50) - getY())
			{
				yCoord=yCoord+1;
			}
			yCoord*=50;
		}
		return new Bomb(xCoord,yCoord);
	}
	
	public boolean getInvulnerability()
	{
		return invulnerable;
	}
	public void setInvulnerability(boolean invulnerability)
	{
		invulnerable=invulnerability;
	}
	
	public int getLives()
	{
		return lives;
	}
	
	public String toString()
	{
		return name;
	}
}
