import java.util.ArrayList;
import java.util.Random;

public class Skeleton extends Enemy {

	private String name = "Skeleton";
	private int hp = 25;
	private int att = 7;
	private int exp = 14;

	private ArrayList<Item> ItemDrops = new ArrayList<Item>();

	Skeleton() {
		for(int i = 0; i < 50; i++) {
			ItemDrops.add(new Bones());
		}
		for(int i = 0; i < 10; i++) {
			ItemDrops.add(new Shortsword());
		}
		ItemDrops.add(new Longsword());
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
