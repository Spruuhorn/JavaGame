package Entities;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class GameObject {

	public float x, y;
	
	public Image spriteSheet;
	
	public Animation animatedSprite;
	
	public Collider hitBox;
	
	public ArrayList<Projectile> projectiles;
	
	public GameObject(float xo, float yo, String image, String tag) {
		
		x= xo;
		y = yo;
		
		try {
			spriteSheet = new Image(image, false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		SpriteSheet sheet = new SpriteSheet(spriteSheet, spriteSheet.getWidth() / 4, spriteSheet.getHeight());
		
		animatedSprite = new Animation(sheet, 100);
		
		hitBox = new Collider(new Rectangle(x, y, spriteSheet.getWidth() / sheet.getHorizontalCount(), spriteSheet.getHeight()), tag);
		
		GameManager.getGameObjects().add(this);
	}
	
	public GameObject(float xo, float yo, String image, String tag, Shape customHitBox) {
		
		x= xo;
		y = yo;
		
		try {
			spriteSheet = new Image(image, false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		SpriteSheet sheet = new SpriteSheet(spriteSheet, spriteSheet.getWidth() / 4, spriteSheet.getHeight());
		
		animatedSprite = new Animation(sheet, 100);
		
		hitBox = new Collider(customHitBox, tag);
		
		GameManager.getGameObjects().add(this);
	}
	
	public GameObject(float xo, float yo, String image, String tag, int i) {
		
		x= xo;
		y = yo;
		
		try {
			spriteSheet = new Image(image, false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		SpriteSheet sheet = new SpriteSheet(spriteSheet, spriteSheet.getWidth() / 4, spriteSheet.getHeight());
		
		animatedSprite = new Animation(sheet, 100);
		
		hitBox = new Collider(new Rectangle(x, y, spriteSheet.getWidth() / sheet.getHorizontalCount(), spriteSheet.getHeight()), tag);
		
	}
	
	public ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}
	
	public Image getSpriteSheet() {
		return spriteSheet;
	}
	
	public Animation getAnimatedSprite() {
		return animatedSprite;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
	
	public Shape getHitBox() {
		return getCollider().getHitBox();
	}
	
	public Collider getCollider() {
		return hitBox;
	}
	
	public int getScore() {
		return 0;
	}

	public void update(int time) {}

	public void onCollision() {}

	public boolean isVisible() {
		return true;
	}
	
	public boolean isDestroyed() {
		return false;
	}

	public void draw(Graphics g) {
	}

}
