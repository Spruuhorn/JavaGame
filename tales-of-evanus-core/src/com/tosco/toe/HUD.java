package com.tosco.toe;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import managers.HUDManager;

/* This class represents any and all displays not in game */
public abstract class HUD {

	{
		HUDManager.add(this);
		active = true;
	}
	
	protected float x;
	protected float y;
	protected int z;
	protected Rectangle rect;
	protected boolean active;
	
	public HUD(float x, float y, int z, float width, float height) {
		this.x = x;
		this.y = y;
		this.
		rect = new Rectangle(x, y, width, height);
	}
	
	public HUD(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public HUD() {
		this.x = 0;
		this.y = 0;
	}
	
	public abstract void draw(SpriteBatch batch);
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public int getZ() {
		return z;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public void setRectangle(float x, float y, float width, float height) {
		if(rect != null) {
			rect.setWidth(width);
			rect.setHeight(height);
			rect.setPosition(x, y);
		} else {
			rect = new Rectangle(x, y, width, height);
		}
	} 
	
	public Rectangle getRectangle() {
		return rect;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public void drawRectangle(ShapeRenderer shapeRenderer) {
		if(rect != null) {
			shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height);
		}
	}
	
}
