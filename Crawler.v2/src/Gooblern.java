import java.util.ArrayList;
import java.util.Random;

public class Gooblern extends Enemy {

	static boolean alive = true;

	private String name = "Gooblern";
	private int hp = 50;
	private int att = 8;
	private int exp = 25;
	
	private ArrayList<Item> ItemDrops = new ArrayList<Item>();
	
	Gooblern() {
		ItemDrops.add(new GooblernsCoin());
	}

	public String getName() {
		return name;
	}

	public int getHP() {
		return hp;
	}

	public void setHP(int a) {
		hp -= a;
	}

	public int getAtt() {
		return att;
	}

	int getEXP() {
		return exp;
	}

	void setAlive(boolean a) {
		alive = a;
	}
}
