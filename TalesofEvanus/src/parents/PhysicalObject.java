package parents;

import java.util.ListIterator;

import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import managers.AssetManager;
import objects.Player;
import other.Transform;
import other.Vector2;

public class PhysicalObject extends Sprite {
	
	private final static float collisionStep = 1.5f;
	
	private final static int UP = -1;
	private final static int DOWN = 1;
	private final static int LEFT = -1;
	private final static int RIGHT = 1;
	private final static int NONE = 0;
	
	public final static int CREATURE = 0;
	public final static int IMPASSABLE = 1;
	public final static int PORTAL = 2;
	
	protected Shape hitbox;
	protected int tag;
	protected Vector2 velocity;
	
	protected float xOffset, yOffset, width, height;
	
	public boolean up, down, left, right;
	
	private int sideH, sideV;
	
	public PhysicalObject(int tag, String anim, String name, Transform trans) {
		super(anim, name, trans);
		this.tag = tag;

		initBase();
		
		AssetManager.physicalObjects.add(this);
	}

	public PhysicalObject(int tag, String animation, Transform trans, int[] points) {
		super(animation, trans);
		this.tag = tag;

		xOffset = points[0];
		yOffset = points[1];
		width   = points[2];
		height  = points[3];
		
		initBase();
		
		AssetManager.physicalObjects.add(this);
	}	
	
	public PhysicalObject(int tag, String animation, Transform trans) {
		super(animation, trans);
		this.tag = tag;

		initBase();
		
		AssetManager.physicalObjects.add(this);
	}
	
	public PhysicalObject(String anim, String name, Transform trans) {
		super(anim, name, trans);
		initBase();
		
		AssetManager.physicalObjects.add(this);
	}
	
	public PhysicalObject(String anim, String name, Transform trans, int[] points) {
		super(anim, name, trans);
		
		xOffset = points[0];
		yOffset = points[1];
		width   = points[2];
		height  = points[3];
		
		initBase();
		
		AssetManager.physicalObjects.add(this);
	}
	
	public PhysicalObject(String animation, Transform trans) {
		super(animation, trans);
		initBase();

		AssetManager.physicalObjects.add(this);
	}
	
	public void applyMovement() {
		
		if(Math.signum(velocity.x) != sideH) {
			transform.worldSpace.x += velocity.x;
		} else {
			velocity.x = 0;
		}
		
		if(Math.signum(velocity.y) != sideV) {
			transform.worldSpace.y += velocity.y;
		} else {
			velocity.y = 0;
		}
		
	}
	
	public void updateColliders() {
		hitbox.setLocation(transform.screenSpace.x + xOffset, transform.screenSpace.y + yOffset);
	}
	
	public Shape getHitBox() {
		return hitbox;
	}
	
	public int getTag() {
		return tag;
	}
	
	public Vector2 getVelocity() {
		return velocity;
	}
	
	public void onCollision(PhysicalObject other) {
		//I am the second giver of life
	}
	
	private void initBase() {
		
		if(width == 0 || height == 0) {
			this.hitbox = new Rectangle(transform.worldSpace.x, transform.worldSpace.y, animation.getWidth(), animation.getHeight());
		} else {
			this.hitbox = new Rectangle(transform.worldSpace.x + xOffset, transform.worldSpace.y + yOffset, width, height);
		}
		this.velocity = new Vector2();
	}
	
	public boolean isSomethingOnMyHorizontals(ListIterator<PhysicalObject> physicalObjectIterator) {
		
		while(physicalObjectIterator.hasNext()) {
			
			PhysicalObject other = physicalObjectIterator.next();
			
			if(other.equals(this)) {
				continue;
			}
			
			if(other.isActive()) {
		
				for(int i = 0; i < 4; i++) {
					
					float space = hitbox.getHeight()/(4 - 1);
					
					Point collisionPoint = new Point(hitbox.getMaxX() + collisionStep, hitbox.getMinY() + (i * space));
					
					if(other.hitbox.contains(collisionPoint) && other.getTag() == IMPASSABLE) {
						right = true;
						sideH = RIGHT;
						return true;
					}
				}
				
				for(int i = 0; i < 4; i++) {
					
					float space = hitbox.getHeight()/(4 - 1);
					
					Point collisionPoint = new Point(hitbox.getMinX() - collisionStep, hitbox.getMinY() + (i * space));
					
					if(other.hitbox.contains(collisionPoint) && other.getTag() == IMPASSABLE) {
						left = true;
						sideH = LEFT;
						return true;
					}
				}
				
				
			}
		}
		
		left = false;
		right = false;
		sideH = NONE;
		return false;
		
	}
	
	public boolean isSomethingOnMyVerticals(ListIterator<PhysicalObject> physicalObjectIterator) {
		
		while(physicalObjectIterator.hasNext()) {
			
			PhysicalObject other = physicalObjectIterator.next();
			
			if(other.equals(this)) {
				continue;
			}
			
			if(other.isActive()) {
		
				for(int i = 0; i < 4; i++) {
					
					float space = hitbox.getWidth()/(4 - 1);
					
					Point collisionPoint = new Point(hitbox.getMinX() + (i * space), hitbox.getMaxY() + collisionStep);
					
					if(other.hitbox.contains(collisionPoint) && other.getTag() == IMPASSABLE) {
						down = true;
						sideV = DOWN;
						return true;
					}
				}
				
				for(int i = 0; i < 4; i++) {
					
					float space = hitbox.getWidth()/(4 - 1);
					
					Point collisionPoint = new Point(hitbox.getMinX() + (i * space), hitbox.getMinY() - collisionStep);
					
					if(other.hitbox.contains(collisionPoint) && other.getTag() == IMPASSABLE) {
						up = true;
						sideV = UP;
						return true;
					}
				}
			}
		}
		
		up = false;
		down = false;
		sideV = NONE;
		return false;
		
	}
	
	public boolean intersects(PhysicalObject other) {
		return hitbox.intersects(other.hitbox);
	}
}
