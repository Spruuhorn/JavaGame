import javafx.animation.AnimationTimer;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Camera {
	
	final static int WIDTH = TopDownLauncher.WIDTH;

	final static int HEIGHT = TopDownLauncher.HEIGHT;
	
	final static int SCALE  = TopDownLauncher.SCALE;
	
	private static WritableImage canvasScreen;
	private static PixelReader pixelReader;
	
	private static Color[][] canvasPixels;
	
	private static GraphicsContext g;
	private static Canvas c;
	
	private static GameObject target;
	
	private static double xo, yo;

	private static double xPadding = 150, yPadding = 150;

	public Camera(Canvas canvas, GameObject t) {
		
		c = canvas;
		g = c.getGraphicsContext2D();
		
		canvasPixels = new Color[WIDTH][HEIGHT];
		
		target = t;
		
		xo = WIDTH / 2;
		yo = HEIGHT / 2;
		
	}
	
	public static void update(double time) {
		
        g.clearRect(0, 0, WIDTH, HEIGHT);
        
        //Loop through list of all game objects in game, and draw them, apply collision detection, etc.
        for(int i = 0; i < GameManager.gameObjects.size(); i++) {
        	
        	GameObject obj = GameManager.gameObjects.get(i);
        	
        	double objCanvasPosX = obj.getX() - (target.getX() - xo);
        	double objCanvasPosY = obj.getY() - (target.getY() - yo);
        	
        	if((objCanvasPosX > -xPadding && objCanvasPosX < WIDTH + xPadding) && (objCanvasPosY > -yPadding && objCanvasPosY < HEIGHT + yPadding)) {
        	
	        	if (obj.equals(target)) {
	        		
	        		obj.draw(xo, yo, g, time);
	        		
	        	} else {
	        		
	        		obj.draw(objCanvasPosX, objCanvasPosY, g, time);
	        		
	        	}
	        	
        	}
        	
        }

        /* The screen gets darker
         * because the snapshot is 
         * recording the darkened values, 
         * adding more dark onto them, 
         * and so on, until its black.
         */
        
        //Used to manage lighting
        canvasScreen = c.snapshot(new SnapshotParameters(), new WritableImage(WIDTH, HEIGHT));
        
        pixelReader = canvasScreen.getPixelReader();
        
        readCanvas();
        
        drawCanvas(g);
        
	}
	
	public static void readCanvas() {
		for(int y = 0; y < HEIGHT; y += SCALE) {
			for(int x = 0; x < WIDTH; x += SCALE) {
				canvasPixels[x][y] = pixelReader.getColor(x,y);
			}
		}
	}
	
	public static void drawCanvas(GraphicsContext g) {
		
		for(int y = 0; y < HEIGHT; y += SCALE) {
			for(int x = 0; x < WIDTH; x += SCALE) {
				
				g.setFill(canvasPixels[x][y]);
				g.fillRect(x, y, SCALE, SCALE);
				
				/*
				if(x >= 0 && y >= 0) {
					Color tempColor = Color.hsb(canvasPixels[x][y].getHue(), canvasPixels[x][y].getSaturation(), TopDownLauncher.clamp(canvasPixels[x][y].getBrightness() + DistanceToLight(x,y), 0, 1), canvasPixels[x][y].getOpacity());
				
					g.setFill(tempColor);
					g.fillRect(x, y, SCALE, SCALE);
				}
				*/
				
			}
		}

	}
	
	public void setTarget(GameObject newTarget) {
		target = newTarget;
	}
	
	/*
	double DistanceToLight(int pixelX, int pixelY) {
		
		double distance = GameObject.distance(pixelX, mouseX, pixelY, mouseY);
		
		if(distance > 150 * SCALE) {
			return -.5;
		} else if(distance > 100 * SCALE) {
			return -0.25;
		} else if(distance > 50 * SCALE){
			return -0.1;
		} else {
			return 0.0;
		}
		
	}
	*/
	
}
