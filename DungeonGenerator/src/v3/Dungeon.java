package v3;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Dungeon {
	
	public static final String OPEN = "[O]";
	public static final String WALL = "[ ]";
	
	private static final int UP = 0;
	private static final int RIGHT = 1;
	private static final int DOWN = 2;
	private static final int LEFT = 3;
	
	private Set<Room> rooms;
	private String[][] vertices;
	private String[][] centers;
	
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
		
		Random r = new Random();
		
		int x = r.nextInt(width - 2) + 1;
		int y = r.nextInt(height - 2) + 1;
		int xo = x;
		int yo = y;
		vertices[xo][yo] = OPEN;
		System.out.println("Starting at (" + xo + ", " + yo + ")");
		
		int total = 0;
		int length = 500;
		
		int lastDirection = -111;
		int error = 0;
		int masterError = 0;
		
		int[] directions = new int[4];
		
		/* In general, the number of paths we will place */
		while(total < length) {
			
			/* Total number of times we have tried going back to the origin */
			if(masterError > 10) {
				break;
			}
			
			/* Total number of times we could not place a path */
			if(error > 10) {
				x = xo;
				y = yo;
				masterError++;
			}
			
			int direction = r.nextInt(4); 
			
			/* Reduce backtracking and singular directional movement */
			while(Math.abs(direction - lastDirection) == 2 || directions[direction] > 1) {
				direction = r.nextInt(4);
			}
			
			int tiles = r.nextInt(3) + 4;
			
			if(!pathCanFit(x, y, tiles, direction)) {
				error++;
				continue;
			} else {
				if (error > 0) {
					error--;
				}
			}
			
			directions[direction]++;
			
			if(direction == UP) { // up
				
				directions[RIGHT] = 0;
				directions[DOWN] = 0;
				directions[LEFT] = 0;
				
				lastDirection = UP;
				
				System.out.println("UP " + tiles + " tiles");
				
				for(int i = 0; i < tiles; i++) {
					
					y--;
					total++;
					vertices[x][y] = OPEN;
					
				}
				
			} else if(direction == RIGHT) { // right
				
				directions[UP] = 0;
				directions[DOWN] = 0;
				directions[LEFT] = 0;
				
				lastDirection = RIGHT;
				
				System.out.println("RIGHT " + tiles + " tiles");
				
				for(int i = 0; i < tiles; i++) {
					
					x++;
					total++;
					vertices[x][y] = OPEN;
					
				}
				
			} else if(direction == DOWN) { // down
				
				directions[RIGHT] = 0;
				directions[UP] = 0;
				directions[LEFT] = 0;
				
				lastDirection = DOWN;
				
				System.out.println("DOWN " + tiles + " tiles");
				
				for(int i = 0; i < tiles; i++) {
					
					y++;
					total++;
					vertices[x][y] = OPEN;
					
				}
				
			} else if(direction == LEFT){ // left
				
				directions[RIGHT] = 0;
				directions[DOWN] = 0;
				directions[UP] = 0;
				
				lastDirection = LEFT;
				
				System.out.println("LEFT " + tiles + " tiles");
				
				for(int i = 0; i < tiles; i++) {
					
					x--;
					total++;
					vertices[x][y] = OPEN;
					
				}
			}
			
		}
		
		for(int yw = 0; yw < vertices[0].length; yw++) {
			for(int xw = 0; xw < vertices.length; xw++) {
				if(hasWall(xw, yw)) {
					vertices[xw][yw] = WALL;
				}
			}
		}
		
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
	
	private boolean pathCanFit(int x, int y, int length, int direction) {
		if(direction == UP) {
			for(int i = 0; i < length; i++) {
				y--;
				if (y <= 0 ||
					vertices[x][y].equals(OPEN) ||
					vertices[x + 1][y].equals(OPEN)	||
					vertices[x - 1][y].equals(OPEN)) {
					return false;
				}
			}
			return true;
		} else if (direction == RIGHT) {
			for(int i = 0; i < length; i++) {
				x++;
				if (x >= width - 1 || 
					vertices[x][y].equals(OPEN) ||
					vertices[x][y + 1].equals(OPEN)	||
					vertices[x][y - 1].equals(OPEN)) {
					return false;
				}
			}
			return true;
		} else if (direction == DOWN) {
			for(int i = 0; i < length; i++) {
				y++;
				if (y >= height - 1 ||
					vertices[x][y].equals(OPEN) ||
					vertices[x + 1][y].equals(OPEN)	||
					vertices[x - 1][y].equals(OPEN)) {
					return false;
				}
			}
			return true;
		} else if (direction == LEFT) {
			for(int i = 0; i < length; i++) {
				x--;
				if (x <= 0 || 
					vertices[x][y].equals(OPEN) ||
					vertices[x][y + 1].equals(OPEN)	||
					vertices[x][y - 1].equals(OPEN)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	private boolean hasWall(int x, int y) {
		
		if(vertices[x][y].equals(OPEN)) {
			return false;
		}
		
		if(x == 0 && y == 0) { //top-left
			return vertices[x + 1][y + 1].equals(OPEN);
		} else if (x == width - 1 && y == 0) { //top-right
			return vertices[x - 1][y + 1].equals(OPEN);
		} else if (x == 0 && y == height - 1) { //bottom-left
			return vertices[x + 1][y - 1].equals(OPEN);
		} else if (x == width - 1 && y == height - 1) { //bottom-right
			return vertices[x - 1][y - 1].equals(OPEN);
		} else if (x == 0) { //left column
			return vertices[x + 1][y].equals(OPEN) ||
					vertices[x + 1][y + 1].equals(OPEN) ||
					vertices[x + 1][y - 1].equals(OPEN);
		} else if (y == 0) { //top row 
			return vertices[x][y + 1].equals(OPEN) ||
					vertices[x + 1][y + 1].equals(OPEN) ||
					vertices[x - 1][y + 1].equals(OPEN);
		} else if (x == width - 1) { //right column
			return vertices[x - 1][y].equals(OPEN) ||
					vertices[x - 1][y + 1].equals(OPEN) ||
					vertices[x - 1][y - 1].equals(OPEN);
		} else if (y == height - 1) { //bottom row
			return vertices[x][y - 1].equals(OPEN) ||
					vertices[x + 1][y - 1].equals(OPEN) ||
					vertices[x - 1][y - 1].equals(OPEN);
		} else { // everything elses
			return vertices[x - 1][y - 1].equals(OPEN) ||
					vertices[x][y - 1].equals(OPEN) ||
					vertices[x + 1][y - 1].equals(OPEN) ||
					vertices[x - 1][y].equals(OPEN) ||
					vertices[x - 1][y + 1].equals(OPEN) ||
					vertices[x][y + 1].equals(OPEN) || 
					vertices[x + 1][y + 1].equals(OPEN) ||
					vertices[x - 1][y - 1].equals(OPEN);
		}
	}
}
