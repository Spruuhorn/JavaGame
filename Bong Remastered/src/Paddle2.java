
import java.awt.event.KeyEvent;

public class Paddle2 extends Sprite {

    private int dy;

    public Paddle2(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void move() {
    	
        y += dy;
     }

    void keyPressed(KeyEvent e) {

        switch(e.getKeyCode()) {

	        case KeyEvent.VK_W: 
	        	dy = -5;
	        break;
	        
	        case KeyEvent.VK_S: 
	        	dy = 5;
	        break;
        }
    }
    public void keyReleased(KeyEvent e) {

    	switch(e.getKeyCode()) {

	        case KeyEvent.VK_W: 
	        	dy = 0;
	        break;
	        
	        case KeyEvent.VK_S: 
	        	dy = 0;
	        break;
    	}
    }
}
