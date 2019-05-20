package Entities;

public class BasicAlien extends Alien {
	
	private int timer = 0;
	
	private float fireRate;
	
	private int hoverY;
	
	private boolean hovering;
	
	private int score;

	public BasicAlien(float x, float y, int health, float speed, String image) {
		
		super(x, y, health, speed, image);
		
		hoverY = (int)((GameManager.GAME_HEIGHT - 250 * 2) * Math.random() + 100 / 2);
		
		hovering = false;
		
		fireRate = 0.25f;
		
		score = 1;
		
		if(x > GameManager.GAME_WIDTH - animatedSprite.getWidth()) {
			x = GameManager.GAME_WIDTH - (animatedSprite.getWidth() + 1);
		}
	}
	
	public void update(int time) {
		
		getHitBox().setX(x);
		getHitBox().setY(y);
		
		timer += time;
		
		if(y != hoverY) {
			hovering = true;
			y += speed * time;
		} else {
			x += speed * time;
		}
		
		if(x < 0 || x > GameManager.GAME_WIDTH - animatedSprite.getWidth()) {
			speed *= -1;
		}
		
		if(timer >= 1/fireRate * 1000 && hovering) {
			timer = 0;
			AssetsManager.alienShoot.play();
			getProjectiles().add(new Projectile(x + (getAnimatedSprite().getWidth()/2 - 8), y, 0f, .2f, "Alien", "AlienProjectile.png"));
		}
	}
	
	public void onCollision() {
		health--;
	}
	
	public int getScore() {
		return score;
	}
	
}
