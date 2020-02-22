package physics;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.tosco.toe.GameObject;

public class Collider {
	
	{
		
	}
	
	public static final int STATIC = 0;
	public static final int DYNAMIC = 1;
	public static final int TRIGGER = 2;
	
	private GameObject gameObject;
	private Rectangle hitbox;
	private Rectangle test, testVert;
	private float x, y;
	private float rawX, rawY;
	private float hv, vv;
	private int type;
	private boolean hasMoved;
	private boolean grounded;
	private boolean active;
	
	public Collider(float x, float y, float width, float height, GameObject gameObject, int type) {
		this.x = x;
		this.y = y;
		this.hv = 0;
		this.vv = 0;
		this.gameObject = gameObject;
		this.type = type;
		hitbox = new Rectangle(x, y, width, height);
		test = new Rectangle(x, y, width, height);
	}
	
	/* NOTE: Do not modify the values of 'other' */
	public void collisionLeft(Collider other) {
		
	}
	
	public void collisionRight(Collider other) {
		
	}
	
	public void collisionTop(Collider other) {
		
	}
	
	public void collisionBottom(Collider other) {
		
	}

	public Rectangle getHitbox() {
		return hitbox;
	}
	
	public Rectangle getTest() {
		return test;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public float getRawX() {
		return rawX;
	}
	
	public float getRawY() {
		return rawY;
	}
	
	public float getWidth() {
		return hitbox.width;
	}
	
	public float getHeight() {
		return hitbox.height;
	}
	
	public int getType() {
		return type;
	}
	
	public float getVelocityX() {
		return hv;
	}
	
	public float getVelocityY() {
		return vv;
	}

	public boolean isGrounded() {
		return grounded;
	}
	
	public void setHitbox(float x, float y, float width, float height) {
		rawX = x;
		rawY= y;
		// temporary until i figure out a way to alter dimensions of box more cleanly
		if(hitbox != null) {
			hitbox.x = x;
			hitbox.y = y;
			hitbox.width = width;
			hitbox.height = height;
			
			test.x = rawX + hv;
			test.y = rawY + vv;
		} else {
			hitbox = new Rectangle(x, y, width, height);
			test = new Rectangle(rawX, rawY, width, height);
		}
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public void setGrounded(boolean grounded) {
		this.grounded = grounded;
	}
	
	public void draw(ShapeRenderer renderer) {
		renderer.rect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
		renderer.rect(test.x, test.y, test.width, test.height);
	}
	
	public void move() {
		if(vv < Physics.TERMINAL_VELOCITY) {
			vv = Physics.TERMINAL_VELOCITY;
		}
		y += vv;
		x += hv;
		gameObject.setX(x);
		gameObject.setY(y);
	}

	public void setX(float x) {
		this.x = x;
		gameObject.setX(x);
	}
	
	public void setY(float y) {
		this.y = y;
		gameObject.setX(y);
	}
	
	public void setVelocityX(float hv) {
		this.hv = hv;
	}
	
	public void setVelocityY(float vv) {
		this.vv = vv;
	}

	// Up is negative
	public void accelerateY(float a) {
		vv += a;
	}
}
