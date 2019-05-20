
public class StickyGoo extends Item {
	
	private int ID = 25;
	
	private String name = "Sticky Goo";
	private int value = 2;
	private String type = "common";

	String getName() {
		return name;
	}

	int getValue() {
		return value;
	}

	String getType() {
		return type;
	}
	
	int getID() {
		return ID;
	}
}
