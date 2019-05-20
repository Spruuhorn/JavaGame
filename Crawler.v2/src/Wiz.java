import java.util.ArrayList;
import java.util.Random;

public class Wiz extends Enemy {

	static boolean alive = true;

	private String name = "Wiz";
	private int hp = 40;
	private int att = 12;
	private int exp = 50;
	
	private ArrayList<Item> ItemDrops = new ArrayList<Item>();

	ArrayList<Item> inventory = new ArrayList<Item>();
	
	Wiz() {
		ItemDrops.add(new Quarterstaff("Wiz's Walking Stick", 10, 5, 45));
	}


	String[] welcomeTXT = { "Greetings...", "I sense you are wise...", "Have you come to gather wisdom?" };

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

	void inventory() {
		inventory.add(new Fireball());
	}

	String getWelcome() {
		Random r = new Random();
		return welcomeTXT[r.nextInt(welcomeTXT.length)];
	}
}
