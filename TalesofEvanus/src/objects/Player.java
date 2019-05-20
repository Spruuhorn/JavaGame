package objects;

import java.util.ArrayList;

import org.newdawn.slick.Input;

import combat.Attack;
import combat.PerformableAttack;
import other.MapLoader;
import other.MouseHandler;
import other.Transform;
import parents.Creature;
import parents.PhysicalObject;
import utilities.Inputs;
import utilities.Time;

public class Player extends Creature {
	
	public static boolean inCombat;
	
	static {
		inCombat = false;
	}
	
	private Input input;
	
	private float lastHorz = -1;
	
	{
		attacks = new ArrayList<>();
	}
	
	public Player(int health, float speed, String animation, String name, Transform transform, int[] points) {
		super(health, speed, animation, name, transform, points);
		this.input = Inputs.input;
		this.speed = 5;
		this.damage = 4;
		
		attacks.add(new Attack("BasicAttack"));
		attacks.add(new Attack("QuickAttack"));
	}
	
	@Override
	public void update() {
		
		if(!inCombat) {
			float horizontal = Inputs.getHorizontalInputs();
			float vertical = Inputs.getVerticalInputs();
			
			float move = moveSpeed;
			
			if(horizontal != 0 && vertical != 0) {
				move = (float) ((Math.sin(Math.PI/4)) *  moveSpeed);
			}
			
			velocity.x = horizontal * move;
			velocity.y = vertical * move;
			
			if(horizontal == 1) {
				setAnimation("PlayerRunRight");
				lastHorz = 1;
			} else if(horizontal == -1) {
				setAnimation("PlayerRunLeft");
				lastHorz = -1;
			} else {
				if(lastHorz == 1) {
					setAnimation("PlayerIdleRight");
				} else if(lastHorz == -1) {
					setAnimation("PlayerIdleLeft");
				}
			}
		}
		
	}
	
	/* Update method in combat phase */
	@Override
	public void executeTurn() {
		
		if(selectedAttack != null) {
			
			if(MouseHandler.selected instanceof Creature) {
				target = (Creature) MouseHandler.selected;
				
				super.executeTurn();
				
				target = null;
				selectedAttack = null;
			}
		}
		
	}
	
	@Override
	public void onCollision(PhysicalObject other) {
		
		if(other.getTag() == PhysicalObject.PORTAL && input.isKeyPressed(Input.KEY_SPACE)) {
			MapLoader.loadMap(((Portal) other));
		}
	}
	
	public ArrayList<Attack> getAttacks() {
		return attacks;
	}
}
