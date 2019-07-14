package managers;

import org.newdawn.slick.Input;

import entities.GameObject;
import interfaces.Clickable;

public class InputManager {
	public static Input input;
	public static void create(Input i) {
		input = i;
	}
	public static void update() {
		if(input.isKeyPressed(Input.KEY_I)) {
			
		}
		if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			mouseClick(input.getMouseX(), input.getMouseY());
		}
	}
	
	private static void mouseClick(int x, int y) {
		for(GameObject object : GameObject.GAMEOBJECTS) {
			if(object instanceof Clickable) {
			}
		}
	}
	
}
