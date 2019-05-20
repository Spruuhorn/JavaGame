package parents;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;

import managers.AssetManager;
import managers.UIManager;
import objects.Camera;
import other.Transform;

public class UIElement extends GameObject {
	
	private Shape hitbox;
	
	public UIElement(float x, float y) {
		this.transform = new Transform(x,y);
		UIManager.elements.add(this);
	}
	
	public void draw(Graphics g) {
		//I am the other giver of life
		GameObject camera = AssetManager.getAsset("Main Camera");
		
		transform.setScreenSpace(transform.worldSpace.x - camera.getTransform().worldSpace.x + ((Camera)camera).getSize().x/2, 
							     transform.worldSpace.y - camera.getTransform().worldSpace.y + ((Camera)camera).getSize().y/2);
	}
	
	public Shape getHitBox() {
		return hitbox;
	}
}
