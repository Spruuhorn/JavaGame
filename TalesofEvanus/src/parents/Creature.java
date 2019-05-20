package parents;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import combat.Attack;
import combat.PerformableAttack;
import other.Transform;
import ui.PlainText;
import utilities.Time;

public class Creature extends PhysicalObject {
	
	protected int health;
	protected float speed;
	protected int xp;
	protected int damage;
	protected float moveSpeed;
	
	protected Creature target;
	protected ArrayList<Attack> attacks;
	protected Attack selectedAttack;
	
	private boolean endTurn;
	private float pointsToNextTurn;

	public Creature(int health, float moveSpeed, int tag, String animation, String name, Transform transform) {
		super(tag, animation, name, transform);
		this.health = health;
		this.moveSpeed = moveSpeed;
		this.endTurn = false;
	}
	
	public Creature(int health, float moveSpeed, String animation, String name, Transform transform) {
		super(animation, name, transform);
		this.tag = PhysicalObject.CREATURE;
		this.health = health;
		this.moveSpeed = moveSpeed;
		this.endTurn = false;
	}
	
	public Creature(int health, float moveSpeed, String animation, String name, Transform transform, int[] points) {
		super(animation, name, transform, points);
		this.tag = PhysicalObject.CREATURE;
		this.health = health;
		this.moveSpeed = moveSpeed;
		this.endTurn = false;
	}
	
	//For passing in a JSON array with the name of the creature
	public Creature(String name, Transform transform) {
		super(name, transform);
		this.tag = PhysicalObject.CREATURE;
		this.endTurn = false;
		
		parseEntityData(name);
	}
	
	public void executeTurn() {
		
		pointsToNextTurn = 0;
		
		if(target != null) {
			System.out.println(this.getName() + " attacked " + target.getName());
			selectedAttack.getAttack().attack(this, target);
		} else {
			System.out.println(this.getName() + " did not hit anything.");
		}
		
		endTurn = true;
	}
	
	public int getHealth() {
		return health;
	}
	
	public float getSpeed() {
		return speed;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public boolean getEndTurn() {
		return endTurn;
	}
	
	public void setEndTurn(boolean a) {
		endTurn = a;
	}
	
	public void incrementTurn() {
		pointsToNextTurn += speed;
	}
	
	public float getPointsToNextTurn() {
		return pointsToNextTurn;
	}
	
	public void setTarget(Creature target) {
		this.target = target;
	}
	
	public void takeDamage(int damage) {
		new PlainText(String.valueOf(damage), this.getTransform().screenSpace.x + this.getAnimation().getWidth()/2, this.getTransform().screenSpace.y - this.getAnimation().getHeight()/2);
		health -= damage;
	}
	
	public void setSelectedAttack(Attack attack) {
		selectedAttack = attack;
	}
	
	private void parseEntityData(String name) {
		
		JSONParser parser = new JSONParser();
		
		try {
			//Retrieve stats from JSON file using name
			JSONObject enemyData = (JSONObject) parser.parse(new FileReader("EntityData/Enemy_Data.json"));
			
			JSONArray enemies = (JSONArray) enemyData.get("Enemies");
			
			for(Object enemy : enemies) {
				
				if(enemy instanceof JSONObject) {
					
					JSONObject jsonEnemy = (JSONObject) enemy;
					
					if(jsonEnemy.get("Name").equals(name)) {
						
						setAnimation((String) jsonEnemy.get("Sprite"));
						
						this.health = Integer.parseInt( (String) jsonEnemy.get("Health") );
						this.speed  = Integer.parseInt( (String) jsonEnemy.get("Speed")  );
						this.damage = Integer.parseInt( (String) jsonEnemy.get("Attack") );
						this.xp     = Integer.parseInt( (String) jsonEnemy.get("XP")     );
					}
					
				}
				
			}
			
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
