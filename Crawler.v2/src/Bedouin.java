import java.util.ArrayList;
import java.util.Random;

public class Bedouin extends Enemy {

	private String name = "Bedouin";
	private int hp = 50;
	private int att = 17;
	private int exp = 30;

	private ArrayList<Item> ItemDrops = new ArrayList<Item>();

	Bedouin() {
		ItemDrops.add(new Cloth());
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
