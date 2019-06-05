package other;

import java.io.File;


import java.util.ArrayList;

import java.util.HashMap;
import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import parents.*;

public class AssetManager {
	
	private final static String IMAGE_EXT = ".png";

	public static ArrayList<GameObject> gameObjects;
	public static HashMap<String, Animation> animations;
	
	private static ArrayList<String> testStrings;
	
	public static void init() {
		
		//INITIZALIZE ARRAYLISTS
		gameObjects = new ArrayList<>();
		animations = new HashMap<String, Animation>();
		
		testStrings = new ArrayList<>();
		addRandomStrings();
		
		//INITIALIZE ALL SPRITES AND ANIMATIONS
	    File[] files = new File("Sprites").listFiles();
	    
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
	    	
	    	int frames;
	    	
	    	try {
	    		frames = Integer.parseInt(fileName.substring(0, indexOfName));
	    	} catch (NumberFormatException nfe) {
	    		frames = 1;
	    	}
	    	
			try {
				
				Image spriteSheet = new Image("Sprites/" + fileName, false, Image.FILTER_NEAREST);
				
				SpriteSheet sheet = new SpriteSheet(spriteSheet, spriteSheet.getWidth() / frames, spriteSheet.getHeight());
				
				Animation animatedSprite = new Animation(sheet, 100);
				
				animations.put(spriteName, animatedSprite);
				
			} 
			
			catch (SlickException e) {e.printStackTrace();}
			
	    }
	    //END OF SPRITE AND ANIMATION INITLIZATION
	
	}

	private static void addRandomStrings() {

		testStrings.add("Tryglaxorite");
		testStrings.add("My every Tuesday");
		testStrings.add("The cashier’s hand began to tremble. As he motioned to select the “large” button on the monitor, he could feel a tear roll down his eye.");
		testStrings.add("Yo Mike, you want to play MapleStory. There is a delayed opening.");
		testStrings.add("I need a really long string but I hate writing strings that say I need a long string so the next string I make it will be a copypasta.");
		testStrings.add("Hello World!");
		testStrings.add("I can't wait to defeat the Moon Lord");
		testStrings.add("Medic cambiate a solider...");

		//testStrings.add("What the fuck did you just fucking say about me, you little bitch? I’ll have you know I graduated top of my class in the Navy Seals, and I’ve been involved in numerous secret raids on Al-Quaeda, and I have over 300 confirmed kills. I am trained in gorilla warfare and I’m the top sniper in the entire US armed forces. You are nothing to me but just another target. I will wipe you the fuck out with precision the likes of which has never been seen before on this Earth, mark my fucking words. You think you can get away with saying that shit to me over the Internet? Think again, fucker. As we speak I am contacting my secret network of spies across the USA and your IP is being traced right now so you better prepare for the storm, maggot. The storm that wipes out the pathetic little thing you call your life. You’re fucking dead, kid. I can be anywhere, anytime, and I can kill you in over seven hundred ways, and that’s just with my bare hands. Not only am I extensively trained in unarmed combat, but I have access to the entire arsenal of the United States Marine Corps and I will use it to its full extent to wipe your miserable ass off the face of the continent, you little shit. If only you could have known what unholy retribution your little “clever” comment was about to bring down upon you, maybe you would have held your fucking tongue. But you couldn’t, you didn’t, and now you’re paying the price, you goddamn idiot. I will shit fury all over you and you will drown in it. You’re fucking dead, kiddo.");
	}
	
	public static String getRandomString() {
		Random r = new Random();
		return testStrings.get(r.nextInt(testStrings.size()));
		
	}

	public static GameObject getAsset(String byName) {
		for(GameObject object : gameObjects) {
			if(object.getName().equals(byName)) {
				return object;
			}
		}
		
		return null;
	}
	
}
