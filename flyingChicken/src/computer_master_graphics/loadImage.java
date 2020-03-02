package computer_master_graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class loadImage {
	
	public static BufferedImage image;
	public static BufferedImage entities;
	public static BufferedImage player,enemy,cat;
	
	public static void init()
	{
		image=imageLoader("/farm.jpg");
		entities=imageLoader("/chick.png");
		enemy=imageLoader("/enemy.png");
		cat=imageLoader("/cat.png");
		
		
		crop();
	}
	
	public static BufferedImage imageLoader(String path)
	{
		try {
			return ImageIO.read(loadImage.class.getResource(path));
		}catch(IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	
	public static void crop()
	{
		enemy = enemy.getSubimage(0, 0, 128, 96);
		player= entities.getSubimage(0, 0, 128, 128);
		
	}
	

}
