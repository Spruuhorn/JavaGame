
public class FinalBoss extends Enemy {
	// Is actually not a troll
	private String name = "The Dragoon";
	private int hp = 200;
	private int att = 50;
	private int exp = 999;

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

}
