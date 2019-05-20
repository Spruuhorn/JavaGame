import java.util.ArrayList;
import java.util.Random;

public class Slime extends Enemy {

	private String name = "Slime";
	private int hp = 25;
	private int att = 7;
	private int exp = 14;

	private ArrayList<Item> ItemDrops = new ArrayList<Item>();

	Slime() {
		ItemDrops.add(new StickyGoo());
		ItemDrops.add(new StickyGoo());
		ItemDrops.add(new StickierGoo());
		ItemDrops.add(new Dagger());
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
