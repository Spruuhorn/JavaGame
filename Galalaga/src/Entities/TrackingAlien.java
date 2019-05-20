package Entities;

public class TrackingAlien extends Alien {
	
	private int timer = 0;
	
	private float fireRate;
	
	private int score;
	
	private GameObject target;

	public TrackingAlien(float x, float y, int health, float speed, String image) {
		
		super(x, y, health, speed, image);
		
		fireRate = 1f;
		
		score = 3;
		
		double distSquared = 0, biggerDistSquared = 0;
		
		for(GameObject obj : GameManager.getGameObjects()) {
			if(obj.getCollider().getTag().equals("Player")) {
				
				distSquared = obj.getX() * obj.getX() + obj.getY() * obj.getY();
				
				if(distSquared > biggerDistSquared) {
					biggerDistSquared = distSquared;
					target = obj;
				}
				
			}
		}
		
		System.out.println(Math.cos(0));
	}
	
	public void update(int time) {
		
		getHitBox().setX(x);
		getHitBox().setY(y);
		
		timer += time;
		
		if(timer >= 1/fireRate * 1000) {
			timer = 0;
			AssetsManager.alienShoot.play();
			
			float diffX = x - target.x;
			float diffY = y - target.y;
			
			float angle = (float) (Math.atan2(diffY, diffX));
			
			System.out.println("Angle: " + angle);
			System.out.println(Math.cos(angle) + " " + Math.sin(angle));
			
			getProjectiles().add(new Projectile(x + (getAnimatedSprite().getWidth()/2 - 8), y + getAnimatedSprite().getHeight()/2, (float)-(.2f * Math.cos(angle)), (float)-(.2f * Math.sin(angle)), "Alien", "AlienProjectile.png"));
		}
	}
	
	public void onCollision() {
		health--;
	}
	
	public int getScore() {
		return score;
	}
	
}
