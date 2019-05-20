package Entities;

import GameStates.GalalagaLauncher;

public class Projectile extends GameObject {
	
	private float speedX, speedY;
	
	private String tagToIgnore;
	
	public Projectile(float x, float y, float speedX, float speedY, String image) {
		
		super(x, y, image, "Projectile", 0);
		
		this.speedY = speedY;
		this.speedX = speedY;
	}
	
	public Projectile(float x, float y, float speedX, float speedY, String tag, String image) {
		
		super(x, y, image, "Projectile", 0);
		
		this.speedY = speedY;
		this.speedX = speedX;
		
		tagToIgnore = tag;
	}
	
	public void update(int time) {
		
		getHitBox().setX(x);
		getHitBox().setY(y);
		
		y += speedY * time;
		x += speedX * time;
	}
	
	public boolean isDestroyed() {
		return y < 0 || y > GalalagaLauncher.height;
	}
	
	public String getTagToIgnore() {
		return tagToIgnore;
	}
}
