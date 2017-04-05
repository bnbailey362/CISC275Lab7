package Model;

import java.util.Random;

public class Direction {
	
	private int xdir;
	private int ydir;
	private int totaldir;
	
	public Direction(int xdir, int ydir) {
		this.xdir = xdir;
		this.ydir = ydir;
		if(xdir == 1 && ydir==-1){
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
