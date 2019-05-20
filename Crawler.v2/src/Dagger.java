
public class Dagger extends Item {
	private boolean IsEquipped = false;
	
	private int ID = 11;

	private String name = "Dagger";
	private String type = "weapon";
	private int dmg = 5;
	private int minDmg = 3;
	private int value = 10;

	public Dagger() {
		setName(name);
		setDMG(dmg);
		setMinDMG(minDmg);
		setValue(value);
	}
	
	public Dagger(String name, int MaxDMG, int MinDMG, int value) {
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
