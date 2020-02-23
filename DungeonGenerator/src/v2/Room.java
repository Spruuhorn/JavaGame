package v2;

public class Room {
	
	public String name;
	
	private int width, height;
	private int x, y;
	private boolean connected;
	
	public Room(Dungeon dungeon, int xo, int yo, int width, int height, String name) {
		
		this.name = name;
		
		x = xo;
		y = yo;
		this.width = width;
		this.height = height;
		
		for(int yd = yo; yd < yo + height; yd++) {
			for(int xd = xo; xd < xo + width; xd++) {
				dungeon.setTile(xd, yd, "[O]");
			}
		}
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean connected() {
		return connected;
	}
	
	public int getCenterX() {
		return x + width/2;
	}
	
	public int getCenterY() {
		return y + height/2;
	}
	
	public int distanceTo(Room other) {
		return Math.abs(this.getCenterX() - other.getCenterX()) + Math.abs(this.getCenterY() - other.getCenterY());
	}
	
	public boolean isConnected(int x, int y) {
		if((x <= this.x + width || x >= this.x) && (y <= this.y + height || y >= this.y)) {
			return true;
		}
		return false;
	}
	
	public boolean equals(Object other) {
		if(!(other instanceof Room)) {
			return false;
		}
		if(other == this) {
			return true;
		}
		Room r = (Room)other;
		return r.x == this.x && r.y == this.y;
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")" + " <" + width + ", " + height + ">";
	}

	public void setConnected(boolean b) {
		connected = b;
	}

}
