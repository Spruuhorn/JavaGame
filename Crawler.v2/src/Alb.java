import java.util.*;

public class Alb extends Enemy {

	static boolean alive = true;

	private String name = "Durning";
	private int hp = 30;
	private int att = 6;
	private int exp = 15;
	
	private ArrayList<Item> ItemDrops = new ArrayList<Item>();

	ArrayList<Item> inventory = new ArrayList<Item>();
	
	Alb() {
		ItemDrops.add(new Shortsword());
	}

	String[] welcomeTXT = {
			"You wanna buy something?! - Great! - Wonderful! I can't wait to show you what I got! Hopefully it doesn't kill you!" };

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
		inventory.add(new HealingPotion());
		inventory.add(new GreaterHealingPotion());
	}

	String getWelcome() {
		Random r = new Random();
		return welcomeTXT[r.nextInt(welcomeTXT.length)];
	}
}
