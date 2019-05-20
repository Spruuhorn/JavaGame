package scenes;
import java.util.ArrayList;
import java.util.ListIterator;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import managers.AssetManager;
import other.MouseHandler;
import other.Transform;
import parents.GameObject;
import parents.PhysicalObject;
import parents.Sprite;
import utilities.Time;

public class DragWorld extends BasicGameState {
	
	public static ArrayList<Shape> slots = new ArrayList<>();
	
	public DragWorld(int state) {}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		GameObject test = new PhysicalObject("Wall", new Transform(100, 100));
		
		for(int y = 0; y < 6; y++) {
			for(int x = 0; x < 4; x++) {
				Shape slot = new Rectangle((x * 64) + 100, (y * 64) + 100, 64, 64);
				slots.add(slot);
			}
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		for (Sprite sprite : AssetManager.sprites) {
			if(sprite.isActive()) {
				sprite.draw();
			}
		}
		
		for(Shape slot : slots) {
			g.draw(slot);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int num) throws SlickException {
		
		/*
		 * Order of game loop:
		 * 1. Update all game objects (update())
		 * 2. Update all colliders (their position)
		 * 3. Apply movement and physics to all objects
		 */
		
		ListIterator<PhysicalObject> physicalObjectIterator = AssetManager.physicalObjects.listIterator();

		while(physicalObjectIterator.hasNext()) {
			
			PhysicalObject object = physicalObjectIterator.next();
			
			if(object.isActive()) {
				
				object.applyMovement();
				
				object.updateColliders();
				
			}
				
		}
		
		ListIterator<GameObject> objectIterator = AssetManager.gameObjects.listIterator();
		
		while(objectIterator.hasNext()) {
			
			GameObject object = objectIterator.next();
			
			if(object.isActive()) {
			
				object.update();
			
			}
		}
		
		MouseHandler.update();
		Time.passTime();
	}

	@Override
	public int getID() {
		
		return 3;
		
	}
	
}
