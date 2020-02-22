package physics;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.tosco.toe.GameObject;

/* Represents a partition of the game map to divide physics collision calculations */
public class CollisionMap {
	
	/* 1D for small optimiazation */
	// Turn to this if ArrayList turns out to be too slow
	//HashSet<?>[] rows = new HashSet<?>[9];
	private ArrayList<HashSet<Collider>> grid;
	private ArrayList<HashSet<Collider>> activeGrid;
	private int rows, cols;
	
	private final int SIZE = 500;
	
	/* @objects - objects in the game map */
	public CollisionMap(int cols, int rows, Set<GameObject> objects) {
		this.rows = rows;
		this.cols = cols;
		grid = new ArrayList<HashSet<Collider>>();
		activeGrid = new ArrayList<HashSet<Collider>>();
		
		for(GameObject object : objects) {
			
		}
	}
	
	public void update() {
		
	}
	
	public ArrayList<HashSet<Collider>> getActiveGrid() {
		return activeGrid;
	}
	
	public HashSet<Collider> getGridCell(int row, int col) {
		int index = row + (col * rows);
		return grid.get(index);
	}
	
}
