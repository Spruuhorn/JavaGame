
public class Cloth extends Item {
	
	private int ID = 213;
	
	private String name = "Cloth";
	private int value = 9;
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
