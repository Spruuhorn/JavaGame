package gui;

import org.newdawn.slick.Image;

import entities.Item;
import interfaces.Clickable;
import managers.AssetManager;

public class InventoryGUI {
	
	private static final float padding = 15;
	private static final int slots = 30;
	private static final int rows = 6;
	private static final int cols = 5;
	
	private class Slot implements Clickable {

		private final Image frame = (Image) AssetManager.sprites.get("slot");
		private Item item;
		
		@Override
		public void onClick() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public InventoryGUI() {
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < cols; col++) {
				
				//int xo = (int) ((x - (cols * sliceWidth)/2) + (col * sliceWidth) + sliceWidth/2);
				//int yo = (int) ((y - (rows * sliceHeight)/2) + (row * sliceHeight) + sliceHeight/2);
				
				
			}
		}
	}
	
	public InventoryGUI(int x, int y) {
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < cols; col++) {
				Slot slot = new Slot();
			}
		}
	}
}
