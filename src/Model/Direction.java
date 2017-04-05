package Model;

import java.util.Random;

public class Direction {
	
	private int xdir;// Uses 1 or -1 to decide Left (-1) or Right(1)
	private int ydir;//Uses 1 or -1 to decide Up(-1) or Down(1)
	private int totaldir;//Holds the Index of the total direction from the array of directions in Animation
	
	public Direction(int xdir, int ydir) {
		this.xdir = xdir;
		this.ydir = ydir;
		if(xdir == 1 && ydir==-1){	//Sets totaldir
			this.totaldir = 1;
		}
		else if(xdir == 1 && ydir == 1){
			this.totaldir = 3;
		}
		else if(xdir == -1 && ydir == 1){
			this.totaldir = 5;
		}
		else if(xdir == -1 && ydir == -1){
			this.totaldir = 7;
		}
	}
	
	public int getXdir() {
		return xdir;
	}
	public int getYdir() {
		return ydir;
	}
	public int getTotalDir(){
		return totaldir;
	}
	
	
}
