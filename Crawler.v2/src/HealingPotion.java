
public class HealingPotion extends Item {
	
	private String name = "Healing Potion";
	private int value = 10;
	private String type = "useable";
	private int healing = 5;

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
