package combat;

import java.util.Random;

import parents.Creature;

public class Attack {
	
	private static Random random;
	
	static {
		random = new Random();
	}
	
	private String name;
	private String description;
	private int baseDamage;
	
	private PerformableAttack attack;
	
	public Attack(String name) {
		switch(name) {
		case "BasicAttack": this.attack = new BasicAttack(); 
		break;
		case "QuickAttack": this.attack = new QuickAttack(); 
		break;
		}
	}
	
	public PerformableAttack getAttack() {
		return attack;
	}
	
	public String getName() {
		return name;
	}
	
	private class BasicAttack implements PerformableAttack {
		
		public BasicAttack() {
			Attack.this.name = "Basic Attack";
			Attack.this.baseDamage = 1;
		}
		
		@Override
		public void attack(Creature attacker, Creature victim) {
			
			victim.takeDamage(baseDamage + random.nextInt(attacker.getDamage()));
			
		}
		
	}
	
	private class QuickAttack implements PerformableAttack {

		public QuickAttack() {
			Attack.this.name = "Quick Attack";
		}
		
		@Override
		public void attack(Creature attacker, Creature victim) {
			victim.takeDamage(baseDamage);
			
		}
		
	}
	
}
