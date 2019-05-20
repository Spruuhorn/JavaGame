package Entities;

public class SlidingAlien extends Alien {
	
	/*A lot of hard coded values here that allow for triple shots
	 * The laserDuration is how long the aline has for shooting
	 */
	
	private int timer = 0, laserTimer = 0;
	
	private float movementTimer = 0f;
	
	private float fireRate;
	
	private float laserDuration;
	
	private int score;
	
	private double cosMultiplier, sinMultiplier;
	
	private float originX, originY, radiusX, radiusY;
	
	private GameObject target;

	public SlidingAlien(float x, float y, int health, float speed, String image) {
		
		super(x, y, health, speed, image);
		
		fireRate = 1f;
		
		laserDuration = .3f;
		
		score = 2;
		
		originX = x;
		originY = y;
		
		doMath();
		
	}
	
	private void doMath() {
		cosMultiplier = Math.random();
		sinMultiplier = Math.random();
		
		radiusX = (int) (Math.random() * 100);
		radiusY = (int) (Math.random() * 100);
	}
	
	public void update(int time) {
		
		getHitBox().setX(x);
		getHitBox().setY(y);
		
		timer += time;
		
		//time / movespeed (of some sort)... the lower the number, the faster the spin
		movementTimer += (float)(time)/250;
		
		//ORIGIN - RADIUS
		x = (float) (originX + radiusX * Math.cos(cosMultiplier * movementTimer));
		y = (float) (originY + radiusY * Math.sin(cosMultiplier * movementTimer));
			
		
		if(timer >= 1/fireRate * 1000) {
			
			laserTimer += time;
			
			//Can be modified to alter how many shots are put into burst
			if(laserTimer % 75 == 0) {
				AssetsManager.alienShoot.play();
				getProjectiles().add(new Projectile(x + (getAnimatedSprite().getWidth()/2 - 8), y + getAnimatedSprite().getHeight()/2, 0f, .3f, "Alien", "AlienProjectile.png"));
			}
			
			if(laserTimer >= laserDuration * 1000) {
				laserTimer = 0;
				timer = 0;
			}
		}
	}
	
	public void onCollision() {
		health--;
	}
	
	public int getScore() {
		return score;
	}
	
}
