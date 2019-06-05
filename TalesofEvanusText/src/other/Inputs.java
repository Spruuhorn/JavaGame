package other;

import org.newdawn.slick.Input;

import parents.GameObject;

public class Inputs {
	
	public static Input input;
	public static void inputInputs(Input i) {
		input = i;
	}
	
	public static void update() {
		
		if(input.isKeyPressed(Input.KEY_B)) {
			Debug.drawHitBox = !Debug.drawHitBox;
		}
		
	}
}
