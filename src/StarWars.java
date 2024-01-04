/* Star Wars: Revenge of the Rebels CPT
 * Date: Thursday, January 28th, 2021
 * Course Code: ICS 3UI 
 * Jaejo Antony Manjila
 * Mr.Conway
 * 
 * Brief Details: This program lets the user play the game Star Wars: Last One Standing where the objective is to shoot
 * all the enemy drones before they reach the bottom of the screen or hit the actual player. The player will be able to
 * control movements of a ship with the arrow keys and has the ability to shoot the enemy drones and destroy them by pressing 
 * the spacebar. Each enemy drone destroyed is 100 points and every drone which have reached the bottom of the screen is 100 point deducted. 
 * 
 * Brief Description:  
 * 
 * Fields - used to prepare my variables that I am going to use throughout the program
 * 
 * Constructor - used to initialize my field variables and build my JFrame. Also creating collision boxes for images that have need collision in the program
 * 			   - enemy timer is started to spawn my enemies
 * 
 * ActionPerformed - used to get the sources of my two timers everytime they run one cycle: enemyTimer and beamTimer
 * 				   - enemyTimer used to spawn enemies. This timer also detects if the beams intersects enemy or the enmy intersects the ship
 * 				   - beamTimer used to move the shooting beams the ship shoots. This is started when the user wants to shoot and hits the spacebar
 * 
 * KeyPressed - actions for when the user hits either 6 buttons: up, down, left , right, spacebar or enter
 * 			  - each arrow key uses the different class to obtain their new x or y position
 * 			  - Up: when user pressed the up arrow key, ship is moved up certain pixels
 * 			  - Down: when user pressed the down arrow key, ship is moved down certain pixels
 * 			  - Right: when user pressed the right arrow key, ship is moved right certain pixels
 * 			  - Left: when user pressed the left arrow key, ship is moved left certain pixels
 * 			  - Spacebar: when the user presses spacebar, the beamTimer starts therefore spawning beams that move in the direction the ship faces
 * 			  - Enter: this is only applicable in the title, when the user pressed enter, they are directed into the game
 * 
 * Paint - if boolean titleCheck equals true, JFrame will change and bring in new images to begin the game
 * 		 - first, will ask the user for their username until they input the proper length and characters
 * 		 - after, it will bring in the ship, enemy(if the boolean enemyVis = true), and the beams(if the spacebar was pressed)
 * 		 - prints the score on the bottom left, increasing by 10 each time the beam intersects the enemy
 * 	
 * IF Statements - used in keyPressed and actionPerformed events to check what buttons are pressed and what timers are running
 * 				 - also used in paint method to check conditions of the title and the input the user types in
 * 
 * Input/Ouput - used in paint method where user is supposed to type their username
 * 			   - output messages when the ship intersects the enemy(found at line 173)
 * 			   - they are also outputted when user doesn't input the correct format in the paint method
 * 
 * Random Numbers - used to determine a random x position for the enemy to spawn 
 * 
 * Loops - used in the paint method to check each character of the input of the user. This is to know if the characters meets the conditions of an eligible username
 * 
 * String Class Methods - checks if the input from user is a character in the paint method. This is used so that every inputted character is always a letter
 * 
 * Try Catch Methods - used in line 238, right after the score hits a certain number to have the user choose his option before a certain time
 * 
 * Arrays - used as a variable to hold a level system. After reaching a certain score, this array is used to display the level change in a message to the user
 * 		  - used as a variable to hold a score system. This is used to check if the score is one of the numbers in the array so that it knows there's a level change
 * 
 * Returning - at line 303, I access the other class where I try to get the x position of the ship by basing it on direction and adding/substracting to get the new point and returning it to the main program.
 * 
 * Additional Class - StarWars_Position class is used to return the x and y positions of both the ship and the beams
 * 
 * JOptionPane Class - used to diplay my game with all the image incorportated in it
 * 
 * JFrame Class - used with keyboard press events such as the arrow keys
 * 
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class StarWars extends JPanel implements KeyListener, ActionListener
{
	//All the fields needed to use throughout the class
	private ImageIcon ship, shipRight, shipLeft, shipUp, shipDown, enemy, beam, beamVer, beamHor, titleimg, title;
	private int shipX, shipY, enemyX, enemyY, beamX, beamY, score, beamDir, dir, contin, speedChange, countRank;
	private String userName;
	private String[] rank = new String [] {"RECRUIT", "CAPTAIN", "COLONEL", "JEDI"};
	private int[] scoreCount = new int [] {50, 100, 150, 200};
	private boolean titleCheck, isFire, enemyVis, name;
	private JFrame frame;
	private Timer beamTimer, enemyTimer, enemyMov;
	private Rectangle2D shipBox, enemyBox, beamHorBox, beamVerBox;
	private Font f;
	private FontMetrics fm;
	
	
	
	public static void main(String[] args) 
	{
		new StarWars();
	}
	
	//Constructor
	public StarWars()
	{	
		//Images variables
		shipLeft = new ImageIcon("images/shipLeft.png");
		shipRight = new ImageIcon("images/shipRight.png");
		shipUp = new ImageIcon("images/shipUp.png");
		shipDown = new ImageIcon("images/shipDown.png");
		ship = new ImageIcon("images/shipUp.png");
		enemy = new ImageIcon("images/enemy.png");
		beam = new ImageIcon("");
		beamVer = new ImageIcon("images/beamVer.png");
		beamHor = new ImageIcon("images/beamHor.png");
		titleimg = new ImageIcon("images/titleimg.png");
		title = new ImageIcon("images/titlebrgrnd.png");
		
		//Integer variables
		shipX = 450;
		shipY = 600;
		enemyX = 0;
		enemyY = 0;
		score = 0;
		speedChange = 5;
		countRank = 0;
		
		//Direction indicators
		dir = 90;
		beamDir = 90;
		
		//Booleans variables
		titleCheck = true;
		isFire = false;
		enemyVis = false;
		name = true;
		
		//Timers variables
		enemyTimer = new Timer(speedChange, this);
		beamTimer = new Timer(50, this);
		enemyMov = new Timer(50, this);
		
		//Font and font metric variables
		f = new Font("Britannic Bold", Font.PLAIN, 30);
		fm = getFontMetrics(f);
		
		setLayout(null);
		addKeyListener(this);
		setFocusable(true);
		requestFocus();
		setBackground(Color.BLACK);
		
		//Constructing the JFrame 
		frame = new JFrame();
		frame.setContentPane(this);
		frame.setSize(900, 800);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setTitle("STAR WARS: Last One Standing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
		//For the actions everytime a timer runs one cycle
		public void actionPerformed(ActionEvent e)
		{
			StarWars_Position data = new StarWars_Position();
			
			//Timer to spawn enemy every second
			if(e.getSource() == enemyTimer)
			{
				//Bringing in random if enemy is not in the panel
				if(enemyX > 900)
				{
					Random rand = new Random();
					enemyX = rand.nextInt(700) + 50;
					enemyY = 0;
				}
				enemyVis = true;
				
				//To start the timer that moves the enemy
				enemyMov.start();
				enemyTimer.stop();
				repaint();	
			}
			
			//Timer to move the enemy down 10 pixels every cycle
			if(e.getSource() == enemyMov)
			{
				enemyY += speedChange;
				
				//If the enemy is below the panel, to decerase the score
				if(enemyY >= 860)
				{
					score -= 50;
					
					//After the decrease, if the score is less than or equal to zero, to end the game
					if(score <= 0)
					{
						//Making JPanel blank and outputting a message while exiting out after
						setVisible(false);
						JOptionPane.showMessageDialog(null, "Sadly, we have lost. Thank You for your Support, Commander!");
						System.exit(0);
					}
				}
				//Initializing collision boxes for ship, enemy, beams, boss
				shipBox = new Rectangle2D.Double(shipX, shipY, shipUp.getIconWidth(), shipUp.getIconHeight());
				enemyBox = new Rectangle2D.Double(enemyX, enemyY, enemy.getIconWidth(), enemy.getIconHeight());
				
				//If the ship collides with enemy
				if (shipBox.intersects(enemyBox))
				{
					//Making JPanel blank and outputting a message while exiting out after
					setVisible(false);
					JOptionPane.showMessageDialog(null, "Sadly, the enemies have crashed into your ship. Thank You for your Support, Commander " + userName + "!");
					System.exit(0);
				}
				
			}
			
			//Timer to move the beams shooting from the ship
			if(e.getSource() == beamTimer)
			{
				//Going into the other class to get x and y coordinates for the beam based on the direction of the ship
				beamX = data.getbeamX(beamX, beamDir);
				beamY = data.getbeamY(beamY, beamDir);
				
				//Creating my bounding boxes for my beams
				beamHorBox = new Rectangle2D.Double(beamX, beamY, beamHor.getIconWidth(), beamHor.getIconHeight());
				beamVerBox = new Rectangle2D.Double(beamX, beamY, beamVer.getIconWidth(), beamVer.getIconHeight());
				
				//If the beam collides with the enemy
				if(beamVerBox.intersects(enemyBox)||beamHorBox.intersects(enemyBox))
				{
					score += 10;
					
					//If the user has completed all four stages and defeated all the enemies
					if(score == 250)
					{
						JOptionPane.showMessageDialog(null, "WE HAVE EMBRACED VICTORY COMMANDER " + userName + "! Thank You for your Support, Commander!");
						System.exit(0);
					}
					
					enemyX = 5000;
					
					//using the countRank to get the necessary level to go to
					if(score == scoreCount[countRank])
					{
						enemyTimer.stop();
						contin = JOptionPane.showConfirmDialog(null, "You Are Entering the " + rank[countRank] + " Stage - Would you like to continue?", "EXIT", JOptionPane.YES_NO_OPTION);
						
						//If the user wants to continue playing
						if(contin == JOptionPane.YES_OPTION)
						{
							countRank++;
							if(countRank == 4)
							{
								countRank = 0;
							}
							
							//To pause for about 1 second to get the user ready
							try
							{
								Thread.sleep(1000);
							}
							catch(InterruptedException f) {}
							
							//Increase the enemy speed by 3 and start the enemy spawner
							speedChange += 3;
							enemyTimer.start();
						}
						
						//If the user doesn't want to continue, output message and exit
						else
						{
							JOptionPane.showMessageDialog(null, "Thank You for your Support, Commander!");
							System.exit(0);
						}
					}
					
					//To start the spawn timer since enemy is destroyed
					enemyTimer.start();
					
					//If the beam is of screen, to stop its movement
					if (isFire == true && beamX - 100 + beam.getIconWidth() >= getWidth() || beamX + 50 < 0 || beamY - 100 + beam.getIconHeight() >= getHeight() || beamY + 50 < 0)
					{
						beamTimer.stop();
					}
				}
				repaint();
			}
		}
		
	//For the actions pressing on button on keyboard
	public void keyPressed(KeyEvent e)
	{
		StarWars_Position data = new StarWars_Position();
		
		// User clicks the left arrow key
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			dir = 180;
			ship = shipLeft;
			
			//Goes to other class to get x position
			shipX = data.getshipX(shipX, dir);
		
		}
				
		//User clicks the right arrow key
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			dir = 0;
			ship = shipRight;
			
			//Goes to other class to get x position
			shipX = data.getshipX(shipX, dir);
			
		}
		
		//User clicks the up arrow key
		else if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			dir = 90;
			ship = shipUp;
	
			//Goes to other class to get y position
			shipY = data.getshipY(shipY, dir);
		}
				
		//User clicks the down arrow key
		else if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			dir = 270;
			ship = shipDown;	
		
			//Goes to other class to get y position
			shipY = data.getshipY(shipY, dir);
		
		}
		
		//User clicks the enter key
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			//This will let the title page disappear and the actual game program bring in the images
			titleCheck = false;
		}
		
		//To get x and y positions when the user clicks spacebar to shoot
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			isFire = true;
			beamDir = dir;
			//If the ship is facing right or left
			if (beamDir == 0 || beamDir == 180)
			{
				beam = beamHor;
			}
			
			//If the ship is facing up or down
			else
			{
				beam = beamVer;
			}
			
			//Creating starting positions
			beamX = shipX + ship.getIconWidth()/2 - 5; 
			beamY = shipY + ship.getIconHeight()/2;
			
			//Starting my beamTimer to make them move 
			beamTimer.start();
		}
		repaint();
	}
	//Necessary method for key events to work
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	
	//Paint method which bring and draw all the images needed in the panel
	public void paint(Graphics g)
	{
		super.paint(g);
		
		//Bringing in graphics
		Graphics2D g2 = (Graphics2D) g;
		
		
		//If boolean is true, bringing in the title page with all the necessary images and strings
		if(titleCheck == true)
		{
			g2.drawImage(title.getImage(), 40, 200, this);
			g2.drawImage(titleimg.getImage(), 250, 350, this);
			g2.setFont(f);
			g2.setColor(Color.YELLOW);
			int instructWidth = fm.stringWidth("PRESS ENTER BUTTON TO CONTINUE");
			g2.drawString("PRESS ENTER BUTTON TO CONTINUE", getWidth()/2 - instructWidth/2, getHeight()/2 + 75);
			 g2.setFont(new Font("Calibri", Font.ITALIC, 20));
			g2.drawString("Use the Arrow Keys to Move and SpaceBar to Shoot", getWidth()/2 - 220, getHeight()/2 + 100);
		}
		
		//If boolean is false, bringing the user into the actual game itself
		else
		{	
			
			//Asking the user for their name, looping it until the user puts the correct input
			while(name == true)
			{
				setVisible(false);
				userName = JOptionPane.showInputDialog(null, "Enter your username:", "STAR WARS: Last One Standing", JOptionPane.QUESTION_MESSAGE);
				
				//If the name isn't within required length and if a character is a letter
				if(userName.length() >= 4 && userName.length() <= 18)
				{
					for (int w = 0; w < userName.length(); w++)
					{
						if (Character.isLetter(userName.charAt(w)))
						{
							name = false;	
						}
						else
						{
							name = true;
							break;
						}
					}
					
					//Checking if the username is within conditions
					if(name == false)
					{
						break;
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Please Enter a Username between 4 - 18 LETTERS!\n\n","STAR WARS: Last One Standing", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				//If not within conditions, always outputting an error message 
				else
				{
					JOptionPane.showMessageDialog(null, "Please Enter a Username between 4 - 18 LETTERS!\n\n","STAR WARS: Last One Standing", JOptionPane.ERROR_MESSAGE);
				}
			}		
			
			//Starting the enemy spawn timer 
			enemyTimer.start();
			setVisible(true);

			
			//Setting the frame size and location on the screen for the game 
			frame.setSize(1200, 800);
			frame.setLocationRelativeTo(null);
			
			//Setting the background to black
			setBackground(Color.BLACK);
			
			//Draw the ship at starting position
			g2.drawImage(ship.getImage(), shipX, shipY, this);
			
			//Draw the username underneath the ship
			g2.setFont(new Font("Calibri", Font.PLAIN, 20));
			g2.setColor(Color.WHITE);
			g2.drawString("Commander  \n" + userName, shipX + shipUp.getIconWidth()/2 - 80, shipY + shipUp.getIconHeight() + 20);
			
			//If the enemy timer runs, draw the enemy
			if(enemyVis == true)
			{
				g2.drawImage(enemy.getImage(), enemyX, enemyY, this);
			}
			
			//If the beam timer runs, draw the beam
			if(isFire == true)
			{
				g2.drawImage(beam.getImage(), beamX, beamY, this);
			}
			
			//Setting fonts and colour for the score displayed in the frame
			g2.setFont(f);
			g2.setColor(Color.YELLOW);
			g2.drawString("SCORE: "+ score, 10, 850);		
		}
	}
}

