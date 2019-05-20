import java.util.ArrayList;
import java.util.Random;

public class Scorpion extends Enemy {

	private String name = "Scorpion";
	private int hp = 40;
	private int att = 14;
	private int exp = 27;

	private ArrayList<Item> ItemDrops = new ArrayList<Item>();

	Scorpion() {
		ItemDrops.add(new MetalPlate());
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
