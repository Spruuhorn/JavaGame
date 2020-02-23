package tosco.evanus;

import com.badlogic.gdx.math.Rectangle;

import interfaces.Clickable;
import interfaces.Hoverable;
import manager.GameManager;
import physics.Collider;

/* This class represents any and all things in the GameMap */
public abstract class GameObject implements Clickable, Hoverable {

	{
		GameManager.addNewGameObject(this);
		active = true;
	}
	
	protected String name;
	protected float x, y;
	protected Collider collider;
	protected boolean active;
	
	public GameObject(float x, float y, String name) {
		this.x = x;
		this.y = y;
		this.name = name;
	}
	
	public GameObject() {
		this.x = 0;
		this.y = 0;
		this.name = "GameObject";
	}
	
	public void generateCollider(float width, float height, int type) {
		collider = new Collider(x - width/2, y - height/2, width, height, this, type);
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public String getName() {
		return name;
	}
	
	// probably should get rid of this
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public Collider getCollider() {
		return collider;
	}
	
	public Rectangle getHitbox() {
		return collider.getHitbox();
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public void move() {
		x = collider.getX() + collider.getWidth()/2;
		y = collider.getY() + collider.getHeight()/2;
	}
}
