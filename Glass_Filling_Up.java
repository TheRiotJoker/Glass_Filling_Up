import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
public class Glass_Filling_Up
extends JFrame
{
	Color upperBackground = new Color(255,255,255);
	Color background = new Color(100, 149, 237);
	Image doubleBufferImg;
	Graphics doubleBufferGraphics;
	public static int showBubbles = 20;
	public static int glassHeight = 990;
	public static int width = 1000;
	public static int height = 1000;
	public static int render = 0;
	private static final long serialVersionUID = 1L;
	static bubble[] bubbles = new bubble[500];
	public static void main(String[] args) throws InterruptedException
	{
		Glass_Filling_Up gui = new Glass_Filling_Up();
		gui.setSize(width, height);
		gui.setVisible(true);
		gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
		gui.setResizable(false);
		for(int i = 0; i < bubbles.length; i++)
		{
			bubbles[i] = new bubble();
			bubbles[0].setFirstBubble(true);
		}
		for(int i = 0; i < bubbles.length; i++)
		{
			bubbles[i].setOutterX(randNumb(800,200));
			bubbles[i].setOutterY(randNumb(1200,1000));
			bubbles[i].setOutterHeight(randNumb(20,10));
			bubbles[i].setOutterWidth(bubbles[i].getOutterHeight());
			bubbles[i].setInnerX(bubbles[i].getOutterX()+(int)(bubbles[i].getOutterWidth()/3.75));
			bubbles[i].setInnerY(bubbles[i].getOutterY()+(int)(bubbles[i].getOutterHeight()/3.75));
			bubbles[i].setInnerWidth(bubbles[i].getOutterWidth()/2);
			bubbles[i].setInnerHeight(bubbles[i].getOutterHeight()/2);
			bubbles[i].setSpeed((int)(bubbles[i].getOutterHeight()*0.8));
		}
		while(true)
		{
			if(glassHeight > 201)
			{
				if(glassHeight % 2 == 0)
				{
					if(render < bubbles.length)
					{
						render += 4;
					}
				}
				else
				{
					if(glassHeight == 207)
					{
						render = bubbles.length;
					}
				}
				glassHeight = glassHeight-3;
			}
			for(int i = 0; i < render; i++)
			{
				if(bubbles[i].getOutterX()+10+bubbles[i].getOutterWidth() > 800)
				{
					bubbles[i].setOutterX(bubbles[i].getOutterX()+randNumb(4,0)*(-1));
				}
				else
				{
					if(bubbles[i].getOutterX()-10-bubbles[i].getOutterWidth() < 195)
					{
						bubbles[i].setOutterX(bubbles[i].getOutterX()+randNumb(4,0));
					}
					else
					{
						if(randNumb(10,0) >= 5)
						{
							bubbles[i].setOutterX(bubbles[i].getOutterX()+randNumb(4,0));
						}
						else
						{
							bubbles[i].setOutterX(bubbles[i].getOutterX()+randNumb(4,0)*(-1));
						}		
					}
				}
				bubbles[i].setOutterY(bubbles[i].getOutterY()-bubbles[i].getSpeed());
				bubbles[i].setInnerX(bubbles[i].getOutterX()+(int)(bubbles[i].getOutterWidth()/3.75));
				bubbles[i].setInnerY(bubbles[i].getOutterY()+(int)(bubbles[i].getOutterHeight()/3.75));
				if(bubbles[i].getInnerY() < glassHeight+3)
				{
					bubbles[i].setOutterX(randNumb(760,200));
					bubbles[i].setInnerX(bubbles[i].getOutterX()+(int)(bubbles[i].getOutterWidth()/3.75));
					bubbles[i].setOutterY(randNumb(1200,1000));
					bubbles[i].setInnerY(bubbles[i].getOutterY()+(int)(bubbles[i].getOutterHeight()/3.75));
				}
			}
			gui.repaint();
			Thread.sleep(30);
		}
	}
	public void paint(Graphics g)
	{
		doubleBufferImg = createImage(width, height); //creates an image of the current screen
		doubleBufferGraphics = doubleBufferImg.getGraphics(); //gets the graphics of the current screen
		paintComponent(doubleBufferGraphics); //takes the graphics
		g.drawImage(doubleBufferImg,0,0,this); //draws them to the screen (i don't completely understand this either)
	}
	public void paintComponent(Graphics g)
	{
		Color wood = new Color(139,69,19);
		Color glassColor = new Color(0,0,0);
		Color insideColor = new Color(255,255,255);
		g.setColor(wood);
		g.fillRect(0,0,width,100);
		for(int i = -105; i < height; i = i+100)
		{
			g.setColor(glassColor);
			g.fillRect(0,i-5,width,i+5);
			g.setColor(wood);
			i += 5;
			g.fillRect(0,i,width,i);
		}
		g.setColor(glassColor);
		g.fillRect(195,195,610,810);
		g.setColor(insideColor);
		g.fillRect(200,200,600,800);
		g.setColor(background);
		g.fillRect(200,glassHeight,600,800);
		for(int i = 0; i < render; i++)
		{
			g.setColor(upperBackground);
			g.fillOval(bubbles[i].getOutterX(), bubbles[i].getOutterY(),bubbles[i].getOutterWidth(), bubbles[i].getOutterHeight());
			g.setColor(background);
			g.fillOval(bubbles[i].getInnerX(), bubbles[i].getInnerY(),bubbles[i].getInnerWidth(), bubbles[i].getInnerHeight());
			g.setColor(glassColor);
			g.fillRect(200,990,600,10);
		}
	}
	public static int randNumb(int max, int min)
	{
		Random random = new Random();
		return random.nextInt((max-min)+1)+min;
	}
}
class bubble
{
	boolean firstBubble = false;
	int outterWidth = 0;
	int outterHeight = 0;
	int innerWidth = 0;
	int innerHeight = 0;
	int innerX = 0;
	int innerY = 0;
	int outterX = 0;
	int outterY = 0;
	int speed = 0;
	int getSpeed()
	{
		return speed;
	}
	void setSpeed(int speed)
	{
		this.speed = speed;
	}
	boolean getFirstBubble()
	{
		return firstBubble;
	}
	void setFirstBubble(boolean firstBubble)
	{
		this.firstBubble = firstBubble; 
	}
	void setOutterX(int outterX)
	{
		this.outterX = outterX;
	}
	int getOutterX()
	{
		return outterX;
	}
	void setOutterY(int outterY)
	{
		this.outterY = outterY;
	}
	int getOutterY()
	{
		return outterY;
	}
	void setOutterWidth(int outterWidth)
	{
		this.outterWidth = outterWidth;
	}
	int getOutterWidth()
	{
		return outterWidth;
	}
	void setOutterHeight(int outterHeight)
	{
		this.outterHeight = outterHeight;
	}
	int getOutterHeight()
	{
		return outterHeight;
	}
	int getInnerX()
	{
		return innerX;
	}
	void setInnerX(int innerX)
	{
		this.innerX = innerX;
	}
	int getInnerY()
	{
		return innerY;
	}
	void setInnerY(int innerY)
	{
		this.innerY = innerY;
	}
	void setInnerHeight(int innerHeight)
	{
		this.innerHeight = innerHeight;
	}
	int getInnerHeight()
	{
		return innerHeight;
	}
	void setInnerWidth(int innerWidth)
	{
		this.innerWidth = innerWidth;
	}
	int getInnerWidth()
	{
		return innerWidth;
	}
	

}