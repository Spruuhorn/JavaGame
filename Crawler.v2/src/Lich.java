import java.util.ArrayList;
import java.util.Random;

public class Lich extends Enemy {

	private String name = "Lich";
	private int hp = 50;
	private int att = 15;
	private int exp = 35;

	private ArrayList<Item> ItemDrops = new ArrayList<Item>();

	Lich() {
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
