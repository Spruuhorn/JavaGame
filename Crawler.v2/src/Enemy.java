public class Enemy {

	private boolean alive;
	private String name;
	private int hp;
	private int att;
	private String drop;
	private int exp;

	String getName() {
		return name;
	}

	int getHP() {
		return hp;
	}

	void setHP(int a) {
		hp -= a;
	}

	int getAtt() {
		return att;
	}

	int getEXP() {
		return exp;
	}

	String dropItem() {
		return drop;
	}

	void setAlive(boolean a) {
		alive = a;
	}

	public Item getItemDrops() {
		return new Item();
	}
}
