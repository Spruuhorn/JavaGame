package combat;

import parents.Creature;

public interface PerformableAttack {
	public void attack(Creature attacker, Creature victim);
}
