package scenes;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class Launcher extends StateBasedGame{
	
	public static int unit = 16;
	
	public static int width = 960, height = 720;
	
	public static final String gameName = "Tales of Evanus: Prolouge";
	
	public static final int menu = 0;
	
	public static final int mainWorld = 1;
	
	public Launcher(String name) {
		
		super(name);
		
		this.addState(new Menu(menu));
		
		this.addState(new MainWorld(mainWorld));
		
	}

	public static void main(String[] args) {
		
		AppGameContainer appGameContainer;
		
		try {
			
			appGameContainer = new AppGameContainer(new Launcher(gameName));
			
			appGameContainer.setDisplayMode(width, height, false);
			
			appGameContainer.start();
			
		} catch(SlickException e) {
			
			e.printStackTrace();
			
		}
		
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		
		this.enterState(mainWorld);
		
	}

}