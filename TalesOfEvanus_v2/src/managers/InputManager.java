package managers;

import org.newdawn.slick.Input;

import entities.GameObject;
import interfaces.Clickable;
import util.ShapeDrawer;

public class InputManager {
	public static Input input;
	public static void create(Input i) {
		input = i;
	}
	public static void update() {
		// Draw grid lines
		if(input.isKeyPressed(Input.KEY_1)) {
			ShapeDrawer.toggleGrid();
		}
		// Draw collider boxes
		if(input.isKeyPressed(Input.KEY_2)) {
			ShapeDrawer.toggleColliders();
		}
		if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			mouseClick(input.getMouseX(), input.getMouseY());
		}
	}
	
	private static void mouseClick(int x, int y) {
		for(GameObject object : GameObject.GAMEOBJECTS) {
			if(object instanceof Clickable) {
				if(object.getCollider().contains(x, y)) {
					((Clickable) object).onClick();
				}
			}
		}
	}
	
}
