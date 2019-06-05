package other;

import org.newdawn.slick.Input;


import parents.GameObject;
import ui.Button;
import ui.TextWindow;
import ui.UIManager;
import ui.UIElement;

public class MouseHandler {
	
	private static Input input;
	
	private static Vector2 position;
	
	private static GameObject selected;
	private static GameObject hovering;
	
	public static void init() {
		input = Inputs.input;
		position = new Vector2();
	}
	
	public static void update() {
		
		position.x = input.getMouseX();
		position.y = input.getMouseY();
		
		if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			
			//new TextWindow(AssetManager.getRandomString(), position.x, position.y);

			selected = getObject();
			if(selected != null) {
				if(selected instanceof Button) {
					((Button) selected).onClick();
				}
			}
		}
		
		if(input.isMousePressed(Input.MOUSE_RIGHT_BUTTON)) {
			System.out.println(UIManager.elements.size());
		}
		
		//hovering = getHovering();
		
	}
	
	private static GameObject getObject() {
		
		for(UIElement ui : UIManager.elements) {
			if(ui.getHitBox() != null) {
				if(ui.getHitBox().contains(position.x, position.y) && ui.isActive()) {
					return ui;
				}
			}
		}
		
		return null;
	}
	
	private static GameObject getHovering() {
		for(UIElement ui : UIManager.elements) {
			if(ui.getHitBox().contains(position.x, position.y) && ui.isActive()) {
				return ui;
			}
		}
		
		return null;
	}

}
