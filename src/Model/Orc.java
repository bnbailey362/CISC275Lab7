package Model;

import java.util.*;

public class Orc {
	static int dir;
	static int action;
	int picNum = 0;
    static int ydir; //1 is North, -1 is South
    static int xdir; //1 is East, -1 is West
    static int xloc;
    static int yloc;
    final int xIncr = 8;
    final int yIncr = 2;
    static boolean justChanged = false;
    final static int imgWidth = 165;
    final static int imgHeight = 165;
    Random rand = new Random();
    
    public Orc(){
    	
    }
}
