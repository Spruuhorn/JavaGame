package entities;

import interfaces.Clickable;
import interfaces.Drawable;

public class BasicObject extends GameObject implements Drawable, Clickable{

	public BasicObject(int x, int y, boolean hitbox, String sprite) {
		super(x, y, hitbox, sprite);
	}

	@Override
	public void update() {
		
	}

	@Override
	public void onClick() {
		System.out.println("I'm Evan!");
	}
	
	@Override
	public void onDraw() {
		int screenX = x - Camera.MAIN_CAMERA.getX() + Camera.MAIN_CAMERA.getWidth()/2 - sprite.getWidth()/2;
		int screenY = y - Camera.MAIN_CAMERA.getY() + Camera.MAIN_CAMERA.getHeight()/2 - sprite.getHeight()/2;
		sprite.draw(screenX, screenY, sprite.getWidth() * 0.5f, sprite.getHeight() * 0.5f);
		collider.setX(screenX);
		collider.setY(screenY);
	}

}
