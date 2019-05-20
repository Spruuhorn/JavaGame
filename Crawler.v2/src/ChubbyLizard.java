import java.util.ArrayList;
import java.util.Random;

public class ChubbyLizard extends Enemy {
	// Is actually not a ChubbyLizard
	private String name = "Chubby Lizard";
	private int hp = 50;
	private int att = 17;
	private int exp = 30;

	private ArrayList<Item> ItemDrops = new ArrayList<Item>();

	ChubbyLizard() {
		ItemDrops.add(new ChubbyScales());
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
