package Entities;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Ellipse;

public class Player extends GameObject {
	
	private int moveUp, moveDown, moveLeft, moveRight, shoot;
	
	private Input input;
	
	private float maxSpeed, speed;
	
	private float fireRate, nextFire;
	
	private int health;
	
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

	public Player(float x, float y, int health, String string) {
		
		super(x, y, string, "Player", new Ellipse(0,0, 20, 24));
		
		this.health = health;
		
		maxSpeed = .5f;
		speed = maxSpeed;
		
		fireRate = 3;
		nextFire = 0;
	}
	
	public void configureControls(int up, int down, int left, int right, int fire) {
		moveUp = up;
		moveDown = down;
		moveLeft = left;
		moveRight = right;
		shoot = fire;
	}
	
	public void update(int time) {
		
		getHitBox().setCenterX(x + animatedSprite.getWidth()/2);
		getHitBox().setCenterY(y + animatedSprite.getHeight()/2);
		
		nextFire += time;
		
		if(input.isKeyDown(moveRight)) {
			x +=speed;
		}
		
		if(input.isKeyDown(moveLeft)) {
			x -=speed;
		}
		
		if(input.isKeyDown(moveUp)) {
			y -=speed;
		}
		
		if(input.isKeyDown(moveDown)) {
			y +=speed;
		}
		
		if(input.isKeyPressed(shoot)) {
			fire();
		}
		
	}
	
	private void fire() {
		if(nextFire >= 1/fireRate * 1000) {
			nextFire = 0;
			AssetsManager.playerShoot.play();
			projectiles.add(new Projectile(x + (getAnimatedSprite().getWidth()/2 - 8), y, 0f, -.6f, "Player", "PlayerProjectileSmall.png"));
		}
	}
	
	public ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}

	public void setInput(GameContainer gc) {
		input = gc.getInput();
	}
	
	public void onCollision() {
		health--;
	}
	
	public boolean isVisible() {
		return health > 0;
	}
	
	public int getHealth() {
		return health;
	}
	
	public boolean isDestroyed() {
		return (!isVisible() && getProjectiles().isEmpty());
	}

	
}
