package View;

import java.awt.Color;import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import Model.Action;
import Model.Board;		//IDK if this is supposed to be here, but otherwise, why have a Board class at all?
import Model.Direction;
import Model.Orc;

public class View extends JPanel {
    BufferedImage[][] pics;
    BufferedImage[][] shoot;
    BufferedImage[][] jump;
    BufferedImage[][] die;
    ArrayList<Orc> Tribe;
	
    public void Button(){
    	
    }
    public void initUI(){
    	JButton b0 = new JButton("ADD");
		JButton b1 = new JButton("REMOVE");
		JButton b2 = new JButton("ACTION");
		b0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Tribe.add(new Orc());
			} 
		});
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Tribe.remove(0);
			} 
		});
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				for(Orc gary : Tribe){
					gary.setAction(Action.RUN);		//Doesn't Actually Change it to run, in place so we can kill orcs.
				}
			}
		});
    }
    
    
    private BufferedImage[] createImageRun(){
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
    private BufferedImage[] createImageJump(){
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
    private BufferedImage[] createImageShoot(){
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
    
	public View(){
		int imgWidth = Tribe.get(0).getImgWidth(); 
		int imgHeight = Tribe.get(0).getImgHeight();
		int frameWidth; //Call getFrameWidth on Board
		int frameHeight; //call getFrameHeight on Board
    	BufferedImage[] imgs1 = createImageRun();
    	pics = new BufferedImage[imgs1.length][10];
    	for(int j =0;j<imgs1.length;j++){
    		BufferedImage[] tempPic = new BufferedImage[10];
    		for(int i = 0; i < 10; i++){
    			tempPic[i] = imgs1[j].getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    			pics[j] = tempPic;
    		}
    	}
    	BufferedImage[] imgs2 = createImageJump();
    	jump = new BufferedImage[imgs2.length][8];
    	for(int j =0;j<imgs2.length;j++){
    		BufferedImage[] tempPic = new BufferedImage[8];
    		for(int i = 0; i < 8; i++){
    			tempPic[i] = imgs2[j].getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    			jump[j] = tempPic;
    		}
    	}
    	BufferedImage[] imgs3 = createImageShoot();
    	shoot = new BufferedImage[imgs3.length][4];
    	for(int j =0;j<imgs3.length;j++){
    		BufferedImage[] tempPic = new BufferedImage[4];
    		for(int i = 0; i < 4; i++){
    			tempPic[i] = imgs3[j].getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    			shoot[j] = tempPic;
    		}
    	}
    }
	public void paint(Graphics g) {
		//JButton b0 = new JButton("ADD");
		//g.drawImage(b0,5,5,Color.gray, this);

		for(Orc Steve : Tribe){//for each orc in list
			Action orcAction = Steve.getAction();
    		int orcx = Steve.getXLoc();
    		int orcy = Steve.getYLoc();
    		int picNum = Steve.getPicNum();
    		int dir = Steve.getDir().getTotalDir();
    		if(orcAction == Action.RUN){
    			picNum = picNum % 10;
    			g.drawImage(pics[dir][picNum], orcx, orcy, Color.gray, this);
    		}
    		else if(orcAction == Action.JUMP){
    			picNum = picNum % 8;
        		g.drawImage(jump[dir][picNum], orcx, orcy, Color.gray, this);
    		}
    		else if(orcAction == Action.SHOOT){
    			picNum = picNum % 4;
        		g.drawImage(shoot[dir][picNum], orcx, orcy, Color.gray, this);
        	}
		}
	}
}
