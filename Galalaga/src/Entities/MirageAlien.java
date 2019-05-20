package Entities;

import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class MirageAlien extends Alien {
	
	private int timer = 0;
	
	private float fireRate;
	
	private int score;
	
	private Animation mirageAlien;
	
	private int realIndex;

	public MirageAlien(float x, float y, int health, float speed, String image) {
		
		super(x, y, health, speed, image);
		fireRate = 1f;
		
		score = 4;
		
		Random r = new Random();
		realIndex = r.nextInt(3);
		System.out.println(realIndex);
		
		try {
			Image sprite = new Image("MirageAlien.png");
			SpriteSheet spritesheet = new SpriteSheet(sprite, sprite.getWidth() / 4, sprite.getHeight());
			mirageAlien = new Animation(spritesheet, 100);
		} catch(SlickException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics g) {
		
		if(isVisible()) {
			mirageAlien.draw(x + (mirageAlien.getWidth() + 1), y);
			mirageAlien.draw(x + 2 * (mirageAlien.getWidth() + 1), y);
		}
	
	}
	
	public void update(int time) {
		
		getHitBox().setX(x + (realIndex * (mirageAlien.getWidth() + 1)));
		getHitBox().setY(y);
		
		timer += time;
		
		if(timer >= 1/fireRate * 1000) {
			timer = 0;
			AssetsManager.alienShoot.play();
			
			float xOffset = (getAnimatedSprite().getWidth()/2 - 8) + (realIndex * (mirageAlien.getWidth() + 1));
			float yOffset = getAnimatedSprite().getHeight()/2;
			
			getProjectiles().add(new Projectile(x + xOffset, y + yOffset, -.075f, .175f, "Alien", "AlienProjectile.png"));
			getProjectiles().add(new Projectile(x + xOffset, y + yOffset, 0f, .2f, "Alien", "AlienProjectile.png"));
			getProjectiles().add(new Projectile(x + xOffset, y + yOffset, .075f, .175f, "Alien", "AlienProjectile.png"));
		}
		
	}
	
	
	public void onCollision() {
		health--;
	}
	
	public int getScore() {
		return score;
	}
	
}
