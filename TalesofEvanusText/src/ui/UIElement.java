package ui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;

import other.Transform;
import parents.GameObject;

public class UIElement extends GameObject {

	private Shape hitbox;
	
	public UIElement(float x, float y) {
		this.transform = new Transform(x,y);
		UIManager.elements.add(this);
	}
	
	public void draw(Graphics g) {
		//I am the other giver of life
		
		transform.setScreenSpace(transform.worldSpace.x, transform.worldSpace.y);
	}
	
	public Shape getHitBox() {
		return hitbox;
	}
}

