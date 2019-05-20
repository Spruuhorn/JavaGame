import java.util.ArrayList;
import java.util.Random;

public class Drunkard extends Enemy {

	private String name = "Drunkard";
	private int hp = 8;
	private int att = 3;
	private int exp = 5;

	private ArrayList<Item> ItemDrops = new ArrayList<Item>();

	Drunkard() {
		Random rhp = new Random();
		Random rdmg = new Random();
		
		hp = rhp.nextInt(20) + 10;
		att = rdmg.nextInt(5);
		exp = (int) ((0.3 * hp) + (0.2 * att));
		
		if(hp == 30) {
			name = "Thicc Drunkard";
		} else if(hp >= 20) {
			name = "Burly Drunkard";
		} else if(hp >= 10) {
			name = "Middling Drunkard";
		} else {
			name = "Meatless Drunkard";
		}
		
		ItemDrops.add(new Beer());
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
