
public class GooblernsCoin extends Item {
	
	private int ID = 29;
	
	private String name = "Gooblern's Coin";
	private int value = 50;
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
