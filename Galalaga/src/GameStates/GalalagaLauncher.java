package GameStates;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import Entities.AssetsManager;

public class GalalagaLauncher extends StateBasedGame{
	
	public static int width = 640, height = 920;
	
	public static final String gameName = "Deep Space";
	
	public static final int menu = 0;
	
	public static final int game = 1;
	
	public static final int how = 2;
	
	public GalalagaLauncher(String name) {
		
		super(name);
		
		this.addState(new Menu(menu));
		
		this.addState(new Game(game));
		
		this.addState(new HowToPlay(how));
		
	}

	public static void main(String[] args) {
		
		AppGameContainer appGameContainer;
		
		try {
			
			appGameContainer = new AppGameContainer(new GalalagaLauncher(gameName));
			
			appGameContainer.setDisplayMode(width, height, false);
			
			appGameContainer.start();
			
		} catch(SlickException e) {
			
			e.printStackTrace();
			
		}
		
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		
		AssetsManager.initAssets();
		
		this.enterState(menu);
		
	}

}
