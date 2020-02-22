package com.tosco.toe;

import java.util.Map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import managers.GameManager;

public class AnimatedEntity extends Entity {
	
	private static final float ANIM_SPD = 0.1f;
	
	protected Animation<TextureRegion> animation;
	protected Map<String, Animation<TextureRegion>> animations;
	
	public AnimatedEntity (float x, float y, Texture sheet, String name) {
		super(x, y, name);
		
		TextureRegion[][] tmpFrames = TextureRegion.split(sheet,64,64);
		TextureRegion[] animationFrames = new TextureRegion[6];
		
		int index = 0;

		for (int i = 0; i < 6; i++){
			animationFrames[index++] = tmpFrames[0][i];
		}
		animation = new Animation<TextureRegion>(ANIM_SPD, animationFrames);
	}
	
	public void draw(SpriteBatch batch, OrthographicCamera camera) {
		
		TextureRegion frame = animation.getKeyFrame(GameManager.elapsedTime,true);
		
		batch.draw(frame, x - frame.getRegionWidth()/2, y - frame.getRegionHeight()/2);
		
		// Rectangles are drawn and calculated using screen coordinates
		/*
		if(collider != null) {
			collider.setHitbox(
					x - camera.position.x + Gdx.graphics.getWidth()/2 - frame.getRegionWidth()/2,
					y - camera.position.y + Gdx.graphics.getHeight()/2 - frame.getRegionHeight()/2, 
					frame.getRegionWidth(), 
					frame.getRegionHeight());
		}
		*/
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
