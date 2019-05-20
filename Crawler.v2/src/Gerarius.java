import java.util.ArrayList;
import java.util.Random;

public class Gerarius extends Enemy {

	static boolean alive = true;

	private String name = "Gerarius";
	private int hp = 35;
	private int att = 10;
	private int exp = 25;
	
	private ArrayList<Item> ItemDrops = new ArrayList<Item>();
	
	Gerarius() {
		ItemDrops.add(new Dagger("Gerarius's Dirty Dagger", 7, 5, 30));
	}

	ArrayList<Item> inventory = new ArrayList<Item>();

	String[] welcomeTXT = { "Of Course! See anything you like? All of my finest weaponry is made by me." };

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
		inventory.add(new Dagger());
		inventory.add(new Shortsword());
		inventory.add(new Quarterstaff());
		inventory.add(new Longsword());
		inventory.add(new Halbard());
		inventory.add(new Spear());
		inventory.add(new Axe());
		inventory.add(new Club());
		inventory.add(new Mace());
		inventory.add(new Katana());
		inventory.add(new Glaive());
		inventory.add(new BattleAxe());
	}

	String getWelcome() {
		Random r = new Random();
		return welcomeTXT[r.nextInt(welcomeTXT.length)];
	}
}
