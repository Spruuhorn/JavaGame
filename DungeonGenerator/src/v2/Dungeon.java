package v2;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Dungeon {
	
	public static final String OPEN = "[O]";
	public static final String WALL = "[W]";
	
	private Set<Room> rooms;
	private String[][] vertices;
	private int[] centers;
	
	private int width, height;
	
	public Dungeon(int width, int height, int roomCount) {
		
		this.width = width;
		this.height = height;
		
		rooms = new HashSet<>();
		vertices = new String[width][height];
		
		for(int y = 0; y < vertices[0].length; y++) {
			for(int x = 0; x < vertices.length; x++) {
				vertices[x][y] = "[ ]";
			}
		}
		
		for(int i = 0; i < roomCount; i++) {
		
			int x, y, w, h;
			
			Random r = new Random();
			
			int failCounter = 0;
			int centerCounter = 0;
			
			while(failCounter < 50) {
				
				w = r.nextInt(2) + 4;
				h = r.nextInt(2) + 4;
				x = r.nextInt(width - 2) + 1;
				y = r.nextInt(height - 2) + 1;
				
				if(roomFits(x, y, w, h)) {
					
					Room room = new Room(this, x, y, w, h);
					rooms.add(room);
					
					centerCounter++;
					centers[centerCounter] = x;
					centers[centerCounter] = y;
					
					break;
				}
				failCounter++;
			}
		}
		
		for(Room room : rooms) {
			
		}
		
		/*
		for(int y = 1; y < vertices[0].length - 1; y++) {
			for(int x = 1; x < vertices.length - 1; x++) {
				if(hasWall(x, y)) {
					vertices[x][y] = WALL;
				}
			}
		}
		*/
		
		for(Room room : rooms) {
			System.out.println(room);
		}
	}
	
	public void setTile(int x, int y, String tile) {
		vertices[x][y] = tile;
	}
	
	private boolean roomFits(int x, int y, int width, int height) {
		
		if(x + width >= this.width - 1 || y + height >= this.height - 1) {
			return false;
		}
		
		if(x <= 1 || y <= 1) {
			return false;
		}
		
		for(int yd = y - 1; yd < y + height + 1; yd++) {
			for(int xd = x - 1; xd < x + width + 1; xd++) {
				if(vertices[xd][yd].equals(OPEN)) {
					return false;
				}
			}
		}
		return true;
	}
	
	public void printDungeon() {
		for(int y = 0; y < vertices[0].length; y++) {
			for(int x = 0; x < vertices.length; x++) {
				System.out.print(vertices[x][y]);
			}
			System.out.println();
		}
	}
	
	private boolean hasWall(int x, int y) {
		
		if(vertices[x][y].equals(OPEN)) {
			return false;
		}
		
		if(vertices[x - 1][y - 1].equals(OPEN)) {
			return true;
		} else if(vertices[x][y - 1].equals(OPEN)) {
			return true;			
		} else if(vertices[x + 1][y - 1].equals(OPEN)) {
			return true;
		} else if(vertices[x - 1][y].equals(OPEN)) {
			return true;
		} else if(vertices[x + 1][y - 1].equals(OPEN)) {
			return true;
		} else if(vertices[x - 1][y + 1].equals(OPEN)) {
			return true;
		} else if(vertices[x][y + 1].equals(OPEN)) {
			return true;
		} else if(vertices[x + 1][y + 1].equals(OPEN)) {
			return true;
		} else if(vertices[x - 1][y - 1].equals(OPEN)) {
			return true;
		} 
		return false;
	}
}
