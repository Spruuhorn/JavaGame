import java.awt.event.KeyEvent;

public class Charge {
	
	private int x;
	private int y;
	private int radius;
	private int Q;
	private int dx;
	private int dy;
	
	Charge(int xx, int yy, int rr, int qq) {
		x = xx;
		y = yy;
		radius = rr;
		Q = qq;
	}
	
	public int getX() {
    	return x;
    }
    
    public int getY() {
    	return y;
    }
    
    public int getR() {
    	return radius;
    }
    
    public int getQ() {
    	return Q;
    }

}
