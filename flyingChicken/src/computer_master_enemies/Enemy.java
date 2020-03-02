package computer_master_enemies;

import java.awt.Graphics;

import computer_master_graphics.loadImage;
import computer_master_manager.gameManager;

public class Enemy {
	
	private int x,y;
	
	public Enemy(int x , int y)
	{
		this.x=x;
		this.y=y;
	}
	
	public void tick()
	{
		if(gameManager.getLevel()==1)
		y += 1;
		if(gameManager.getLevel()==2)
		{
			y+=2;
		}
		if(gameManager.getLevel()==3)
			y+=3;
	}
	
	public void render(Graphics g)
	{
		g.drawImage(loadImage.enemy,x,y,50,50,null);
		
	}
	
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	

}
