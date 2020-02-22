package managers;

import java.io.File;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;

public class AssetManager {
	
	private final static String IMAGE_EXT = ".png";
	
	public static Map<String, Sound> sounds;
	public static Map<String, Animation> sprites;
	
	static {
		
		sounds = new HashMap<>();
		sprites = new HashMap<>();
		
		// Get all files from 'sprites' folder
	    File[] files = new File("assets/sprites").listFiles();
	    
	    // Loop through array of files
	    for(File file : files) {
	    	String fileName = file.getName();
	    	int indexOfName = 0;
	    	
	    	// Loop through the file name: if there are numbers in the name, it is an animation
	    	for(int i = 0; i < fileName.length(); i++) {
	    		try {
	    			Integer.parseInt(Character.toString(fileName.charAt(i)));
	    		} catch(NumberFormatException nfe) {  
	    			indexOfName = i;
	    			break;
	    		}  
	    	}
	    	
	    	// Single image
	    	if(indexOfName == 0) {
				try {
					String spriteName = fileName.substring(0, fileName.length() - IMAGE_EXT.length());
					Image spriteSheet = new Image("assets/sprites/" + fileName, false, Image.FILTER_NEAREST);
					SpriteSheet sheet = new SpriteSheet(spriteSheet, spriteSheet.getWidth(), spriteSheet.getHeight());
					Animation animatedSprite = new Animation(sheet, 1000);
					sprites.put(spriteName, animatedSprite);
				} catch (SlickException e) {
					e.printStackTrace();
				}
	    	} else {
		    	String spriteName = fileName.substring(indexOfName, fileName.length() - IMAGE_EXT.length());
		    	int frames = 1;
		    	try {
		    		frames = Integer.parseInt(fileName.substring(0, indexOfName));
		    	} catch (NumberFormatException nfe) {nfe.printStackTrace();}
				try {
					Image spriteSheet = new Image("assets/sprites/" + fileName, false, Image.FILTER_NEAREST);
					SpriteSheet sheet = new SpriteSheet(spriteSheet, spriteSheet.getWidth() / frames, spriteSheet.getHeight());
					Animation animatedSprite = new Animation(sheet, 100);
					sprites.put(spriteName, animatedSprite);
				} catch (SlickException e) {e.printStackTrace();}
		    }
	    }
	}
}
