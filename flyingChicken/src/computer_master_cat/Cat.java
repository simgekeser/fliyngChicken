package computer_master_cat;

import java.awt.Graphics;

import computer_master_graphics.loadImage;

public class Cat {
	
	private int x,y;
	
	public Cat(int x , int y)
	{
		this.x=x;
		this.y=y;
	}
	
	public void tick()
	{
		y += 1;
	}
	
	public void render(Graphics g)
	{
		g.drawImage(loadImage.cat,x,y,50,50,null);
		
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
