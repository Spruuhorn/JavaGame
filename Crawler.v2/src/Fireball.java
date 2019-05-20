
public class Fireball extends Item {
	private boolean IsEquipped = false;

	private String name = "Book of FireBolt";
	private int dmg = 8;
	private int minDmg = 1;
	private int value = 20;
	private String type = "magic";
	private int cooldown = 3;
	private int ready = 3;

	String getName() {
		return name;
	}

	int getDMG() {
		return dmg;
	}

	int getMinDMG() {
		return minDmg;
	}

	int getValue() {
		return value;
	}

	String getType() {
		return type;
	}

	boolean getIsEquipped() {
		return IsEquipped;
	}

	void setIsEquipped(boolean a) {
		IsEquipped = a;
	}

	int getCooldown() {
		return cooldown;
	}

	int getReady() {
		return ready;
	}

	void setCooldown(int a) {
		cooldown += a;
	}
}
