import java.util.ArrayList;
import java.util.Random;

public class Snail extends Enemy {

	private String name = "Snail";
	private int hp = 30;
	private int att = 7;
	private int exp = 15;

	private ArrayList<Item> ItemDrops = new ArrayList<Item>();

	Snail() {
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
