package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class View extends JPanel {
    BufferedImage[][] pics;
    BufferedImage[][] shoot;
    BufferedImage[][] jump;
    BufferedImage[][] die;
    static boolean justChanged = false;
    final static int frameWidth = 500;
    final static int frameHeight = 300;
    final static int imgWidth = 165;
    final static int imgHeight = 165;
	
    public void Button(){
    	
    }
    public void initUI(){
    	JButton b0 = new JButton("ADD");
		JButton b1 = new JButton("REMOVE");
		JButton b2 = new JButton("ACTION");
		b0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//add orc to list
			} 
		});
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//remove orc from list
			} 
		});
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//change orc action for each orc
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
    	//accesses orc list here, gets orcs
		//List orclist = List;
		for(int i=0;i<2;i++){//for each orc in list
			int orcAction = 0;
    		int orcx=1;
    		int orcy=1;
    		int picNum=0;
    		int dir=0;
    		//function action, x, t, picnum, and dir
    		if(orcAction == 0){
    			picNum = picNum % 10;
    			g.drawImage(pics[dir][picNum], orcx, orcy, Color.gray, this);
    		}
    		if(orcAction == 1){
    			picNum = picNum % 8;
        		g.drawImage(jump[dir][picNum], orcx, orcy, Color.gray, this);
    		}
    		if(orcAction == 2){
    			picNum = picNum % 4;
        		g.drawImage(shoot[dir][picNum], orcx, orcy, Color.gray, this);
        	}
		}
	}
}
