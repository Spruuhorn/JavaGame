package ui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import managers.UIManager;
import parents.UIElement;

public class PlainText extends UIElement{
	
	private float rise;
	private float alpha;
	private Color color;
	
	private String text;

	public PlainText(String text, float x, float y) {
		super(x, y);
		this.text = text;
		
		this.alpha = 1.0f;
		this.rise = 0f;
		this.color = new Color(1.0f, 1.0f, 1.0f, alpha);
		
		System.out.println(transform.worldSpace.x + " " + transform.worldSpace.y);
	}
	
	public void draw(Graphics g) {
		//UIManager.font.drawString(transform.screenSpace.x - UIManager.font.getWidth(text), transform.screenSpace.y - UIManager.font.getHeight(text), text);
		rise -= 0.001f;
		UIManager.font.drawString(transform.worldSpace.x, transform.worldSpace.y + rise, text, color);
		color.a -= 0.0005f;
	}
	
}
