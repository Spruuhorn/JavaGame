package other;

public class Transform {
	
	public Vector2 worldSpace;
	public Vector2 screenSpace;
	public int z;
	
	//Screen space may have to be calculated before hand to avoid collision detections at start up
	
	public Transform(float worldX, float worldY) {
	    this.worldSpace = new Vector2(worldX, worldY);
	    this.screenSpace = new Vector2();
	}
	
	public Transform(float worldX, float worldY, int z) {
	    this.worldSpace = new Vector2(worldX, worldY);
	    this.screenSpace = new Vector2();
	    this.z = z;
	}
	
	public Transform(Vector2 vector2) {
	    this.worldSpace = vector2;
	    this.screenSpace = new Vector2();
	}
	
	public void setWorldSpace(float x, float y) {
		worldSpace.x = x;
		worldSpace.y = y;
	}
	
	public void setScreenSpace(float x, float y) {
		screenSpace.x = x;
		screenSpace.y = y;
	}
	
	public void setWorldSpace(Vector2 vector2) {
		worldSpace = vector2;
	}
	
	public void setScreenSpace(Vector2 vector2) {
		screenSpace = vector2;
	}
	
	public String toString() {
		return worldSpace.x + ", " + worldSpace.y;
	}
	
}
