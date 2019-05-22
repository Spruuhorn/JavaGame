package entities;

import java.util.HashSet;
import java.util.Set;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.Renderable;
import org.newdawn.slick.geom.Shape;

import entities.Camera;

public abstract class GameObject {
	
	public static Set<GameObject> GAMEOBJECTS;
	static { GAMEOBJECTS = new HashSet<>(); }
	{ GAMEOBJECTS.add(this); }
	
	protected int x, y;
	protected boolean active;
	protected String name;
	protected Shape collider;
	protected Renderable sprite; 
	/* I made the sprite the interface Renderable because sprites are stored 
	 * in the AssetManager as Renderable.
	 */
	
	public GameObject(int x, int y) {
		this.x = x;
		this.y = y;
		this.active = true;
	}
	
	public GameObject(int x, int y, Renderable sprite) {
		this(x, y);
		this.sprite = sprite;
	}
	
	public GameObject(int x, int y, Shape collider, Renderable sprite) {
		this(x, y);
		this.collider = collider;
		this.sprite = sprite;
	}
	
	public abstract void update();
	
	public void draw() {
		if(sprite instanceof Image) { // If the sprite is an image
			int screenX = x - Camera.MAIN_CAMERA.getX() + Camera.MAIN_CAMERA.getWidth()/2 - ((Image) sprite).getWidth()/2;
			int screenY = y - Camera.MAIN_CAMERA.getY() + Camera.MAIN_CAMERA.getHeight()/2 - ((Image) sprite).getHeight()/2;
			sprite.draw(screenX, screenY);
		} else if (sprite instanceof Animation) { // If the sprite is an animation
			int screenX = x - Camera.MAIN_CAMERA.getX() + Camera.MAIN_CAMERA.getWidth()/2 - ((Animation) sprite).getWidth()/2;
			int screenY = y - Camera.MAIN_CAMERA.getY() + Camera.MAIN_CAMERA.getHeight()/2 - ((Animation) sprite).getHeight()/2;
			sprite.draw(screenX, screenY);
		}
	}
	
	public boolean isColliding(Shape other) {
		return collider.intersects(other);
	}
	
	public boolean isCollidable() {
		return collider != null;
	}
	
	public boolean isDrawable() {
		return sprite != null;
	}
	
	public boolean isActive() {
		return active;
	}
	
	// Getters and Setters
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public String getName() {
		return name;
	}
	
	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
