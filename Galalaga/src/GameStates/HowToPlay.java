package GameStates;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Entities.AssetsManager;

public class HowToPlay extends BasicGameState {
	
	private Input input;
	
	private String player1Controls = "Player 1\nMove Up - W\nMove Down - S\\nMove Left - A\\nMove Right - S\\nShoot - Spacebar";
	private String player2Controls = "Player 1\nMove Up - Up Arrow\nMove Down - Down Arrow\\nMove Left - Left Arrow\\nMove Right - Right Arrow\\nShoot - Left Control";
	
	private TrueTypeFont f;
	
	public HowToPlay(int state) {}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		gc.setShowFPS(false);
		
		input = gc.getInput();
		
		f = AssetsManager.font;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		f.drawString(0, 0, player1Controls, Color.yellow);
		f.drawString(100, 100, player2Controls, Color.yellow);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int num) throws SlickException {
		if(input.isKeyPressed(Input.KEY_UP)) {
			sbg.enterState(GalalagaLauncher.menu);
		}
	}

	@Override
	public int getID() {
		
		return 2;
		
	}
	
}
