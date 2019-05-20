import java.util.ArrayList;
import java.util.Random;

public class Necromancer extends Enemy {

	private String name = "Necromancer";
	private int hp = 35;
	private int att = 12;
	private int exp = 24;

	private ArrayList<Item> ItemDrops = new ArrayList<Item>();

	Necromancer() {
		for(int i = 0; i < 50; i++) {
			ItemDrops.add(new Bones());
		}
		for(int i = 0; i < 10; i++) {
			ItemDrops.add(new HealingPotion());
		}
		ItemDrops.add(new GreaterHealingPotion());
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
