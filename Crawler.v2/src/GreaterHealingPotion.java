
public class GreaterHealingPotion extends Item {
	private String name = "Greater Healing Potion";
	private int value = 20;
	private String type = "useable";
	private int healing = 10;

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
