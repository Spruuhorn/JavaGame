import java.util.ArrayList;
import java.util.Random;

public class MartialMonkey extends Enemy {

	private String name = "Martial Monkey";
	private int hp = 12;
	private int att = 3;
	private int exp = 6;

	private ArrayList<Item> ItemDrops = new ArrayList<Item>();

	MartialMonkey() {
		for(int i = 0; i < 20; i++) {
			ItemDrops.add(new Fur());
		}
		ItemDrops.add(new Quarterstaff());
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
