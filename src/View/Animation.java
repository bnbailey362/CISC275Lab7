package View;
//T Harvey
//based loosely on http://www.java2s.com/Code/JavaAPI/java.awt/GraphicsdrawImageImageimgintxintyImageObserverob.htm
 
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Model.Action;
import Model.Board;
import Model.Orc;

public class Animation extends JPanel {

    private static final int NUMIMGRUN = 10;		//Sets the values of the number of pics for each action
    private static final int NUMIMGJUMP = 8;
    private static final int NUMIMGSHOOT = 4;
    private static final int NUMIMGDEATH = 7;
	final int frameCount = 10;
    int picNum = 0;
    
    
    BufferedImage[][] runPics;						//BufferedImage Arrays to hold the animations
    BufferedImage[][] shootPics;
    BufferedImage[][] jumpPics;
    BufferedImage[][] deathPics;
    
	static Board board;								
	static ArrayList<Orc> tribe;					//Arraylist of Orcs

    //Paints each orc depending on its action, direction, and location
    public void paint(Graphics g) {
    	for(Orc b : tribe){
    		b.setPicNum((b.getPicNum()+ 1) % Animation.getFrameCount(b.getAction()));
    		if(b.getAction() == Action.RUN){
    			g.drawImage(runPics[b.getDir().getTotalDir()][b.getPicNum()], b.getXLoc(), b.getYLoc(), null, this);
    		}
    		else if(b.getAction() == Action.JUMP){
    			g.drawImage(jumpPics[b.getDir().getTotalDir()][b.getPicNum()], b.getXLoc(), b.getYLoc(), null, this);
    		}
    		else if(b.getAction() == Action.SHOOT){
    			g.drawImage(shootPics[b.getDir().getTotalDir()][b.getPicNum()], b.getXLoc(), b.getYLoc(), null, this);
        	}
    	}
    	super.paintComponents(g);
    }

