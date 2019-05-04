package v1;
import java.util.ArrayList;
import java.util.List;

public class Map {
	
	private final int PADDING = 1;
	
	private String[][] map;
	
	private int width, height;
	
	private int numberOfRooms;
	
	private List<Room> rooms;
	
	public Map(int width, int height, int numberOfRooms) {
		
		this.width = width;
		this.height = height;
		this.numberOfRooms = numberOfRooms;
		
		map = new String[width][height];
		rooms = new ArrayList<Room>();
		
		//Create starting room
		createStartRoom();
		createRooms();
		generateMap();
		drawMap();
		
	}

	private void generateMap() {
		
		for(int row = 0; row < height; row++) {
			for(int col = 0; col < width; col++) {
				map[row][col] = "-";
			}
		}
		
		for(Room room : rooms) {
			for(int row = room.getY1(); row < room.getY2(); row++) {
				for(int col = room.getX1(); col < room.getX2(); col++) {
					map[row][col] = "X";
				}
			}
		}
	}
	
	private void drawMap() {
		
		for(int row = 0; row < width; row++) {
			for(int col = 0; col < height; col++) {
				System.out.print(map[row][col]);
			}
			System.out.println();
		}
		
	}
	
	private void createStartRoom() {
		
		int roomWidth = 3;
		int roomHeight = 3;
		
		int x = MapGenerator.randomNum(width - (roomWidth + PADDING), PADDING);
		int y = MapGenerator.randomNum(height - (roomHeight + PADDING), PADDING);
		
		rooms.add(new Room(x, y, roomWidth, roomHeight));
		
	}
	
	private void createRooms() {
		
		int count = 0;
		
		int breakCount = 0;
		
		while(count < numberOfRooms) {
			
			if(breakCount == 999) {
				break;
			}
			
			boolean intersects = false;
			
			int roomWidth = MapGenerator.randomNum(8, 5);
			int roomHeight = MapGenerator.randomNum(6, 4);
			
			int x = MapGenerator.randomNum(width - (roomWidth + PADDING), PADDING);
			int y = MapGenerator.randomNum(height - (roomHeight + PADDING), PADDING);
			
			Room newRoom = new Room(x, y, roomWidth, roomHeight);
			
			for(Room room : rooms) {
				
				if(newRoom.intersects(room)) {
					
					intersects = true;
					break;
					
				}
					
			}
			
			if(!intersects) {
				rooms.add(newRoom);
				count++;
			} else {
				breakCount++;
				continue;
			}
	
		}
		
	}
	
	public void createHalls() {
		
	}
	
	public void createWalls() {

	}
}
