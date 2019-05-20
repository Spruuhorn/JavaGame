
public class Shortsword extends Item {
	private boolean IsEquipped = false;
	
	private int ID = 12;

	private String name = "Shortsword";
	private String type = "weapon";
	private int dmg = 5;
	private int minDmg = 7;
	private int value = 50;

	public Shortsword() {
		setName(name);
		setDMG(dmg);
		setMinDMG(minDmg);
		setValue(value);
	}
	
	//Unique Items
	public Shortsword(String name, int MaxDMG, int MinDMG, int value) {
		setName(name);
		setDMG(MaxDMG);
		setMinDMG(MinDMG);
		setValue(value);
	}

	String getName() {
		return name;
	}

	String getType() {
		return type;
	}

	void setName(String a) {
		name = a;
	}

	int getDMG() {
		return dmg;
	}

	void setDMG(int a) {
		dmg = a;
	}

	int getMinDMG() {
		return minDmg;
	}

	void setMinDMG(int a) {
		minDmg = a;
	}

	int getValue() {
		return value;
	}

	void setValue(int a) {
		value = a;
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
