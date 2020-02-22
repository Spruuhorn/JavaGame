package com.tosco.toe;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BackgroundHUD extends HUD {
	
	private NinePatch bg;
	private float x, y;
	private float width, height;
	
	public BackgroundHUD(float x, float y, float width, float height,
			Texture texture, int top, int right, int bottom, int left) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.bg = new NinePatch(texture, top, right, bottom, left);
	}
	
	public void setBg(NinePatch bg) {
		this.bg = bg;
	}


	public void setX(float x) {
		this.x = x;
	}


	public void setY(float y) {
		this.y = y;
	}


	public void setWidth(float width) {
		this.width = width;
	}


	public void setHeight(float height) {
		this.height = height;
	}


	public void draw(SpriteBatch batch) {
		bg.draw(batch, x, y, width, height);
	}
}
