package Model;

import java.util.Random;

public enum Direction {
	NORTHEAST(1,-1), SOUTHEAST(1,1), SOUTHWEST(-1,1), NORTHWEST(-1,-1);
	
	private int xdir;
	private int ydir;
	private int totaldir;
	
	private Direction(int xdir, int ydir) {
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
	
	private static final Direction[] VALUES = values();
	private static final int SIZE = VALUES.length;
	private static final Random RANDOM = new Random();
	public static Direction getRandomDirection()  {
		return VALUES[RANDOM.nextInt(SIZE)];
	}
}
