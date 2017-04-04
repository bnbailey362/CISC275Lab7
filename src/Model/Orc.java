package Model;

import java.util.Random;

public class Orc {
	Direction orcDirection;
	Action action;
	int picNum;
    int xloc;
    int yloc;
    int xIncr = 8;
    int yIncr = 2;
    boolean justChanged;
    final static int imgWidth = 165;
    final static int imgHeight = 165;
    Random rand = new Random();
    
    public Orc(){
    	orcDirection = Direction.getRandomDirection();
    	this.action = Action.RUN;
    	picNum = 0;
    	this.xloc = rand.nextInt(Board.frameWidth - imgWidth);
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
    public void setAction(Action death){
    	if(death != Action.DEATH){
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
    	return picNum++;
    }
    //
    public int getXLoc(){
    	return xloc;
    }
    public int getYLoc(){
    	return yloc;
    }
    public void setXLoc(int xChange){
    	xloc += xChange;
    }
    public void setYLoc(int yChange){
    	yloc += yChange;
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
    	this.justChanged = change;
    }
}
