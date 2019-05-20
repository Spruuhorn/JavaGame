package Entities;

import java.util.ArrayList;

public class Alien extends GameObject {
	
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	
	public int health;
	
	public float speed;
	
	public int score;

	public Alien(float x, float y, int health, float speed, String image) {
		
		super(x, y, image, "Alien");
		
		this.health = health;
		this.speed = speed;
		
	}
	
	public ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}
	
	public boolean isVisible() {
		return health > 0;
	}
	
	public boolean isDestroyed() {
		return (!isVisible() && getProjectiles().isEmpty());
	}

}
