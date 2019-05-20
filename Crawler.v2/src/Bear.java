import java.util.*;

public class Bear extends Enemy {

	private String name = "Bear";
	private int hp = 8;
	private int att = 2;
	private int exp = 4;

	private ArrayList<Item> ItemDrops = new ArrayList<Item>();

	Bear() {
		ItemDrops.add(new Fur());
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
