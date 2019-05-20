import java.util.ArrayList;
import java.util.Random;

public class Spirit extends Enemy {

	private String name = "Spirit";
	private int hp = 35;
	private int att = 12;
	private int exp = 24;

	private ArrayList<Item> ItemDrops = new ArrayList<Item>();

	Spirit() {
		for(int i = 0; i < 99; i++) {
			ItemDrops.add(new StickyGoo());
		}
		ItemDrops.add(new Scythe());

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
