import java.util.ArrayList;
import java.util.Random;

public class Mimic extends Enemy {

	private String name = "Mimic";
	private int hp = 30;
	private int att = 9;
	private int exp = 18;

	private ArrayList<Item> ItemDrops = new ArrayList<Item>();

	Mimic() {
		for(int i = 0; i < 16; i++) {
			ItemDrops.add(new MetalLock());
		}
		ItemDrops.add(new Dagger());
		ItemDrops.add(new Shortsword());
		ItemDrops.add(new Longsword());
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
