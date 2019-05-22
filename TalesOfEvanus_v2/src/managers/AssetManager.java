package managers;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.Renderable;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;

public class AssetManager {
	
	private final static String IMAGE_EXT = ".png";
	
	public static Map<String, Sound> sounds;
	public static Map<String, Renderable> sprites;
	
	static {
		
		sounds = new HashMap<>();
		sprites = new HashMap<>();
		
	    File[] files = new File("assets/sprites").listFiles();
	    for(File file : files) {
	    	String fileName = file.getName();
	    	int indexOfName = 0;
	    	
	    	for(int i = 0; i < fileName.length(); i++) {
	    		try {
	    			Integer.parseInt(Character.toString(fileName.charAt(i)));
	    		} catch(NumberFormatException nfe) {  
	    			indexOfName = i;
	    			break;
	    		}  
	    	}
	    	
	    	String spriteName = fileName.substring(indexOfName, fileName.length() - IMAGE_EXT.length());
	    	int frames = 1;
	    	try {
	    		frames = Integer.parseInt(fileName.substring(0, indexOfName));
	    	} catch (NumberFormatException nfe) {
	    		Image sprite;
				try {
					sprite = new Image("assets/sprites/" + fileName, false, Image.FILTER_NEAREST);
		    		sprites.put(spriteName, sprite);
		    		continue;
				} catch (SlickException e) {e.printStackTrace();}
	    	}
	    	
			try {
				Image spriteSheet = new Image("assets/sprites/" + fileName, false, Image.FILTER_NEAREST);
				SpriteSheet sheet = new SpriteSheet(spriteSheet, spriteSheet.getWidth() / frames, spriteSheet.getHeight());
				Animation animatedSprite = new Animation(sheet, 100);
				sprites.put(spriteName, animatedSprite);
			} catch (SlickException e) {e.printStackTrace();}
	    }
	}
}
