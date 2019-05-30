package scene;

import java.util.Iterator;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import entities.BasicObject;
import entities.Camera;
import entities.GameObject;
import gui.InventoryGUI;
import gui.NineSlicedGUI;
import managers.AssetManager;
import util.ShapeDrawer;

public class MainScene extends BasicGameState {
	
	public MainScene (int state) {
		// Nothing to do here.
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
		//gc.setShowFPS(false);
		Camera c = new Camera(0, 0, TalesOfEvanusLauncher.width, TalesOfEvanusLauncher.height);
		NineSlicedGUI gui = new NineSlicedGUI(0, 0, 40, 40, AssetManager.sprites.get("inventoryNineSlice"));
		//GameObject test = new BasicObject(0, 0, AssetManager.sprites.get("GodBlessThisImage"));
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		for(GameObject object : GameObject.GAMEOBJECTS) {
			if(object.isDrawable()) {
				object.draw();
			}
		}
		
		/**************************
		 * Need to draw GUI on	  *
		 * a different loop after *
		 * rendering gameobjects  * 
		 **************************/
		
		/*
		for(GUIObject object : GameObject.GAMEOBJECTS) {
			if(object.isDrawable()) {
				object.draw();
			}
		}
		*/
		
		for(Line line : ShapeDrawer.grid) {
			g.draw(line);
		}
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int time) throws SlickException {
		
		Iterator<GameObject> gameObjectIterator = GameObject.GAMEOBJECTS.iterator();
		
		while(gameObjectIterator.hasNext()) {
			
			GameObject object = gameObjectIterator.next();
			object.update();
			
		}
		
	}

	@Override
	public int getID() {
		
		return 0;
		
	}

}
