package gui;

import entities.Camera;
import entities.GameObject;
import entities.Item;
import interfaces.Clickable;
import interfaces.Drawable;

public class InventoryGUI {
	
	private static final int padding = 60;
	private static final int slots = 30;
	private static final int rows = 6;
	private static final int cols = 5;
	
	private class Slot extends GameObject implements Drawable, Clickable {

		public Slot(int x, int y, boolean hitbox, String sprite) {
			super(x, y, hitbox, sprite);
		}
		
		private Item item;
		
		@Override
		public void onClick() {
			System.out.println("Item in slot: " + item);
		}

		@Override
		public void onDraw() {
			int screenX = x - Camera.MAIN_CAMERA.getX() + Camera.MAIN_CAMERA.getWidth()/2 - sprite.getWidth()/2;
			int screenY = y - Camera.MAIN_CAMERA.getY() + Camera.MAIN_CAMERA.getHeight()/2 - sprite.getHeight()/2;
			sprite.draw(screenX, screenY);
			collider.setX(screenX);
			collider.setY(screenY);
		}

		@Override
		public void update() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public InventoryGUI(int x, int y) {
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < cols; col++) {
				Slot slot = new Slot(row * padding, col * padding, true, "slot");
			}
		}
	}
}
