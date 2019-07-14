package entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.Renderable;

import interfaces.Clickable;
import interfaces.Drawable;

public class BasicObject extends GameObject implements Drawable, Clickable{

	public BasicObject(int x, int y, Renderable sprite) {
		super(x, y, sprite);
	}

	@Override
	public void update() {
		
	}

	@Override
	public void onClick() {
		
	}
	
	@Override
	public void onDraw() {
		if(sprite instanceof Image) { // If the sprite is an image
			int screenX = x - Camera.MAIN_CAMERA.getX() + Camera.MAIN_CAMERA.getWidth()/2 - ((Image) sprite).getWidth()/2;
			int screenY = y - Camera.MAIN_CAMERA.getY() + Camera.MAIN_CAMERA.getHeight()/2 - ((Image) sprite).getHeight()/2;
			sprite.draw(screenX, screenY);
		} else if (sprite instanceof Animation) { // If the sprite is an animation
			int screenX = x - Camera.MAIN_CAMERA.getX() + Camera.MAIN_CAMERA.getWidth()/2 - ((Animation) sprite).getWidth()/2;
			int screenY = y - Camera.MAIN_CAMERA.getY() + Camera.MAIN_CAMERA.getHeight()/2 - ((Animation) sprite).getHeight()/2;
			sprite.draw(screenX, screenY);
		}
	}

}
