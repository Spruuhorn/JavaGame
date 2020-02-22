package physics;

import com.badlogic.gdx.math.Rectangle;
import com.tosco.toe.GameObject;

import managers.GameManager;

public class Physics {
	
	public static boolean APPLY = true;
	public static float TERMINAL_VELOCITY = -50f;
	public static float GRAVITY_ACCELERATION = -0.1f;
	
	public static Rectangle RECT_TEST = new Rectangle(0,0,0,0);
	
	// After Physics are calculated, move the game objects
	// begs the question, who should do the moving? the collider telling or the gameobject following
	public static void update() {
		// For each ACTIVE grid cell to check in the current map's collision map
		/*
		for(Set<Collider> colliders : GameManager.getActiveGrid()) {
			// For each collider in the current grid
			for(Collider collider : colliders) {
				// For every other collider in the current grid
				if(collider.getType() == DYNAMIC) {
					for(Collider other : colliders) {
						if(!collider.equals(other)) {
							
						}
					}
				}
			}
		}
		*/
		/*
		for(GameObject object : GameManager.getGameObjects()) {
			for(GameObject other : GameManager.getGameObjects()) {
				if(!object.equals(other)) {
					if(object.getCollider().getHitbox().overlaps(other.getCollider().getHitbox())) {
						APPLY = false;
					}
				}
			}
		}
		*/
		
		if(APPLY) {
			for(GameObject object : GameManager.getGameObjects()) {
				
				Collider collider = object.getCollider();
				if(collider.getType() == Collider.DYNAMIC) {
					Rectangle test = collider.getTest();
					/*
					RECT_TEST.set(collider.getHitbox().x, collider.getHitbox().y, collider.getWidth(), collider.getHeight());
					System.out.println("RECT_TEST: " + RECT_TEST.x + " " + RECT_TEST.y);
					int deltaX = 0;
					int deltaY = 0;
					
					float lastX = RECT_TEST.getX();
					float lastY = RECT_TEST.getY();
					
					float velocityX = collider.getVelocityX();
					float velocityY = collider.getVelocityY();
					
					boolean canMoveX = true;
					boolean canMoveY = true;
					
					if(velocityX == 0 && (velocityY == 0 && collider.isGrounded())) {
						continue;
					}
					if(velocityX > collider.getWidth()) {
						deltaX = (int)(velocityX / collider.getWidth());
					}
					if(velocityY > collider.getHeight()) {
						deltaY = (int)(velocityY / collider.getHeight());
					}
					
					*/
					collider.accelerateY(GRAVITY_ACCELERATION);
					
					for(GameObject otherObject : GameManager.getGameObjects()) {
						Collider otherCollider = otherObject.getCollider();
						Rectangle other = otherCollider.getHitbox();
						if(!object.equals(otherObject)) {
							if(test.overlaps(other)) {
								collider.setY(otherObject.getY() + otherCollider.getHeight() + 100);
								collider.setGrounded(true);
								collider.setVelocityY(0);
								System.out.println("collision with " + otherObject.getName());
							}
							/*
							// Scanning horizontal
							for(int i = 1; i <= 1 + deltaX; i++) {
								RECT_TEST.setX(RECT_TEST.getX() + (velocityX * i));
								if(RECT_TEST.overlaps(other)) {
									System.out.println("COLLIDING X WITH " + otherObject.getName());
									if(lastX <= other.getX()) {
										collider.collisionRight(otherCollider);
									} else if (lastX > other.getX()) {
										collider.collisionLeft(otherCollider);
									}
									canMoveX = false;
									break;
								}
								lastX = RECT_TEST.getX();
							}
							
							// Scanning Vertical
							for(int i = 1; i <= 1 + deltaY; i++) {
								RECT_TEST.setY(RECT_TEST.getY() + (velocityY * i));
								System.out.println(object.getName() + " checking at " + RECT_TEST.getY() + " from " + " this y: " + object.getY() + " (" + i + ")");
								if(RECT_TEST.overlaps(other)) {
									System.out.println("COLLIDING Y WITH " + otherObject.getName());
									if(lastY > other.getY()) {
										collider.collisionTop(otherCollider);
										RECT_TEST.setY(other.y - RECT_TEST.height);
									} else if (lastY <= other.getY()) {
										collider.collisionBottom(otherCollider);
										RECT_TEST.setY(other.y + other.height);
										
										// Lower collision -> GROUNDED
										collider.setGrounded(true);
									}
									canMoveY = false;
									break;
								}
								
								lastY = RECT_TEST.getY();
								collider.setGrounded(false);
							}
							*/
						}
					}
					
					// Update movements with collision info
					// GRAVITY
					
					/*
					if(!canMoveX) {
						collider.setVelocityX(0);
					}
					
					if(!canMoveY) {
						collider.setVelocityY(0);
					}
					*/
					collider.move();
				}
			}
		}
	}
}
