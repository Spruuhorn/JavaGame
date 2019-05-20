package other;

import org.newdawn.slick.Input;

import managers.AssetManager;
import managers.UIManager;
import objects.Camera;
import parents.GameObject;
import parents.PhysicalObject;
import parents.UIElement;
import scenes.Launcher;
import ui.Button;
import ui.TextWindow;
import utilities.Inputs;

public class MouseHandler {
	
	private static Input input;
	
	private static Vector2 position;
	
	public static GameObject selected;
	public static GameObject hovering;
	
	public static void init() {
		input = Inputs.input;
		position = new Vector2();
	}
	
	public static void update() {
		
		position.x = input.getMouseX();
		position.y = input.getMouseY();
		
		Vector2 screenToWorld = ((Camera)AssetManager.getAsset("Main Camera")).screenToWorldSpace(position.x, position.y);
		
		if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			
			/*
			//On place down
			if(selected != null) {
				
				selected = null;
				
			} else {
				selected = getObject();
			}
			*/
			
			selected = getObject();
			if(selected instanceof Button) {
				((Button) selected).onClick();
			}
		}
		
		if(input.isMousePressed(Input.MOUSE_RIGHT_BUTTON)) {
			new Button("Click me!", screenToWorld.x, screenToWorld.y);
		}
		
		/*
		if(selected != null) {
			selected.getTransform().setWorldSpace(screenToWorld.x, screenToWorld.y);
		}
		*/
		
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
		
		for(PhysicalObject object : AssetManager.physicalObjects) {
			if(object.getHitBox().contains(position.x, position.y) && object.isActive()) {
				if(object.isActive()) {
					return object;
				}
			}
		}
		
		return null;
	}
	
	private static GameObject getHovering() {
		for(PhysicalObject object : AssetManager.physicalObjects) {
			if(object.getHitBox().contains(position.x, position.y) && object.isActive()) {
				return object;
			}
		}
		
		return null;
	}

}
