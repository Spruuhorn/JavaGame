
public class CollisionBox {
	
	private double x, y, width, height;
	
	public CollisionBox (double xo, double yo, double w, double h) {
		
		x = xo;
		y = yo;
		width = w;
		height = h;
		
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getWidth() {
		return width;
	}
	
	public double getHeight() {
		return height;
	}
	
	public void setX(double newX) {
		x = newX;
	}
	
	public void setY(double newY) {
		y = newY;
	}
	
	public void setPos(double newX, double newY) {
		x = newX;
		y = newY;
	}
}
