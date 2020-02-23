package v2;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class Dungeon {
	
	public static final String OPEN = "[O]";
	public static final String PATH = "[X]";
	public static final String WALL = "[W]";
	
	private Room startingRoom;
	private Set<Room> rooms;
	private String[][] vertices;
	private int[] centers;
	
	private int width, height;
	
	public Dungeon(int width, int height, int roomCount) {
		
		this.width = width;
		this.height = height;
		
		rooms = new HashSet<>();
		vertices = new String[width][height];
		
		// Create empty tiles
		for(int y = 0; y < vertices[0].length; y++) {
			for(int x = 0; x < vertices.length; x++) {
				vertices[x][y] = "[ ]";
			}
		}
		
		// Place rooms
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
<<<<<<< HEAD
					
					Room room = new Room(this, x, y, w, h);
=======
					Room room = new Room(this, x, y, w, h, "Room #" + i);
					if(rooms.isEmpty()) {
						startingRoom = room;
					}
>>>>>>> branch 'master' of https://github.com/Spruuhorn/TheFunBox.git
					rooms.add(room);
					
					centerCounter++;
					centers[centerCounter] = x;
					centers[centerCounter] = y;
					
					break;
				}
				failCounter++;
			}
		}
		
		Set<Room> unconnectedRooms = new HashSet<>();
		for(Room room : rooms) {
			unconnectedRooms.add(room);
		}
		
		Room current = startingRoom;
		while(!unconnectedRooms.isEmpty()) {
			Iterator<Room> roomIter = unconnectedRooms.iterator();
			Room next = null;
			while(roomIter.hasNext()) {
				next = roomIter.next();
				if(!current.equals(next)) {
					connectRooms(current, next);
					break;
				}
			}
			if(next == null) {
				break;
			}
			unconnectedRooms.remove(current);
			current = next;
			unconnectedRooms.remove(next);
		}
		
		for(int y = 1; y < vertices[0].length - 1; y++) {
			for(int x = 1; x < vertices.length - 1; x++) {
				if(vertices[x][y] == PATH) {
					vertices[x][y] = OPEN;
				}
			}
		}
		
		// Place Walls
		for(int y = 1; y < vertices[0].length - 1; y++) {
			for(int x = 1; x < vertices.length - 1; x++) {
				if(hasWall(x, y)) {
					vertices[x][y] = WALL;
				}
			}
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
	
	private void connectRooms(Room a, Room b) {
		int startX = a.getCenterX() ;
		int startY = a.getCenterY();
		int endX = b.getCenterX();
		int endY = b.getCenterY();
		int dirX = (int) Math.signum(endX - startX);
		int dirY = (int) Math.signum(endY - startY);
		
		int x = startX, y = startY;
		
		while(x != endX) {
			x += dirX;
			if(checkPathCollisionX(x, y)) {
				vertices[x][y] = "[X]";
				break;
			}
			
			vertices[x][y] = "[X]";
		}
		while(y != endY) {
			y += dirY;
			if(checkPathCollisionY(x, y)) {
				vertices[x][y] = "[X]";
				break;
			}
			
			vertices[x][y] = "[X]";
		}
	}
	
	private boolean checkPathCollisionY(int x, int y) {
		if (vertices[x][y].equals(PATH) ||
			vertices[x + 1][y].equals(PATH) ||
			vertices[x - 1][y].equals(PATH)) {
			return true;
		}
		return false;
	}
	
	private boolean checkPathCollisionX(int x, int y) {
		if (vertices[x][y].equals(PATH) ||
			vertices[x][y + 1].equals(PATH) ||
			vertices[x][y - 1].equals(PATH)) {
			return true;
		}
		return false;
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
