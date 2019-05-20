package utilities;

import java.util.Comparator;

import parents.Sprite;

public class zComparator implements Comparator<Sprite> {

	@Override
	public int compare(Sprite sprite1, Sprite sprite2) {
		
		//If origins are centered
		Float num1 = sprite1.getTransform().worldSpace.y + sprite1.getAnimation().getHeight()/2;
		Float num2 = sprite2.getTransform().worldSpace.y + sprite2.getAnimation().getHeight()/2;

		
		/*
		//If origins are not centered
		Float num1 = sprite1.getTransform().worldSpace.y + sprite1.getAnimation().getHeight();
		Float num2 = sprite2.getTransform().worldSpace.y + sprite2.getAnimation().getHeight();
		*/
		
		return num1.compareTo(num2);
	}
		
}
