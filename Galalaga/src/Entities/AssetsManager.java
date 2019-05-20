package Entities;

import java.awt.Font;
import java.io.InputStream;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

public class AssetsManager {
	
	public static TrueTypeFont font;
	public static Sound select;
	public static Sound alienShoot;
	public static Sound alienHit;
	public static Sound alienDestroy;
	public static Sound playerShoot;
	public static Sound playerHit;
	public static Sound gameOverSound;
	
	public static void initAssets() throws SlickException {
		
		InputStream inputStream	= ResourceLoader.getResourceAsStream("joystix monospace.ttf");
		
		try {
			
			//Set Font
			Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtFont = awtFont.deriveFont(16f);
			font = new TrueTypeFont(awtFont, true);
		
			
			select = new Sound("Select.wav");
			alienShoot = new Sound("AlienShoot.wav");
			playerShoot = new Sound("PlayerShoot.wav");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
