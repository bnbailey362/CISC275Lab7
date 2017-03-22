//T Harvey
//based loosely on http://www.java2s.com/Code/JavaAPI/java.awt/GraphicsdrawImageImageimgintxintyImageObserverob.htm
 
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Animation extends JPanel {

    final int frameCount = 10;
    int picNum = 0;
    BufferedImage[][] pics;
    static int dir;
    static boolean justChanged = false;
    static int ydir = 1;
    static int xdir = 1;
    static int xloc = 0;
    static int yloc = 0;
    final int xIncr = 8;
    final int yIncr = 2;
    final static int frameWidth = 500;
    final static int frameHeight = 300;
    final static int imgWidth = 165;
    final static int imgHeight = 165;

    //Override this JPanel's paint method to cycle through picture array and draw images
    public void paint(Graphics g) {
    	picNum = (picNum + 1) % frameCount;
    	g.drawImage(pics[dir][picNum], xloc+=(xdir*xIncr), yloc+=(ydir*yIncr), Color.gray, this);
    	
    	// TODO: Keep the orc from walking off-screen, turn around when bouncing off walls.
		//Be sure that animation picture direction matches what is happening on screen.
    }

    //Make frame, loop on repaint and wait
    public static void main(String[] args) {
    	JFrame frame = new JFrame();
    	frame.getContentPane().add(new Animation());
    	frame.setBackground(Color.gray);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(frameWidth, frameHeight);
    	frame.setVisible(true);
    	for(int i = 0; i < 1000; i++){
    		update();
    		dir = updateDir();
    		frame.repaint();
    		try {
    			Thread.sleep(100);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    	}
    }

    private static int updateDir() {
		if(xdir==1&&ydir==1){
			return 3;
		}
		else if(xdir==-1&&ydir==1){
			return 5;
		}
		else if(xdir==1&&ydir==-1){
			return 1;
		}
		else if(xdir==-1&&ydir==-1){
			return 7;
		}
		return dir;
		
	}

	private static void update() {
    	if (!justChanged){
		if (contactVertWalls()){
			xdir = -xdir;
			justChanged=true;
		}
		if (contactHorzWalls()){
			ydir = -ydir;
			justChanged=true;
		}
    	}
    	else{justChanged = false;}
		
	}

	private static boolean contactHorzWalls() {
		return yloc<0 || yloc>=(frameHeight-imgHeight);
	}

	private static boolean contactVertWalls() {
		return xloc<0 || xloc>=(frameWidth-imgWidth);
	}

	//Constructor: get image, segment and store in array
    public Animation(){
    	BufferedImage[] imgs = createImage();
    	pics = new BufferedImage[imgs.length][10];
    	for(int j =0;j<imgs.length;j++){
    	BufferedImage[] tempPic = new BufferedImage[10];
    	for(int i = 0; i < frameCount; i++){
    		tempPic[i] = imgs[j].getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    		pics[j] = tempPic;
    	}
    	}
    	// TODO: Change this constructor so that at least eight orc animation pngs are loaded
    }  
    
    //Read image from file and return
    private BufferedImage[] createImage(){
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
    	
    	// TODO: Change this method so you can load other orc animation bitmaps
    }
}