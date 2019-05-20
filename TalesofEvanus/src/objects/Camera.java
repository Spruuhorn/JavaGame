package objects;

import other.MapLoader;
import other.Transform;
import other.Vector2;
import parents.GameObject;

public class Camera extends GameObject {
	
	private Vector2 size;
	private Transform target;
	
	public Camera() {
		super("Main Camera", new Transform(0,0));
	}
	
	public Camera(float xo, float yo, float width, float height) {
		super("Main Camera", new Transform(xo, yo));
		//Well, the size ain't exactly a Transform, but I'll let it slide...
		size = new Vector2(width, height);
	}
	
	public Camera(Transform toFollow, float width, float height) {
		super("Main Camera", new Transform(toFollow.worldSpace.x, toFollow.worldSpace.y));
		target = toFollow;
		//Well, the size ain't exactly a Transform, but I'll let it slide...
		size = new Vector2(width, height);
	}

	public void update() {
		
		//Minus 32 accounts for half the tile size
		
		if(target != null) {
			if( !((target.worldSpace.x - size.x/2) <= -32) && !((target.worldSpace.x + size.x/2) >= MapLoader.currentActiveMap.getWidth() - 32)) {
				transform.worldSpace.x = target.worldSpace.x;
			} else {
				if(transform.worldSpace.x - size.x/2 < -32) {
					transform.worldSpace.x += 16;
				}
				
				if(transform.worldSpace.x + size.x/2 > MapLoader.currentActiveMap.getWidth() - 32) {
					transform.worldSpace.x -= 16;
				}
			}
			
			if( !((target.worldSpace.y - size.y/2) <= -32) && !((target.worldSpace.y + size.y/2) >= MapLoader.currentActiveMap.getHeight() - 32)) {
				transform.worldSpace.y = target.worldSpace.y;
			} else {
				if(transform.worldSpace.y - size.y/2 < -32) {
					transform.worldSpace.y += 16;
				}
				
				if(transform.worldSpace.y + size.y/2 > MapLoader.currentActiveMap.getHeight() - 32) {
					transform.worldSpace.y -= 16;
				}
			}
		}
		
	}
	
	public void follow(Transform newTarget) {
		target = newTarget;
	}
	
	public void goTo() {
		transform.setWorldSpace(target.worldSpace.x, target.worldSpace.y);
	}
	
	public Vector2 getSize() {
		return size;
	}
	
	public Vector2 screenToWorldSpace(float x, float y) {
		
		float worldSpaceX = x + (transform.worldSpace.x - size.x/2);
		float worldSpaceY = y + (transform.worldSpace.y  - size.y/2);

		return new Vector2(worldSpaceX, worldSpaceY);
		
	}
	
}
