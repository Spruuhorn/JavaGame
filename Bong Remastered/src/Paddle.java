
import java.awt.event.KeyEvent;

public class Paddle extends Sprite {

    private int dy;

    public Paddle(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void move() {
    	
        y += dy;
     }

    void keyPressed(KeyEvent e) {

        switch(e.getKeyCode()) {

	        case KeyEvent.VK_UP: 
	        	dy = -5;
	        break;
	        
	        case KeyEvent.VK_DOWN: 
	        	dy = 5;
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
    	}
    }
}
