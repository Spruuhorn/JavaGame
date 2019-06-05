package other;

public class Vector2 {
	
	public float x, y;
	
	public Vector2() {
		this.x = 0;
		this.y = 0;
	}
	
	public Vector2(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2(Vector2 other) {
		this.x = other.x;
		this.y = other.y;
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
