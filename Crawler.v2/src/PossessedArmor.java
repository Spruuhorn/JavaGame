import java.util.ArrayList;
import java.util.Random;

public class PossessedArmor extends Enemy {

	private String name = "Possessed Armor";
	private int hp = 30;
	private int att = 9;
	private int exp = 18;

	private ArrayList<Item> ItemDrops = new ArrayList<Item>();

	PossessedArmor() {
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
