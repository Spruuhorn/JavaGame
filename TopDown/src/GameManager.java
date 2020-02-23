import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;

public class GameManager {
	
	public final static long startNanoTime = System.nanoTime();
	
	public static boolean drawHitBoxes = false;
	
	public static List<GameObject> gameObjects;
	
	public static GameObject player;
	
	public static void init() {
		
		//THE ENTIRE LIST OF GAME OBJECTS EVER
		gameObjects = new ArrayList<GameObject>();
		
		Map newMap = new Map(100, 100, 2500);
		
		//GameObject tree = new GameObject(100, 200, -45, 120, 80, 30, "Pine(nobg)");
		
		player = new Player(newMap.getStartX(), newMap.getStartY(), -24, 16, 48, 16, "file:Player/", "PlayerIdleDown", 8);
		
		AnimationTimer timer = new AnimationTimer() {
			
	        public void handle(long currentNanoTime) {
	        	
	            double t = (currentNanoTime - GameManager.startNanoTime) / 1000000000.0; 
		
	            Camera.update(t);
	            
	            player.update();
	            
	        }
	    };       
	     
	    timer.start();
	}
	
}
