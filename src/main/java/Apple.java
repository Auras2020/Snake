import java.awt.Color;
import java.awt.Graphics;

public class Apple {
	 private int xCoor, yCoor, width, height;
	    
	    public Apple(int xCoor, int yCoor, int tileSize) {
	        this.xCoor = xCoor;
	        this.yCoor = yCoor;
	        width = tileSize;
	        height = tileSize;
	    }
	   
	    private final static int nr_ticks=500000;
	    
	    public void draw(Graphics g, int size) {
	        g.setColor(Color.RED);
	        final int nr=11-(nr_ticks/100000);
	    	g.drawString("LEVEL: " + nr, 430, 20);
	    	g.drawString("SCORE: " + (size-5)*10, 10, 20);
	        g.fillRect(xCoor * width , yCoor * height, width, height);
	    }
	    
	    public int getxCoor() {
	        return xCoor;
	    }
	    public void setxCoor(int xCoor) {
	        this.xCoor = xCoor;
	    }
	    public int getyCoor() {
	        return yCoor;
	    }
	    public void setyCoor(int yCoor) {
	        this.yCoor = yCoor;
	    }

		public static int getNrTicks() {
			return nr_ticks;
		}
	    
	    
}
