package entities;

import org.newdawn.slick.Renderable;

public class BasicObject extends GameObject {

	public BasicObject(int x, int y, Renderable sprite) {
		super(x, y, sprite);
		System.out.println(x + " , " + y);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
