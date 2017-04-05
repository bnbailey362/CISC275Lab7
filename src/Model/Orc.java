package Model;

import java.util.Random;

public class Orc {
	Direction orcDirection;		
	Action action;				
	int picNum;
    int xloc;					//X and Y Coord on the Frame
    int yloc;
    int xIncr = 8;				//X speed, positive is right, negative is left
    int yIncr = 2;				//Y speed, positive is down, negative is up
    static boolean justChanged = false;	//Checks to see if the orcs direction was just changed from hitting a wall
    public final static int imgWidth = 165;
    public final static int imgHeight = 165;
    Random rand = new Random();
    
    public Orc(){
    	int dirx = rand.nextInt(2);		//Sets the Orcs random direction
    	int diry = rand.nextInt(2);
    	if(dirx == 1){
    		if(diry == 1){
    			orcDirection = new Direction(1,1);
    		}
    		else{
    			orcDirection = new Direction(1,-1);
    		}
    	}
    	else{
    		if(diry == 1){
    			orcDirection = new Direction(-1,1);
    		}
    		else{
    			orcDirection = new Direction(-1,-1);
    		}
    	}
    	this.action = Action.RUN;		//Sets the Orc to default to Run
    	picNum = 0;						
    	this.xloc = rand.nextInt(Board.frameWidth - imgWidth);//Set the Orc at a random x and y coord
    	this.yloc = rand.nextInt(Board.frameHeight - imgHeight);    	
    	justChanged = false;
    }
    
 
    
    public int getImgWidth(){
    	return imgWidth;
    }
    public int getImgHeight(){
    	return imgHeight;
    }
    public Direction getDir(){
    	return orcDirection;
    }
    //
    public Action getAction(){
    	return action;
    }
    public void setAction(Boolean death){	//Changes the Orc to the next action
    	if(death == false){
	    	if(action == Action.RUN){
	    		action = Action.JUMP;
	    	}
	    	else if(action == Action.JUMP){
	    		action = Action.SHOOT;
	    		xIncr = 0;
	    		yIncr = 0;
	    	}
	    	else if(action == Action.SHOOT){
	    		action = Action.RUN;
	    		xIncr = 8;
	    		yIncr = 2;
	    	}
    	}
    	else{
    		action = Action.DEATH;
    		xIncr = 0;
    		yIncr = 0;
    	}
    }
    //
    public int getPicNum(){
    	return picNum;
    }
    //
    public int getXLoc(){
    	return xloc;
    }
    public int getYLoc(){
    	return yloc;
    }
    public void setXLoc(int newX){
    	xloc = newX;
    }
    public void setYLoc(int newY){
    	yloc = newY;
    }
    //
    public int getxIncr(){
    	return xIncr;
    }
    public int getyIncr(){
    	return yIncr;
    }
    //
    public boolean getJustChanged(){
    	return justChanged;
    }
    public void setJustChanged(boolean change){
    	justChanged = change;
    }

	public int getYdir() {
		return orcDirection.getYdir();
	}



	public int getXdir() {
		return orcDirection.getXdir();
	}

	public int getTotalDir(){
		return orcDirection.getTotalDir();
	}

	public void setXdir(int i) {				//After contact with a wall, changes the direction by swapping the x and y directions
		orcDirection = new Direction(i,orcDirection.getYdir());
	}
	
	public void setYdir(int i) {				//After contact with a wall, changes the direction by swapping the x and y directions
		orcDirection = new Direction(orcDirection.getXdir(),i);
	}



	public void setPicNum(int i) {
		picNum = i;
		
	}


}
