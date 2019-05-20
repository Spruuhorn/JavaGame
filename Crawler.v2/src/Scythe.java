
public class Scythe extends Item {
	private boolean IsEquipped = false;
	
	private int ID = 114;

	private String name = "Scythe";
	private String type = "weapon";
	private int dmg = 37;
	private int minDmg = 13;
	private int value = 300;

	public Scythe() {
		setName(name);
		setDMG(dmg);
		setMinDMG(minDmg);
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
	
	void LOADIsEquipped(int a) {
		if(a == 1) {
			setIsEquipped(true);
		} else {
			setIsEquipped(false);
		}
	}
	
	int getID() {
		return ID;
	}
}
