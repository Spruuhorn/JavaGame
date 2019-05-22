package gui;

import entities.Item;
import interfaces.Clickable;

public class InventoryGUI {
	
	private static final int padding = 8;
	
	private class Slot implements Clickable {

		private Item item;
		
		@Override
		public void onClick() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public InventoryGUI(int x, int y, int rows, int cols) {
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < cols; col++) {
				
			}
		}
	}
}
