package v1;
public class Room {
	
	private int x1, y1, x2, y2;
	
	private Tile[] tiles;
	
	public Room(int xo, int yo, int w, int h) {
		
		x1 = xo;
		y1 = yo;
		x2 = x1 + w;
		y2 = y1 + h;
		
	}
	
	public int getX1() {
		return x1;
	}
	
	public int getY1() {
		return y1;
	}
	
	public int getX2() {
		return x2;
	}
	
	public int getY2() {
		return y2;
	}
	
	public boolean intersects(Room otherRoom) {
		return (x1 <= otherRoom.x2 && x2 >= otherRoom.x1 && y1 <= otherRoom.y2 && otherRoom.y2 >= otherRoom.y1);
	}
	
	public String toString() {
		return "Room at (" + x1 + "," + y1 + ")";
	}
}
