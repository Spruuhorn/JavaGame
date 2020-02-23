package com.tosco.toe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;

import interfaces.Updateable;
import physics.Collider;
import physics.Physics;

public class Player extends AnimatedEntity implements Updateable {

	private final static String name = "Player";
	
	private InventoryHUD inventoryHud;
	private float speed = 4;
	private float jump = 12;
	
	public Player(float x, float y, Texture sheet) {
		super(x, y, sheet, name);
		generateCollider(32, 64, Collider.DYNAMIC);
		//inventoryHud = new InventoryHUD(5, 6, new Texture("nineslice2.png"));
		//inventoryHud.setActive(false);
	}

	@Override
	public void update() {

		if(Gdx.input.isKeyPressed(Keys.A)) {
			collider.setVelocityX(-speed);
		} else if(Gdx.input.isKeyPressed(Keys.D)) {
			collider.setVelocityX(speed);
		} else {
			collider.setVelocityX(0);
		}
		
		if(Physics.APPLY) {
			if(Gdx.input.isKeyPressed(Keys.W) && collider.isGrounded()) {
				collider.setVelocityY(jump);
				collider.setGrounded(false);
			}
		} else {
			if(Gdx.input.isKeyPressed(Keys.W)) {
				collider.setVelocityY(speed);
			} else if(Gdx.input.isKeyPressed(Keys.S)) {
				collider.setVelocityY(-speed);
			} else {
				collider.setVelocityY(0);
			}
		}
			
		if(Gdx.input.isKeyJustPressed(Keys.I)) {
			inventoryHud.setActive(!inventoryHud.isActive());
		}
		
	}
	
}
