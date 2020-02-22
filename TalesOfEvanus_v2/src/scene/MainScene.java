package scene;

import java.util.Iterator;

import org.newdawn.slick.Color;
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
import interfaces.Clickable;
import interfaces.Drawable;
import interfaces.Hoverable;
import managers.AssetManager;
import managers.InputManager;
import util.ShapeDrawer;

public class MainScene extends BasicGameState {
	
	public MainScene (int state) {
		// Nothing to do here.
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
		//gc.setShowFPS(false);
		Camera c = new Camera(0, 0, TalesOfEvanusLauncher.width, TalesOfEvanusLauncher.height);
		//NineSlicedGUI gui = new NineSlicedGUI(0, 0, 235, 425, AssetManager.sprites.get("inventoryNineSlice"));
		GameObject test = new BasicObject(0, 0, true, "GodBlessThisImage");
		InventoryGUI invGUI = new InventoryGUI(0,0);
		InputManager.create(gc.getInput());
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		for(GameObject object : GameObject.GAMEOBJECTS) {
			if(object instanceof Drawable) {
				((Drawable) object).onDraw();
			}
		}
		
		if(ShapeDrawer.drawColliders) {
			for(GameObject object : GameObject.GAMEOBJECTS) {
				if(object.isCollidable()) {
					g.draw(object.getCollider());
				}
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
		
		if(ShapeDrawer.drawGrid) {
			for(Line line : ShapeDrawer.grid) {
				if(line.getMinX() == TalesOfEvanusLauncher.width/2 || line.getMinY() == TalesOfEvanusLauncher.height/2) {
					g.setColor(Color.green);
				} else {
					g.setColor(Color.white);
				}
				g.draw(line);
			}
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int time) throws SlickException {
		InputManager.update();
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
