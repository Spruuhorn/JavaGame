package gui;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public class NineSlicer {
	public static Image[] slice(Image image) {
		Image[] slices = new Image[9];
		SpriteSheet sheet = new SpriteSheet(image, image.getWidth() / 3, image.getHeight()/ 3);
		Animation animatedSprite = new Animation(sheet, 100);
		for(int i = 0; i < 9; i++ ) {
			slices[i] = animatedSprite.getImage(i);
		}
		return slices;
	}
}
