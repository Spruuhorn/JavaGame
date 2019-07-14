package entities;

import java.util.HashSet;
import java.util.Set;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.Renderable;
import org.newdawn.slick.geom.Shape;

import entities.Camera;
import interfaces.Clickable;

public abstract class GameObject {
	
	public static Set<GameObject> GAMEOBJECTS;
	static { GAMEOBJECTS = new HashSet<>(); }
	{ 
		GAMEOBJECTS.add(this); 
		if(this instanceof Clickable) {
			System.out.println("Instance of clickable");
		}
	}
	
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
	
	public void remove() {
		GameObject.GAMEOBJECTS.remove(this);
	}
	
	public boolean isColliding(Shape other) {
		return collider.intersects(other);
	}
	
	public boolean isCollidable() {
		return collider != null;
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
