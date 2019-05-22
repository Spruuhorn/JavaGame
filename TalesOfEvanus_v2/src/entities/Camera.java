package entities;

public class Camera extends GameObject {
	
	public static Camera MAIN_CAMERA;
	private GameObject target;

	private int width, height;
	
	public Camera(int x, int y, int width, int height) {
		super(x, y);
		this.width = width;
		this.height = height;
		
		if(MAIN_CAMERA == null) {
			MAIN_CAMERA = this;
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void follow(GameObject target) {
		this.target = target;
	}

}
