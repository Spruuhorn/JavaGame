package scenes;

import java.util.Collections;
import java.util.ListIterator;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import managers.AssetManager;
import managers.UIManager;
import parents.*;
import utilities.Debug;
import utilities.Inputs;
import utilities.Time;
import utilities.zComparator;
import other.MapLoader;
import other.MouseHandler;

public class MainWorld extends BasicGameState {
	
	public Graphics graphics;
	
	public MainWorld(int state) {}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
		MapLoader.currentActiveMap = MapLoader.maps.get("testMap1");
		MapLoader.loadMap("testMap1");
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		
		for(Sprite bg : Sprite.background) {
			if(bg.isActive()) {
				bg.draw();
			}
		}
		
		Collections.sort(AssetManager.sprites, new zComparator());
		
		for (Sprite sprite : AssetManager.sprites) {
			if(sprite.isActive()) {
				sprite.draw();
			}
		}
		
		
		// Used for Debugging purposes
		
		if(Debug.drawHitBox) {
			for(PhysicalObject object : AssetManager.physicalObjects) {
				
				if(object.isActive()) {
				
					g.draw(object.getHitBox());
				}
			}
		}
		
		for(UIElement ui : UIManager.elements) {
			ui.draw(g);
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
		ListIterator<PhysicalObject> otherPhysicalObjectIterator = AssetManager.physicalObjects.listIterator();
		ListIterator<PhysicalObject> anotherPhysicalObjectIterator = AssetManager.physicalObjects.listIterator();

		while(physicalObjectIterator.hasNext()) {
			
			PhysicalObject object = physicalObjectIterator.next();
			
			if(object.isActive()) {
				
				object.applyMovement();
				
				object.updateColliders();
				
				object.isSomethingOnMyVerticals(otherPhysicalObjectIterator);
				
				object.isSomethingOnMyHorizontals(anotherPhysicalObjectIterator);
				
			}
				
		}
		
		ListIterator<PhysicalObject> thirdPhysicalObjectIterator = AssetManager.physicalObjects.listIterator();
		ListIterator<PhysicalObject> fourthPhysicalObjectIterator = AssetManager.physicalObjects.listIterator();
		
		while(thirdPhysicalObjectIterator.hasNext()) {
			
			PhysicalObject object = thirdPhysicalObjectIterator.next();
			
			if(object.isActive()) {
				
				while(fourthPhysicalObjectIterator.hasNext()) {
					
					PhysicalObject otherObject = fourthPhysicalObjectIterator.next();
					
					if(object.equals(otherObject)) {
						continue;
					}
					
					if(!otherObject.isActive()) {
						continue;
					}
					
					if(object.intersects(otherObject)) {
						object.onCollision(otherObject);
					}
					
				}
				
			}
				
		}
		
		ListIterator<GameObject> objectIterator = AssetManager.gameObjects.listIterator();
		
		while(objectIterator.hasNext()) {
			
			GameObject object = objectIterator.next();
			
			if(object.isActive()) {
			
				object.update();
			
			}
		}
		
		Inputs.update();
		MouseHandler.update();
		Time.passTime();
	}

	@Override
	public int getID() {
		
		return 1;
		
	}
	
}
