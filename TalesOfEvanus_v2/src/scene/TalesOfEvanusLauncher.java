package scene;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class TalesOfEvanusLauncher extends StateBasedGame{

	public static int width = 800, height = 500;
	public static final String gameName = "Tales of Evanus";
	public static final int main = 0;
	
	public TalesOfEvanusLauncher(String name) {
		super(name);
		this.addState(new MainScene(main));
	}

	public static void main(String[] args) {
		AppGameContainer appGameContainer;
		try {
			appGameContainer = new AppGameContainer(new TalesOfEvanusLauncher(gameName));
			appGameContainer.setDisplayMode(width, height, false);
			appGameContainer.start();
		} catch(SlickException e) {e.printStackTrace();}
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.enterState(main);
	}

}