    public static void main(String[] args) {
    	JFrame frame = new JFrame();				//Create background
    	Animation animation = new Animation();		//Create Animation for Buttons
    	
    	board = new Board();						//Set board
    	tribe = board.getTribe();					//Load the Arraylist of Orcs 
    	
    	frame.setBackground(Color.gray);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	 frame.setSize(board.getFrameWidth(), board.getFrameHeight());
    	frame.setVisible(true);
    	
    	JButton b0 = new JButton("ADD");			//Adds the three buttons
        JButton b1 = new JButton("REMOVE");
        JButton b2 = new JButton("ACTION");
        
        
        b0.addActionListener(new ActionListener() {	//Sets the action for the Add button
            public void actionPerformed(ActionEvent event) {
            	tribe.add(new Orc());
            } 
        });
        b1.addActionListener(new ActionListener() {	//Sets the actions for the Remove button
            public void actionPerformed(ActionEvent event) {
            	tribe.remove(0);
            } 
        });
        b2.addActionListener(new ActionListener() {	//Sets the action for the Action button
            public void actionPerformed(ActionEvent event) {
                for(Orc Gary : tribe){
                	Gary.setAction(false); 
                }
            }
        });
        
        b0.setVisible(true);						//Makes each button visible
        b1.setVisible(true);
        b2.setVisible(true);
        
        animation.add(b0);							//Adds the buttons to the animation
        animation.add(b1);
        animation.add(b2);
        
        
        
        frame.getContentPane().add(animation);		//Adds the animation to the frame
        frame.invalidate();
        
    	while(true){
    		board.update();							//Update board and repaint
    		frame.repaint();
    		try {
    			Thread.sleep(100);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    	}
    }

   

	
    public Animation(){						//Loads the 3 Animations in all directions
    	BufferedImage[] imgs = createRunImage();		
    	runPics = new BufferedImage[imgs.length][NUMIMGRUN];
    	for(int j =0;j<imgs.length;j++){
    	BufferedImage[] tempPic = new BufferedImage[NUMIMGRUN];
    	for(int i = 0; i < frameCount; i++){
    		tempPic[i] = imgs[j].getSubimage(Orc.imgWidth*i, 0, Orc.imgWidth, Orc.imgHeight);
    		runPics[j] = tempPic;
    	}
    	}
    	BufferedImage[] imgs2 = createJumpImage();
    	jumpPics = new BufferedImage[imgs2.length][NUMIMGJUMP];
    	for(int j =0;j<imgs2.length;j++){
    		BufferedImage[] tempPic = new BufferedImage[NUMIMGJUMP];
    		for(int i = 0; i < 8; i++){
    			tempPic[i] = imgs2[j].getSubimage(Orc.imgWidth*i, 0, Orc.imgWidth, Orc.imgHeight);
    			jumpPics[j] = tempPic;
    		}
    	}
    	BufferedImage[] imgs3 = createShootImage();
    	shootPics = new BufferedImage[imgs3.length][NUMIMGSHOOT];
    	for(int j =0;j<imgs3.length;j++){
    		BufferedImage[] tempPic = new BufferedImage[NUMIMGSHOOT];
    		for(int i = 0; i < 4; i++){
    			tempPic[i] = imgs3[j].getSubimage(Orc.imgWidth*i, 0, Orc.imgWidth, Orc.imgHeight);
    			shootPics[j] = tempPic;
    		}
    	}
    }  
    
    //Read image from file and return
    private BufferedImage[] createRunImage(){
    	int numDirections = 8;
    	String[] imagenames = {"orc_forward_north", "orc_forward_northeast", "orc_forward_east", "orc_forward_southeast", "orc_forward_south", "orc_forward_southwest", "orc_forward_west", "orc_forward_northwest"};
    	BufferedImage[] bufferedImages = new BufferedImage[numDirections];
    	try {
    		for(int i =0;i<imagenames.length;i++){
    		bufferedImages[i] = ImageIO.read(new File("images/orc/" + imagenames[i] + ".png"));
    	}}
    		catch (IOException e) {
    		e.printStackTrace();
    	}
    	return bufferedImages;
    }
  //Read image from file and return
    private BufferedImage[] createJumpImage(){
    	int numDirections = 8;
    	String[] imagenames = {"orc_jump_north", "orc_jump_northeast", "orc_jump_east", "orc_jump_southeast", "orc_jump_south", "orc_jump_southwest", "orc_jump_west", "orc_jump_northwest"};
    	BufferedImage[] bufferedImages = new BufferedImage[numDirections];
    	try {
    		for(int i =0;i<imagenames.length;i++){
    		bufferedImages[i] = ImageIO.read(new File("images/orc/" + imagenames[i] + ".png"));
    	}}
    		catch (IOException e) {
    		e.printStackTrace();
    	}
    	return bufferedImages;
    }
  //Read image from file and return
    private BufferedImage[] createShootImage(){
    	int numDirections = 8;
    	String[] imagenames = {"orc_fire_north", "orc_fire_northeast", "orc_fire_east", "orc_fire_southeast", "orc_fire_south", "orc_fire_southwest", "orc_fire_west", "orc_fire_northwest"};
    	BufferedImage[] bufferedImages = new BufferedImage[numDirections];
    	try {
    		for(int i =0;i<imagenames.length;i++){
    		bufferedImages[i] = ImageIO.read(new File("images/orc/" + imagenames[i] + ".png"));
    	}}
    		catch (IOException e) {
    		e.printStackTrace();
    	}
    	return bufferedImages;

    }

	public static int getFrameCount(Action action) {
		if(action == Action.RUN){
			return NUMIMGRUN;
		}
		if(action == Action.JUMP){
			return NUMIMGJUMP;
		}
		if(action == Action.SHOOT){
			return NUMIMGSHOOT;
		}
		else return NUMIMGDEATH;
	}
}