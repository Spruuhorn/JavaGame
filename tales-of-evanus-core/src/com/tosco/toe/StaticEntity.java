package com.tosco.toe;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import physics.Collider;

public class StaticEntity extends Entity {
	
	protected Texture sprite;
	
	public StaticEntity (float x, float y, Texture sprite, String name) {
		super(x, y, name);
		this.sprite = sprite;
		
		generateCollider(sprite.getWidth(), sprite.getHeight(), Collider.STATIC);
	}
	
	public StaticEntity (float x, float y, Texture sprite) {
		super(x, y, "StaticEntity");
		this.sprite = sprite;
		
		generateCollider(sprite.getWidth(), sprite.getHeight(), Collider.STATIC);
	}
	
	public void draw(SpriteBatch batch, OrthographicCamera camera) {
		batch.draw(sprite, x - sprite.getWidth()/2, y - sprite.getHeight()/2);
	}
}
