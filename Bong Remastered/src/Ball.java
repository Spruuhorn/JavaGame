
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Ball extends Sprite {

	int dx;
    int dy;
    int score1;
    int score2;

    public Ball(int x, int y, int radius) {
        super(x, y, radius, radius);
        dx = 5;
        dy = 2;
        
        score1 = 0;
        score2 = 0;
    }

    public void move() {
    	
    	if(y < 0) {
    		dy *= -1;
    	} else if (y - 15 > 450) {
    		dy *= -1;
    	}
    	
    	if(x < 0) {
    		score2++;
    		x = 500;
    		y = 250;
    	} else if (x > 1000) {
    		score1++;
    		x = 500;
    		y = 250;
    	}
    	
    	x += dx;
        y += dy;
     }
    
    public void hit() {
    	dx *= -1;
    }
    
    String score() {
    	return (score1 + "     " + score2);
    }
}
