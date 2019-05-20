package Entities;

import org.newdawn.slick.geom.Shape;

public class Collider {
	
	private Shape hitBox;
	
	private String tag;
	
	public Collider(Shape shape, String tag) {
		hitBox = shape;
		this.tag = tag;
	}
	
	public Shape getHitBox() {
		return hitBox;
	}
	
	public void setX(float newX) {
		hitBox.setX(newX);
	}
	
	public void setY(float newY) {
		hitBox.setY(newY);
	}
	
	public String getTag() {
		return tag;
	}
	
	public boolean intersects(Collider other) {
		return hitBox.intersects(other.getHitBox());
	}
}
