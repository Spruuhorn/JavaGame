package scenes;

import java.util.ListIterator;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import parents.*;
import ui.DialogueManager;
import ui.UIElement;
import ui.UIManager;
import other.AssetManager;
import other.Inputs;
import other.MouseHandler;
import other.Time;

public class MainWorld extends BasicGameState {
	
	private final Color background = new Color(104f/255f, 56f/255f, 108f/255f,1f);
	
	public MainWorld(int state) {}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
		gc.setShowFPS(false);
		
		Inputs.inputInputs(gc.getInput());
		AssetManager.init();
		UIManager.init();
		MouseHandler.init();
		DialogueManager.loadDialogue(DialogueManager.opening);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		g.setBackground(background);
		
		for(UIElement ui : UIManager.elements) {
			
			if(ui.isActive()) {
				ui.draw(g);
			}
			
		}
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int num) throws SlickException {

		
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
