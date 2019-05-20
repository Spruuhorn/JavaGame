
import java.awt.event.KeyEvent;

public class MenuControl {
	
	private boolean ingame = false;
    private String[] menu = {"main", "play", "about"};
    private int select = 0;
    private String selected = menu[select];
    
    private int y = 185;

    public MenuControl() {
    	
    }

    public void move() {
    	getY();
    }
    
    String getSelect() {
    	return selected;
    }
    
    void setSelect(int s) {
    	selected = menu[s];
    }
    
    boolean getIngame() {
    	return ingame;
    }
    
    int getY() {
    	return y;
    }

    void keyPressed(KeyEvent e) {

        switch(e.getKeyCode()) {
        
        case KeyEvent.VK_UP: 
        	select--;
        	y -= 45;
        	if(select < 0) {
        		select = 0;
        		y = 185;
        	}
        break;
        
        case KeyEvent.VK_DOWN: 
        	select++;
        	y += 45;
        	if(select > 2) {
        		select = 2;
        		y = 230;
        	}
        break;
        
        case KeyEvent.VK_ENTER: 
        	ingame = true;
        	;
        break;
        }
    }
    public void keyReleased(KeyEvent e) {

    	switch(e.getKeyCode()) {

    	}
    }
}
