import java.util.*;

public class Player {
	//changed all to static
	static String location = "town";
	static String state = "free";

	private static int hp = 10;
	private static int maxHP = 10;

	static int gold = 20;

	private static int exp = 0;
	private static int maxEXP = 15;
	private static int lvl = 1;

	private static int strength = 1;
	private static int agility = 1;
	private static int intelligence = 1;

	private static int dmg = 0;
	private static int minDmg = 0;

	private static int MagDmg = 0;
	private static int MinMagDmg = 0;

	static ArrayList<Item> inventory = new ArrayList<Item>();

	int getHP() {
		return hp;
	}

	void setHP(int a) {
		hp += a;
		if (hp > maxHP) {
			hp = maxHP;
		}
	}
	
	void LOADHP(int a) {
		hp = a;
	}

	int getMaxHP() {
		return maxHP;
	}
	
	void setMaxHP(int a) {
		maxHP += a;
	}
	
	void LOADMaxHP(int a) {
		maxHP = a;
	}

	int getLevel() {
		return lvl;
	}
	
	void setLevel(int a) {
		lvl = a;
	}

	int getEXP() {
		return exp;
	}
	
	void setEXP(int a) {
		exp = a;
	}

	int getMaxEXP() {
		return maxEXP;
	}
	
	void setMaxEXP(int a) {
		maxEXP = a;
	}

	int getDMG() {
		return dmg;
	}

	int getMinDMG() {
		return minDmg;
	}

	void setDMG(int a) {
		dmg = a + getSTR()/2;
	}
	
	void LOADDMG(int a) {
		dmg = a;
	}

	void setMinDMG(int a) {
		minDmg = a;
	}

	int getMagDMG() {
		return MagDmg;
	}

	int getMagMinDMG() {
		return MinMagDmg;
	}

	void setMagDMG(int a) {
		MagDmg = a + getINT()/3;
	}
	
	void LOADMagDMG(int a) {
		MagDmg = a;
	}

	void setMinMagDMG(int a) {
		MinMagDmg = a;
	}

	int attack(int max, int min) {
		Random rand = new Random();
		int damage = rand.nextInt(max) + min;
		return damage;
	}

	int PlayerTakesDamage(int a) {
		Random rand = new Random();
		int damage = rand.nextInt(a) + 1;
		hp -= damage;
		return damage;
	}

	boolean leveling(int a) {
		exp += a;
		if (exp >= maxEXP) {
			lvl++;
			int overEXP = exp - maxEXP;
			exp = 0;
			exp += overEXP;
			maxEXP += 5;

			maxHP += 2;
			hp = maxHP;

			return true;
		}

		return false;
	}

	int getSTR() {
		return strength;
	}

	int getAGL() {
		return agility;
	}

	int getINT() {
		return intelligence;
	}

	void setSTR(int a) {
		strength += a;
	}

	void setAGL(int a) {
		agility += a;
	}

	void setINT(int a) {
		intelligence += a;
	}
	
	void LOADSTR(int a) {
		strength = a;
	}

	void LOADAGL(int a) {
		agility = a;
	}

	void LOADINT(int a) {
		intelligence = a;
	}

	Item getCurrentSpell() {
		Item spell = new Item();
		for (int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i).getIsEquipped() && inventory.get(i).getType().equalsIgnoreCase("magic")) {
				return inventory.get(i);
			}
		}
		return spell;
	}

	void casted() {
		getCurrentSpell().setCooldown(-getCurrentSpell().getCooldown());
	}

}
