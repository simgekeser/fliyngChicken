package computer_master_bullets;

import java.awt.Color;
import java.awt.Graphics;

public class Bullet {
	
	private int x , y , speed;
	
	public Bullet(int x , int y)
	{
		this.x=x;
		this.y=y;
		speed=10;
	}
	
	public void tick()
	{
		y-=speed;
	}
	public int getY()
	{
		return y;
	}
	public int getX()
	{
		return x;
	}
	
	public void render(Graphics g)
	{
		g.setColor(Color.red);
		g.fillOval(x, y, 6, 10);
		g.setColor(Color.BLACK);
	}

}
