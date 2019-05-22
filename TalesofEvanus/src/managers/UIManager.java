package managers;

import java.awt.Font;

import java.io.InputStream;
import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

import parents.UIElement;

public class UIManager {
	
	private static final String nineSliceName = "nineSlice";
	
	public static int NINE_SLICE_DIM;
	
	public static final int TOP_LEFT = 0;
	public static final int TOP_MID = 1;
	public static final int TOP_RIGHT = 2;
	public static final int MID_LEFT = 3;
	public static final int MID = 4;
	public static final int MID_RIGHT = 5;
	public static final int BOT_LEFT = 6;
	public static final int BOT_MID = 7;
	public static final int BOT_RIGHT = 8;
	
	public static ArrayList<UIElement> elements;
	public static ArrayList<Image> nineSlices;
	
	public static TrueTypeFont font;
	
	public static void init() {
		
		elements = new ArrayList<>();
		nineSlices = new ArrayList<>();
		
		try {
			
			//Set Font
			InputStream inputStream	= ResourceLoader.getResourceAsStream("Font/joystix monospace.ttf");
			Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtFont = awtFont.deriveFont(20f);
			font = new TrueTypeFont(awtFont, true);
			
			slice(new Image("Sprites/" + nineSliceName + ".png", false, Image.FILTER_NEAREST));
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void slice(Image image) {
		
		NINE_SLICE_DIM = image.getWidth() / 3;
		
		SpriteSheet sheet = new SpriteSheet(image, image.getWidth() / 3, image.getHeight()/ 3);
		
		Animation animatedSprite = new Animation(sheet, 100);
		
		for(int i = 0; i < 9; i++ ) {
			nineSlices.add(animatedSprite.getImage(i));
		}
	}
	
}
