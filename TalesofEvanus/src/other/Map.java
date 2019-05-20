package other;

import java.util.ArrayList;

import parents.GameObject;

public class Map {
	
	private String tag;
	private Vector2 size;
	private ArrayList<GameObject> tiles;
	
	public Map(int width, int height, ArrayList<GameObject> tiles) {
		this.size = new Vector2 (width, height);
		this.tiles = tiles;
	}
	
	public ArrayList<GameObject> getActiveTiles() {
		return tiles;
	}
	
	public String getTag() {
		return tag;
	}
	
	public float getWidth() {
		return size.x * 64;
	}
	
	public float getHeight() {
		return size.y * 64;
	}
	
}
