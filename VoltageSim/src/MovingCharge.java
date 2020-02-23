import java.awt.event.KeyEvent;

public class MovingCharge {
	

	private int dy = 0;
	private int dx = 0;
	private int radius = 25;
	private int Q = -1;
	private int x = 100;
	private int y = 250;
	
	public void move() {
    	
		
        y += dy;
        x += dx;
        
     }

    void keyPressed(KeyEvent e) {

        switch(e.getKeyCode()) {

	        case KeyEvent.VK_UP: 
	        	dy = -5;
	        break;
	        
	        case KeyEvent.VK_DOWN: 
	        	dy = 5;
	        break;
	        
	        case KeyEvent.VK_LEFT: 
	        	dx = -5;
	        break;
	        
	        case KeyEvent.VK_RIGHT: 
	        	dx = 5;
	        break;
        }
    }
    public void keyReleased(KeyEvent e) {

    	switch(e.getKeyCode()) {

	        case KeyEvent.VK_UP: 
	        	dy = 0;
	        break;
	        
	        case KeyEvent.VK_DOWN: 
	        	dy = 0;
	        break;
	        
	        case KeyEvent.VK_LEFT: 
	        	dx = 0;
	        break;
	        
	        case KeyEvent.VK_RIGHT: 
	        	dx = 0;
	        break;
    	}
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
