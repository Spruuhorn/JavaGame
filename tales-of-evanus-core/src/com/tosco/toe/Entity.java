package com.tosco.toe;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Entity extends GameObject {

	protected float screenX, screenY;
	
	public Entity(float x, float y, String name) {
		super(x, y, name);
	}
	
	public abstract void draw(SpriteBatch batch, OrthographicCamera camera);
	
	@Override
	public void onClick(int x, int y) {
		System.out.println("____________________________________________");
		System.out.println("Clicked on " + name + " at (" + x + ", " + y + ")");
		System.out.println("Worldspace at (" + this.x + ", " + this.y + ")");
		System.out.println("Collider at (" + this.getCollider().getX() + ", " + this.getCollider().getY() + ")");
		System.out.println("____________________________________________");
	}

	@Override
	public void onHover(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onHoverOff() {
		// TODO Auto-generated method stub
		
	}
}
