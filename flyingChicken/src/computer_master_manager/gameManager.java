package computer_master_manager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import computer_master_bullets.Bullet;
import computer_master_display.Display;
import computer_master_enemies.Enemy;
import computer_master_entity.Player;
import computer_master_setUp.gameSetUp;
import computer_master_cat.Cat;

public class gameManager implements KeyListener {

	private Player player;
	public static ArrayList<Bullet> bullet;
	private ArrayList<Enemy> enemies;
	private ArrayList<Cat> cat;
	private int catint = 0 ;
	private long current;
	private long delay;
	private int health;
	private int score;
	private boolean start;
	private  static int level = 1;
	
	public static int getLevel() {
		return level;
	}

	public static void setLevel(int level) {
		gameManager.level = level;
	}

	public gameManager()
	{
		
	}
	
	public void init()
	{
		
		Display.frame.addKeyListener(this);
		player=new Player(300 , 300);
		player.init();
		bullet=new ArrayList<Bullet>();
		enemies=new ArrayList<Enemy>();
		cat=new ArrayList<Cat>();
		current=System.nanoTime();
		delay=1000;//mikrop çýkma sayýsý
		
		health=player.getHealth();
		score=0;
		level=1;
	}
	
	public void tick()
	{
		if(start)
		{
			player.tick();
			
			for(int i=0;i<bullet.size();i++)
			{
				bullet.get(i).tick();
			}
			
			long breaks=(System.nanoTime()-current)/1000000;
			
			if(breaks>delay)
			{
				catint++;
				for(int i=0;i<1;i++)
				{ System.out.println(cat.size());
					Random rand = new Random();
					int randX=rand.nextInt(450);
					int randY=rand.nextInt(450);
					int randcatx=rand.nextInt(450);
					int randcaty=rand.nextInt(450);
					if(health>0)
					{
						enemies.add(new Enemy(randX,-randY));
						if(catint%3==0) {
						
						cat.add(new Cat(randcatx,-randcaty));
						}
					}
				}
					current=System.nanoTime();
				}
				
				
				
				//enemies
				for(int i=0;i<enemies.size();i++)
				{
					enemies.get(i).tick();
				}
				
				//cats
				for(int i=0;i<cat.size();i++)
				{
					cat.get(i).tick();
				}
			}
		}
		
		public void render(Graphics g)
		{
			if(start) 
			{
				player.render(g);
			
				//bullet
				for(int i=0;i<bullet.size();i++)
				{
					bullet.get(i).render(g);
				}
				
				for(int i=0;i<bullet.size();i++)
				{
					
					if(bullet.get(i).getY()<=50)
					{
						bullet.remove(i);
						i--;
					}
				}
				
				
				for(int i=0;i<cat.size();i++)
				{
					if(!(cat.get(i).getX()<=30 || cat.get(i).getX()>=450-30 || cat.get(i).getY()>=450-30))
					{
						if(cat.get(i).getY()>=30)
						{
							cat.get(i).render(g);
						}
					}
				}
				
				//enemies
				
				
				for(int i=0;i<enemies.size();i++)
				{
					if(!(enemies.get(i).getX()<=50 || enemies.get(i).getX()>=450-50 || enemies.get(i).getY()>=450-50))
					{
						if(enemies.get(i).getY()>=50)
						{
							enemies.get(i).render(g);
							System.out.println(enemies.get(i).getX());
						}
					}
				}
				
				//cat
				
				
				
				
				
				//enemies & player collision
				
				for(int i=0;i<enemies.size();i++)
				{
					int ex=enemies.get(i).getX();
					int ey=enemies.get(i).getY();
					
					int px=player.getX();
					int py=player.getY();
					
					if(px<ex+50 && px+60>ex&& py<ey+50&& py+60>ey)
					{
						enemies.remove(i);
						i--;
						health--;
						
						System.out.println(health);
						
						if(health<=0)
						{
							enemies.removeAll(enemies);
							player.setHealth(0);
							start=false;
						}
					}
					
					//cat player
					
					for(int c=0;c<cat.size();c++)
					{
						int cex=cat.get(c).getX();
						int cey=cat.get(c).getY();
						
						int cpx=player.getX();
						int cpy=player.getY();
						
						if(cpx<cex+50 && cpx+60>cex&& cpy<cey+50&& cpy+60>cey)
						{
							System.out.println("bbbb");
							cat.remove(c);
							c--;
							health=health-3;
							
							System.out.println(health);
							
							if(health<=0)
							{
								cat.removeAll(cat);
								player.setHealth(0);
								start=false;
							}
						}
					
					}
					//bullets && enemy collision
					
					for(int j=0;j<bullet.size();j++)
					{
						int bx=bullet.get(j).getX();
						int by=bullet.get(j).getY();
						
						if(ex<bx+6 && ex+50>bx && ey<by+6 && ey+50>by)
						{
							enemies.remove(i);
							i--;
							bullet.remove(j);
							j--;
							score=score+5;
							
							if(score > 20)
							{
								level=2;
							}
							if(score > 40)
							{
								level=3;
							}
							
						}
					}
					
					
					g.setColor(Color.blue);
					g.setFont(new Font("arial",Font.BOLD,40));
					g.drawString("Score : "+score+"  Level:"+level,70,500);
					g.setColor(Color.green);
					g.drawString("Health : "+health, 100,50);
					
				}
			}
			
			else {
				g.setFont(new Font("arial",Font.PLAIN,20));
				g.drawString("PRESS ENTER TO START", 100, (gameSetUp.gameHeight/2)+50);
				g.setFont(new Font("arial",Font.BOLD,33));
				g.drawString("CHICK GAME", 100, (gameSetUp.gameHeight/5)+80);
			}
		}
		
		



	@Override
	public void keyPressed(KeyEvent e) {
		int source = e.getKeyCode();
		if(source==KeyEvent.VK_ENTER)
		{
			start=true;
			init();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}



