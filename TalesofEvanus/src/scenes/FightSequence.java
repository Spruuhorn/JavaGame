package scenes;
import java.util.ArrayList;
import java.util.ListIterator;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import combat.Attack;
import interfaces.OnClick;
import managers.AssetManager;
import managers.UIManager;
import objects.Player;
import other.MouseHandler;
import other.Transform;
import other.Vector2;
import parents.Creature;
import parents.PhysicalObject;
import parents.UIElement;
import ui.Button;
import utilities.Debug;
import utilities.Inputs;
import utilities.Time;

public class FightSequence extends BasicGameState {
	
	public FightSequence(int state) {}
	
	/*
	 * A bunch of hardcoded points to place combatants (Will be fixed later)
	 */
	private final static Vector2 playerPlacement = new Vector2(0,128);
	private final static Vector2 pos1 = new Vector2(0, -128);
	private final static Vector2 pos3 = new Vector2(0, -196);
	private final static Vector2 pos1a = new Vector2(-64, -128);
	private final static Vector2 pos2 = new Vector2(64, -128);
	private final static Vector2 pos3a = new Vector2(-64, -192);
	private final static Vector2 pos4 = new Vector2(64, -192);
	
	private static Vector2[] positions = {
			pos1a, pos2, pos3a, pos4
	};
	
	
	private static Creature actingCreature;
	private static ArrayList<Creature> creaturesInBattle;
	
	private static Image fightBackground;
	
	private float maxSpeedPoints = 10;
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
		creaturesInBattle = new ArrayList<>();

		generateFight("Test", 3);
		
		int counter = 0;
		for(Attack attack : AssetManager.player.getAttacks()) {
			Button button = new Button(attack.getName(), (-Launcher.width/2) + 16, counter * 96);
			button.addOnClick(new AttackAction(attack));
			counter++;
		}
		
		actingCreature = determineFirstMove();
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		//fightBackground.draw(0, 0);
		
		for(Creature creature : creaturesInBattle) {
			creature.draw();
			
			g.drawString("Health: " + creature.getHealth(), creature.getTransform().screenSpace.x, creature.getTransform().screenSpace.y + creature.getAnimation().getHeight());
		}
		
		for(UIElement ui : UIManager.elements) {
			ui.draw(g);
		}
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int num) throws SlickException {
		
		if(!actingCreature.getEndTurn()) {
			
			actingCreature.executeTurn();
			
		} else {
			
			for(Creature creature : creaturesInBattle) {
				
				if(creature.getHealth() > 0) {
					creature.incrementTurn();
					
					if(creature.getPointsToNextTurn() >= maxSpeedPoints) {
						
						creature.setEndTurn(false);
						actingCreature = creature;
						
					}
				}
				
			}
			
		}
		
		ListIterator<PhysicalObject> physicalObjectIterator = AssetManager.physicalObjects.listIterator();

		while(physicalObjectIterator.hasNext()) {
			
			PhysicalObject object = physicalObjectIterator.next();
			
			if(object.isActive()) {
				
				object.applyMovement();
				
				object.updateColliders();
				
			}
				
		}
		
		Inputs.update();
		Time.passTime();
		MouseHandler.update();
	}

	@Override
	public int getID() {
		
		return 2;
		
	}
	
	private void doMath() {
		
	}
	
	public static void generateFight(String area, int numberOfEnemies) {
		
		for(int i = 0; i < numberOfEnemies; i++) {
			Creature bat = new Creature("Bat", new Transform(0,0));
			bat.setTarget(AssetManager.player);
			bat.setSelectedAttack(new Attack("BasicAttack"));
			creaturesInBattle.add(bat);
		}
		
		if(creaturesInBattle.size() == 1) {
			positions[0] = pos1;
		} else if (creaturesInBattle.size() == 3) {
			positions[2] = pos3;
		}
		
		int counter = 0;
		for(Creature enemy : creaturesInBattle) {
			enemy.getTransform().setWorldSpace(positions[counter]);
			counter++;
		}
		
		positions[0] = pos1a;
		positions[2] = pos3a;
		
		AssetManager.player.getTransform().setWorldSpace(playerPlacement);
		creaturesInBattle.add((Creature) AssetManager.player); 
		
	}
	
	private Creature determineFirstMove() {
		
		Creature fastestCreature = creaturesInBattle.get(0);
		float fastestSpeed = fastestCreature.getSpeed();
		
		for(Creature creature : creaturesInBattle) {
			if(creature.getSpeed() > fastestSpeed) {
				fastestSpeed = creature.getSpeed();
				fastestCreature = creature;
			}
		}
		
		return fastestCreature;
	}
	
	private class AttackAction implements OnClick {

		Attack attack;
		
		public AttackAction(Attack attack) {
			this.attack = attack;
		}
		
		@Override
		public void onClick() {
			
			AssetManager.player.setSelectedAttack(attack);
			
		}
		
	}
	
}
