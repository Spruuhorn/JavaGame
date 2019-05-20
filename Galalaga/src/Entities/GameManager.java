package Entities;

import java.util.ArrayList;
import java.util.Random;

import GameStates.GalalagaLauncher;

public class GameManager {
	
	public static final int GAME_WIDTH = GalalagaLauncher.width;
	public static final int GAME_HEIGHT = GalalagaLauncher.height;
	
	private static ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	
	private static int timer = 0;
	
	private static int masterTimer = 0;
	
	private static int wait = 750;
	
	private static int timeToNextLevel = 1;
	
	private static int numPerSpawnTick = 1;
	
	private static int level = 0;
	
	private static int maxNumOfAliens = 10;
	
	private static double basicSpawnChance = 1.0, slidingSpawnChance = 0, trackingSpawnChance = 0.0, mirageSpawnChance = 0.0;
	
	public static ArrayList<GameObject> getGameObjects() {
		return gameObjects;
	}
	
	public static void update(int time) {
		
		int numOfAliens = 0;
		
		masterTimer += time;
		
		timer += time;
		
		for(GameObject obj : gameObjects) {
			if(obj.getCollider().getTag().equals("Alien")) {
				numOfAliens++;
			}
		}
		
		if(masterTimer % (1000 * 60 * timeToNextLevel) == 0) {
			setLevels(++level);
		}
		
		if(timer % wait == 0 && numOfAliens < maxNumOfAliens) {
			createAliens();
		}
		
	}
	
	public static void setLevels(int level) {
		switch(level) {
		case 1:
			maxNumOfAliens = 15;
			timeToNextLevel = 2;
			basicSpawnChance = 0.75;
			slidingSpawnChance = 0.25;
			break;
			
		case 3: 
			maxNumOfAliens = 20;
			wait = 1700;
			timeToNextLevel = 3;
			basicSpawnChance = 0.6;
			slidingSpawnChance = 0.3;
			trackingSpawnChance = 0.2;
			break;
			
		case 5: 
			maxNumOfAliens = 25;
			wait = 1400;
			numPerSpawnTick = 2;
			basicSpawnChance = 0.5;
			slidingSpawnChance = 0.4;
			trackingSpawnChance = 0.3;
			mirageSpawnChance = 0.25;
			break;
			
		case 8: 
			maxNumOfAliens = 30;
			wait = 1200;
			basicSpawnChance = 0.5;
			slidingSpawnChance = 0.4;
			trackingSpawnChance = 0.3;
			mirageSpawnChance = 0.25;
			break;
			
		case 10: 
			maxNumOfAliens = 35;
			wait = 1000;
			numPerSpawnTick = 3;
			basicSpawnChance = 0.15;
			slidingSpawnChance = 0.6;
			trackingSpawnChance = 0.5;
			mirageSpawnChance = 0.33;
			break;
		}
	}
	
	//BASIC ALIENS ARE SPAWNING OUT OF BOUNDS - STUCK
	public static void createAliens() {
		
		for(int i = 0; i < numPerSpawnTick; i++) {
		
			double random = Math.random();
			
			if(random < mirageSpawnChance) {
				
				new MirageAlien((int)((GAME_WIDTH - (40 * 2)) * Math.random() + 40), 0, 5, .25f, "MirageAlien.png");
			
			} else if (random < trackingSpawnChance) {
				
				new TrackingAlien((int)((GAME_WIDTH - (40 * 2)) * Math.random() + 40), 0, 3, .25f, "LongAlien.png");
				
			} else if (random < slidingSpawnChance) {
				
				new SlidingAlien((int)((GAME_WIDTH - (40 * 2)) * Math.random() + 40), (int)((GAME_HEIGHT + (100 * 2)) * Math.random() - 500), 4, .25f, "SlidingAlien.png");
				
			} else if (random < basicSpawnChance){
				
				new BasicAlien((int)(GAME_WIDTH * Math.random()), 0, 2, .25f, "Alien1.png");
				
			}
			
		}
	}

	public static void reset() {
		
		timer = 0;
		
		masterTimer = 0;
		
		wait = 750;
		
		timeToNextLevel = 1;
		
		numPerSpawnTick = 1;
		
		level = 0;
		
		maxNumOfAliens = 10;
		
		basicSpawnChance = 1.0;
		slidingSpawnChance = 0;
		trackingSpawnChance = 0.0;
		mirageSpawnChance = 0.0;
		
		gameObjects.removeAll(gameObjects);
		
	}
	
	
}
