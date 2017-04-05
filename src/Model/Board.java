package Model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import View.Animation;

public class Board {
	final static int frameWidth = 500;
    final static int frameHeight = 300;
    ArrayList<Orc> tribe;
    
    public Board(){
    	tribe = new ArrayList<Orc>();
    }
    
    public void update(){
    	for(Orc b : tribe){
    		if (!b.getJustChanged()){
    			if (contactVertWalls(b)&&(!(b.getAction()==Action.SHOOT))){
    				b.setXdir(-b.getXdir());
    				b.setJustChanged(true);
    			}
    			if (contactHorzWalls(b)&&(!(b.getAction()==Action.SHOOT))){
    				b.setYdir(-b.getYdir());
    				b.setJustChanged(true);
    			}
    	    	}
    	    	else{b.setJustChanged(false);}
    		b.setXLoc(b.getXLoc() + (b.getXdir()*b.getxIncr()));
    		b.setYLoc(b.getYLoc() + (b.getYdir()*b.getyIncr()));
    		
    	}
    }
    
    private static boolean contactHorzWalls(Orc b) {
		return b.getYLoc()<0 || b.getYLoc()>=(frameHeight-b.getImgHeight());
	}

	private static boolean contactVertWalls(Orc b) {
		return b.getXLoc()<0 || b.getXLoc()>=(frameWidth-b.getImgWidth());
	}
	
    public int getFrameWidth(){
    	return frameWidth;
    }
    public int getFrameHeight(){
    	return frameHeight;
    }

	public ArrayList<Orc> getTribe() {
		return tribe;
	}
}
