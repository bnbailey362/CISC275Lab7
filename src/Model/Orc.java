package Model;

import java.util.Random;

public class Orc {
	int dir;
	int action;
	int picNum;
    int ydir; //1 is North, -1 is South
    int xdir; //1 is East, -1 is West
    int xloc;
    int yloc;
    final static int xIncr = 8;
    final static int yIncr = 2;
    boolean justChanged;
    final static int imgWidth = 165;
    final static int imgHeight = 165;
    Random rand = new Random();
    
    public Orc(){
    	this.dir = rand.nextInt(4); //Random starting direction
    	this.action = 0;
    	picNum = 0;
    	if(this.dir == 0){
    		this.xdir = 1;
    		this.ydir = -1;
    	}
    	else if(this.dir == 1){
    		this.xdir = 1;
    		this.ydir = 1;
    	}
    	else if(this.dir == 2){
    		this.xdir = -1;
    		this.ydir = 1;
    	}
    	else if(this.dir == 3){
    		this.xdir = -1;
    		this.ydir = -1;
    	}
    	this.xloc = rand.nextInt(Board.frameWidth - imgWidth);
    	this.yloc = rand.nextInt(Board.frameHeight - imgHeight);    	
    	justChanged = false;
    }
    public int getDir(){
    	return dir;
    }
    public void setDir() { //Jason's Direction system only allows 4 directions, so lets take out the other directions from the array
		if(xdir == 1 && ydir == 1){
			dir = 1;
		}
		else if(xdir == -1 && ydir == 1){
			dir = 2;
		}
		else if(xdir == 1 && ydir == -1){
			dir = 0;
		}
		else if(xdir == -1 && ydir == -1){
			dir = 3;
		}
	}
    //
    public int getAction(){
    	return action;
    }
    public void setAction(){
    	action = (action + 1) % 3;
    }
    //
    public int getPicNum(){
    	return picNum;
    }
    public void setPicNum(){
    	picNum = (picNum+1)%10; //Need to update with correct frame count from action in view
    }
    //
    public int getYDir(){
    	return ydir;
    }
    public int getXDir(){
    	return xdir;
    }
    public void setXDir(int newDir){
    	if(newDir == 1 || newDir == -1){xdir = newDir;}
    }
    public void setyDir(int newDir){
    	if(newDir == 1 || newDir == -1){ydir = newDir;}
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
