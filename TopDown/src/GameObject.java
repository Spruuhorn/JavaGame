import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class GameObject {
	
	final double FRAME_RATE = .100;
	
	public Image[] frames;
	
	public Image image;
	
	public double x, y;
	
	public double canvasX, canvasY;
	
	public String imagePath;
	
	public CollisionBox colBox;
	
	public String type;

	/* Animated Image with collision
	 * Collision box is drawn with origin in the middle of the sprite
	 * Standard computer coordinate system applies
	 */
    public GameObject(double xo, double yo, double xhb, double yhb, double whb, double hhb, String imgPath, String defaultImage, int imgSize) {
    	
    	x = xo;
    	
    	y = yo;
    	
    	imagePath = imgPath;
    	
    	frames = new Image[imgSize];
    	
    	setImages(defaultImage);
    	
    	colBox = new CollisionBox(xhb, yhb, whb, hhb);
    	
    	GameManager.gameObjects.add(this);
    	
    }
    
	//Static Image with collision
    public GameObject(double xo, double yo, double xhb, double yhb, double whb, double hhb, String defaultImage) {
    	
    	x = xo;
    	
    	y = yo;
    	
    	imagePath = "";
    	
    	frames = new Image[1];
    	
    	setImage(defaultImage);
    	
    	colBox = new CollisionBox(xhb, yhb, whb, hhb);
    	
    	GameManager.gameObjects.add(this);
    	
    }
	
	//Animated Image
    public GameObject(double xo, double yo, String imgPath, String defaultImage, int imgSize) {
    	
    	x = xo;
    	
    	y = yo;
    	
    	imagePath = imgPath;
    	
    	frames = new Image[imgSize];
    	
    	setImages(defaultImage);
    	
    	GameManager.gameObjects.add(this);
    	
    }
    
    //Static Image
    public GameObject(double xo, double yo, String imgPath, String image) {
    	
    	x = xo;
    	
    	y = yo;
    	
    	imagePath = imgPath;
    	
    	frames = new Image[1];
    	
    	setImages(image);
    	
    	GameManager.gameObjects.add(this);
    	
    }
    
    //Static Image
    public GameObject(double xo, double yo, String image) {
    	
    	x = xo;
    	
    	y = yo;
    	
    	imagePath = "";
    	
    	frames = new Image[1];
    	
    	setImage(image);
    	
    	GameManager.gameObjects.add(this);
    	
    }
    
    //Specific Static Image
    public GameObject(String t, double xo, double yo, String image) {
    	
    	x = xo;
    	
    	y = yo;
    	
    	type = t;
    	
    	imagePath = "";
    	
    	frames = new Image[1];
    	
    	setImage(image);
    	
    	GameManager.gameObjects.add(this);
    	
    }
    
    public void setImages(String imageAddress) {
    	for (int i = 0; i < frames.length; i++) {
		    frames[i] = new Image(imagePath + imageAddress + (i + 1) + ".png");
		}
    }
    
    public void setImage(String imageAddress) {
		frames[0] = new Image(imagePath + imageAddress + ".png");
    }
    
    public Image getFrame(double time) {
    	
        int index = (int)((time % (frames.length * FRAME_RATE)) / FRAME_RATE);
        return frames[index];
        
    }
    
    public void draw(double x, double y, GraphicsContext g, double t) {
    	
    	g.drawImage(getFrame(t), x - getFrame(t).getWidth() / 2, y - getFrame(t).getHeight() / 2);
    	
    }
    
    public void draw(double x, double y, GraphicsContext g) {
    	
    	g.drawImage(image, x - image.getWidth() / 2, y - image.getHeight() / 2);
    	
    }
    
    public double getX() {
		return x;
	}
    
    public double getY() {
    	return y;
    }
    
    public void setX(double newX) {
		x = newX;
	}
    
    public void setY(double newY) {
    	y = newY;
    }
    
    public double getCanvasX() {
		return canvasX;
	}
    
    public double getCanvasY() {
    	return canvasY;
    }
    
    public void setCanvasX(double newX) {
		canvasX = newX;
	}
    
    public void setCanvasY(double newY) {
    	canvasY = newY;
    }
    
    public String getType() {
    	return type;
    }
    
    public Image getImage() {
    	return frames[0];
    }
    
    public String getImagePath() {
    	return imagePath;
    }
    
    public CollisionBox getCollisionBox() {
    	if(colBox != null) {
    		return colBox;
    	}
    	return null;
    }
    
	public static double clamp(double num, double min, double max) {
		if(num < min) {
			num = min;
		} else if (num > max) {
			num = max;
		}
		return num;
	}
	
	public static double distance(double x1, double x2, double y1, double y2) {
		return Math.sqrt((Math.pow(x1 - x2, 2)+Math.pow(y1 - y2, 2)));
	}
	
	public static void lerp(double a, double b, double t) {
		
	}
	
    public static int RandomNum(int low, int up) {
        Random rand = new Random();
        int num = rand.nextInt(up) + low;
        return num;
        
    }
    
    public static int RandomNum(int up) {
        Random rand = new Random();
        int num = rand.nextInt(up);
        return num;
        
    }
	
	public void handleMovement(KeyEvent e) {}
	public void handleMove(KeyEvent e) {}
	public void update() {}
	public void setCanMove(boolean b) {}

	
}
