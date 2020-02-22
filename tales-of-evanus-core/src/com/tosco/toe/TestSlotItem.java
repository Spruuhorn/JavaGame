package com.tosco.toe;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TestSlotItem extends HUD {
	
	private String name;
	private String desc;
	private Texture img;
	private boolean slotted;
	
	public TestSlotItem(float x, float y, Texture texture) {
		this.name = texture.toString();
		this.x = x;
		this.y = y;
		this.img = texture;
		slotted = true;
	}
	
	@Override
	public void draw(SpriteBatch batch) {
		if(slotted) {
			batch.draw(img, x + (66 - 48)/2, y + (66 - 48)/2, 48, 48);
		} else {
			batch.draw(img, x, y);
		}
	}

	public String getName() {
		return name;
	}

	public Texture getTexture() {
		return img;
	}
	
	public void setSlotted(boolean slotted) {
		this.slotted = slotted;
	}
	
}
