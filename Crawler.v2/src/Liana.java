import java.util.ArrayList;
import java.util.Random;

public class Liana extends Enemy {

	private String name = "Liana";
	private int hp = 12;
	private int att = 3;
	private int exp = 6;

	private ArrayList<Item> ItemDrops = new ArrayList<Item>();

	Liana() {
		ItemDrops.add(new Vine());
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

	public int getEXP() {
		return exp;
	}

	public Item getItemDrops() {
		Random r = new Random();

		return ItemDrops.get(r.nextInt(ItemDrops.size()));
	}

}
