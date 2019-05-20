import java.util.ArrayList;
import java.util.Random;

public class PiranhaPlant extends Enemy {

	private String name = "Piranha Plant";
	private int hp = 18;
	private int att = 5;
	private int exp = 10;

	private ArrayList<Item> ItemDrops = new ArrayList<Item>();

	PiranhaPlant() {
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
