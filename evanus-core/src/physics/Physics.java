package physics;

import com.badlogic.gdx.graphics.Color;

import tosco.evanus.GameObject;

import manager.GameManager;

public class Physics {
	
	public static boolean APPLY = false;
	public static float TERMINAL_VELOCITY = -50f;
	public static float GRAVITY_ACCELERATION = -0.8f;
	
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
		
		for(GameObject object : GameManager.getGameObjects()) {
			
			Collider collider = object.getCollider();
			if(collider.getType() == Collider.DYNAMIC) {
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
				boolean colliding = false;
				
				if(APPLY) {
					collider.accelerateY(GRAVITY_ACCELERATION);
				}
				
				float lower = collider.getY();
				float upper = collider.getY() + collider.getHeight();
				float right = collider.getX() + collider.getWidth();
				float left = collider.getX();
				
				collider.move();
				
				for(GameObject otherObject : GameManager.getGameObjects()) {
					Collider otherCollider = otherObject.getCollider();
					
					if(!object.equals(otherObject)) {
						
						float otherLower = otherCollider.getY();
						float otherUpper = otherCollider.getY() + otherCollider.getHeight();
						float otherRight = otherCollider.getX() + otherCollider.getHeight();
						float otherLeft = otherCollider.getX();
						
						if(collider.betterCollides(otherCollider)) {
							
							if (collider.getVelocityY() < 0) {
								System.out.println("BOT");
								collider.translateY(otherUpper - lower);
							} 
							if (collider.getVelocityY() > 0) {
								System.out.println("TOP");
								collider.translateY(upper - otherLower);
							}
							if (collider.getVelocityX() < 0) {
								System.out.println("LEFT");
							}
							if (collider.getVelocityX() > 0) {
								System.out.println("RIGHT");
							}
							/*
							// if right side collision else if left side collision
							if(collider.getLastX() < otherCollider.getX() + otherCollider.getWidth()/2) {
								//collider.translateX(otherLeft - right);
								System.out.println("RIGHT");
							} else if(collider.getLastX() >= otherCollider.getX() + otherCollider.getWidth()/2) {
								//collider.translateX(left - otherRight);
								System.out.println("LEFT");
							}
							
							// if top side collision else if bottom side collision
							if(collider.getLastY() < otherCollider.getY() + otherCollider.getHeight()/2) {
								//collider.translateY(upper - otherLower);
								System.out.println("TOP");
							} else if(collider.getLastY() >= otherCollider.getY() + otherCollider.getHeight()/2) {
								//collider.translateY(otherUpper - lower);
								System.out.println("BOT");
							}
							*/
							
							collider.setVelocityY(0);
							collider.setGrounded(true);
							colliding = true;
							otherCollider.setColor(Color.RED);
						} else {
							otherCollider.setColor(Color.GREEN);
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
				
				if (colliding) {
					collider.setColor(Color.RED);
				} else {
					collider.setColor(Color.GREEN);
				}
			}
		}
	}
}

