package scenes;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import managers.AssetManager;
import managers.UIManager;
import other.MapLoader;
import other.MouseHandler;
import utilities.Inputs;

public class Menu extends BasicGameState {
	
	public Menu(int state) {}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		Inputs.inputInputs(gc.getInput());
		AssetManager.init();
		UIManager.init();
		MapLoader.init();
		MouseHandler.init();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int num) throws SlickException {
		
	}

	@Override
	public int getID() {
		
		return 0;
		
	}
	
}
