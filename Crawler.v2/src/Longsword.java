
public class Longsword extends Item {
	private boolean IsEquipped = false;
	
	private int ID = 14;

	private String name = "Longsword";
	private String type = "weapon";
	private int dmg = 14;
	private int minDmg = 10;
	private int value = 120;

	String getName() {
		return name;
	}

	String getType() {
		return type;
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

	boolean getIsEquipped() {
		return IsEquipped;
	}

	void setIsEquipped(boolean a) {
		IsEquipped = a;
	}
	
	int getID() {
		return ID;
	}
}
