import java.util.*;

public class Troll extends Enemy {
	// Is actually not a troll
	private String name = "Troll";
	private int hp = 100;
	private int att = 30;
	private int exp = 60;

	private ArrayList<Item> ItemDrops = new ArrayList<Item>();

	Troll() {
		for(int i = 0; i < 20; i++) {
			ItemDrops.add(new Fur());
		}
		ItemDrops.add(new Club());
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
