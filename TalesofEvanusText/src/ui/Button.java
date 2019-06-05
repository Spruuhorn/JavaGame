package ui;

import org.newdawn.slick.Graphics;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Button extends UIElement {
	
	private TextWindow ui;
	private Shape button;
	
	private String path;
	
	public Button(String description, String path, float x, float y) {
		
		super(x,y);
		
		ui = new TextWindow(description, x, y, TextWindow.NO_ROLL);
		button = new Rectangle(x, y, ui.getWidth(), ui.getHeight());
		this.path = path;
	}
	
	public void draw(Graphics graphics) {
		
		super.draw(graphics);
		
		ui.getTransform().setWorldSpace(transform.worldSpace.x, transform.worldSpace.y);
		button.setLocation(transform.screenSpace.x, transform.screenSpace.y);
		
		//graphics.draw(button);

	}
	
	public void onClick() {
		DialogueManager.loadDialogue(path);
	}
	
	public Shape getHitBox() {
		return button;
	}
	
	public TextWindow getTextWindow() {
		return ui;
	}
}
