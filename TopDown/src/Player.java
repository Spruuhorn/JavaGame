import javafx.scene.input.KeyEvent;

public class Player extends GameObject{

	public Player(double xo, double yo, double xhb, double yhb, double whb, double hhb, String imgPath, String defaultImage, int imgSize) {
		
		super(xo, yo, xhb, yhb, whb, hhb, imgPath, defaultImage, imgSize);
		
		speed = 4;
		
		canMove = true;
		
		direction = "Down";
		
		state = "Idle";
		
	}

	private final String type = "Player";
	
	private String direction;
	private String state;
	private double dirY;
	private double dirX;
	private int speed;
	
	private boolean canMove;
	
	private double velocityX, velocityY;


	
	public void update() {
		
		if(canMove) {
			velocityX = dirX * speed; 
			velocityY = dirY * speed;
			    	
			x += velocityX;
			y += velocityY;
			    	
			if(dirX == 0 && dirY == 0 ) {
			    state = "Idle";
			}
			
			getCollisionBox().setPos(x, y);
			
			setImages(type + state + direction);
		}
				
	}
	
	public void handleMovement(KeyEvent e) {
		switch(e.getCode()) {
		
		case SPACE: 
			System.out.println(x + " " + y);
			break;
		
		case W:
			
			direction = "Up";
			state = "Move";
			dirY = -1;
			setImages(type + state + direction);
			
			break;
			
		case A:
			
			direction = "Left";
			state = "Move";
			dirX = -1;
			
			setImages(type + state + direction);
			
			break;
			
		case S:
			
			direction = "Down";
			state = "Move";
			dirY = 1;
			
			setImages(type + state + direction);
			
			break;
	
		case D:
			
			direction = "Right";
			state = "Move";
			dirX = 1;
			
			setImages(type + state + direction);
			
			break;
			
		default: break;
		}
	}
	
	public void handleMove(KeyEvent e) {
		switch(e.getCode()) {
		case W:
			
			dirY = 0;
			
			break;
			
		case A:
			
			dirX = 0;
			
			break;
			
		case S:
			
			dirY = 0;
			
			break;
	
		case D:
			
			dirX = 0;
			
			break;
			
		default:break;
		}
	}
	
	public void setCanMove(boolean move) {
		canMove = move;
	}

}
