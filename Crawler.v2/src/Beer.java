
public class Beer extends Item {
	private String name = "Beer";
	private int value = 8;
	private String type = "useable";
	private int healing = 3;

	String getType() {
		return type;
	}

	String getName() {
		return name;
	}

	int getValue() {
		return value;
	}

	int getHealing() {
		return healing;
	}
}
