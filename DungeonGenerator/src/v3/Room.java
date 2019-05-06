package v3;

public class Room {
	
	private int width, height;
	private int x, y;
	
	public Room(Dungeon dungeon, int xo, int yo, int width, int height) {
		
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
	
	public String toString() {
		return "(" + x + ", " + y + ")" + " <" + width + ", " + height + ">";
	}
}
