package com.tosco.toe;

public class InventoryManager {
	
	private static TestSlotItem selected;
	private static InventoryHUD.Slot lastSlot;
	
	
	public static TestSlotItem getSelected() {
		return selected;
	}
	
	public static void setSelected(TestSlotItem s) {
		selected = s;
	}
	
	public static void setLastSlot(InventoryHUD.Slot s) {
		lastSlot = s;
	}
}
