package ui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import interfaces.OnClick;
import parents.UIElement;

public class Button extends UIElement{
	
	private TextWindow ui;
	private Shape button;
	
	private OnClick action;
	
	public Button(String description, float x, float y) {
		
		super(x,y);
		
		ui = new TextWindow(description, x, y, TextWindow.NO_ROLL);
		button = new Rectangle(x, y, ui.getWidth(), ui.getHeight());
	}
	
	public void draw(Graphics graphics) {
		
		super.draw(graphics);
		
		ui.getTransform().setScreenSpace(transform.screenSpace.x, transform.screenSpace.y);
		button.setLocation(transform.screenSpace.x, transform.screenSpace.y);
		
		//graphics.draw(button);
	}
	
	public void onClick() {
		if(action != null)
			action.onClick();
	}
	
	public Shape getHitBox() {
		return button;
	}
	
	public TextWindow getTextWindow() {
		return ui;
	}
	
	public void setActive(boolean a) {
		ui.setActive(a);
		this.setActive(a);
	}
	
	public void addOnClick(OnClick action) {
		this.action = action;
	}
}
