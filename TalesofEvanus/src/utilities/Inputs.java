package utilities;

import org.newdawn.slick.Input;

import managers.AssetManager;
import other.MapLoader;
import parents.GameObject;

public class Inputs {
	
	public static Input input;
	
	private static float inputHorizontal;
	private static float inputVertical;
	
	public static void inputInputs(Input i) {
		input = i;
	}
	
	public static void update() {
		
		if(input.isKeyDown(Input.KEY_RIGHT)) {
			inputHorizontal = 1; 
		} else if (input.isKeyDown(Input.KEY_LEFT)) {
			inputHorizontal = -1;
		} else {
			inputHorizontal = 0;
		}
		
		if(input.isKeyDown(Input.KEY_UP)) {
			inputVertical = -1;
		} else if (input.isKeyDown(Input.KEY_DOWN)) {
			inputVertical = 1;
		} else {
			inputVertical = 0;
		}
		
		if(input.isKeyPressed(Input.KEY_B)) {
			Debug.drawHitBox = !Debug.drawHitBox;
		}
		
		if(input.isKeyPressed(Input.KEY_R)) {
			Debug.drawRays = !Debug.drawRays;
		}
		
		if(input.isKeyPressed(Input.KEY_1)) {
			MapLoader.loadMap("testMap1");
		}
		
		if(input.isKeyPressed(Input.KEY_2)) {
			MapLoader.loadMap("testMap2");
		}
		
		if(input.isKeyPressed(Input.KEY_3)) {
			MapLoader.loadMap("testMap3");
		}
		
		if(input.isKeyPressed(Input.KEY_D)) {
			GameObject p = AssetManager.getAsset("Player");
			AssetManager.gameObjects.remove(p);
		}
		
	}
	
	public static float getHorizontalInputs() {
		return inputHorizontal;
	}
	
	public static float getVerticalInputs() {
		return inputVertical;
	}
}
