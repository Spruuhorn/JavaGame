package manager;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.Set;

import tosco.evanus.GameMap;
import tosco.evanus.GameObject;
import tosco.evanus.Player;

import physics.Collider;

/* Static class to keep track of which map is currently being played and can distribute the gameobjects accordingly */
public class GameManager {
	
	private static GameMap map;
	private static Player player;
	
	public static float elapsedTime;
	
	public static void setCurrentMap(GameMap curr) {
		map = curr;
	}
	
	public static void setPlayer(Player p) {
		player = p;
	}
	
	public static void addNewGameObject(GameObject object) {
		map.addNewGameObject(object);
	}
	
	public static GameMap getCurrentMap() {
		return map;
	}
	
	public static Set<GameObject> getGameObjects() {
		return map.getGameObjects();
	}
	
	public static ArrayList<HashSet<Collider>> getActiveGrid() {
		return map.getCollisionMap().getActiveGrid();
	}
	
	public static Player getPlayer() {
		return player;
	}
}
