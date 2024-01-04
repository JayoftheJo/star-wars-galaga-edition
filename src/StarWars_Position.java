import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.ImageObserver;
public class StarWars_Position 
{
	// Fields (AKA Global variables) 
	private int beamxPos, beamyPos;
	private ImageIcon shipLeft, shipRight, shipUp, shipDown;

	//Constructor to initialize enemy, ship, and beam positions 
	public void StarWars_Postion()
	{
		beamxPos = 0;
		beamyPos = 0;
		shipLeft = new ImageIcon("shipLeft.png");
		shipRight = new ImageIcon("shipRight.png");
		shipUp = new ImageIcon("shipUp.png");
		shipDown = new ImageIcon("shipDown.png");
	}
	
	// Returns the x coordinate of the image we are using. 
	public int getshipX(int shipX, int dir)
	{
		StarWars_Position constr = new StarWars_Position();
		
		//To not let the ship get of screen if the ship is on the left side of the boundary or if its not, to move it 10 pixels left
		if(dir == 180)
		{
			if((shipX - 20) <= 0)
			{
				shipX -= 0;
			}
			else
			{
				shipX -= 15;
			}
		}
		
		//To not let the ship get of screen if the ship is on the right side of the boundary or if its not, to move it 10 pixels right
		if(dir == 0)
		{
			if((shipX + 150) >= 1200)
			{
				shipX += 0;
			}
			else
			{
				shipX += 15;
			}
		}
		return shipX;
	}
	
	//Returns the y coordinate of the image we are using. 
	public int getshipY(int shipY , int dir)
	{
		StarWars_Position constr = new StarWars_Position();
		
		//To not let the ship get of screen if the ship is on the upper side of the boundary or if its not, to move it 10 pixels up
		if(dir == 90)
		{
			if(shipY - 10 <= 0)
			{
				shipY -= 0;
			}
			else
			{
				shipY -= 15;
			}
		}
		
		//To not let the ship get of screen if the ship is on the bottom side of the boundary or if its not, to move it 10 pixels down
		if (dir == 270)
		{
			if(((shipY + 150) >= 900))
			{
				shipY += 0;
			}
			else
			{
				shipY += 15;
			}
		}
		
		return shipY;
	}
	
	//This method is used to move the beams x position
	public int getbeamX(int beamxPos, int b)
	{
		if(b == 0)
		{
			beamxPos += 20;
		}
		else if(b == 180)
		{
			beamxPos -= 20;
		}
		return beamxPos;
	}
	
	//This method is used to move the beams y position
	public int getbeamY(int beamyPos, int b)
	{
		if(b == 90)
		{
			beamyPos -= 20;
		}
		else if(b == 270)
		{
			beamyPos += 20;
		}
		return beamyPos;
	}
}
