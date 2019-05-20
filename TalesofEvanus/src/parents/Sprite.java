package parents;

import java.util.ArrayList;

import org.newdawn.slick.Animation;

import managers.AssetManager;
import objects.Camera;
import other.Transform;

public class Sprite extends GameObject {
	
	public static ArrayList<Sprite> background = new ArrayList<>();
	
	private final int BACKGROUND = 1;
	private int z;
	
	private final Animation nullSprite = AssetManager.animations.get("null");
	
	protected Animation animation;
	
	public Sprite(String animation, String name, Transform transform) {
		super(name, transform);
		this.animation = AssetManager.animations.get(animation);
		this.z = transform.z;
		if(this.animation == null) {
			this.animation = nullSprite;
		}
		
		if(z == BACKGROUND) {
			background.add(this);
		} else {
			AssetManager.sprites.add(this);
		}
	}
	
	public Sprite(String animation, Transform transform) {
		super(animation, transform);
		this.animation = AssetManager.animations.get(animation);
		this.z = transform.z;
		if(this.animation == null) {
			this.animation = nullSprite;
		}
		
		if(z == BACKGROUND) {
			background.add(this);
		} else {
			AssetManager.sprites.add(this);
		}
		
		this.name = animation;
	}

	public void draw() {
		
		GameObject camera = AssetManager.getAsset("Main Camera");
		

		//If origins are centered
		transform.setScreenSpace(transform.worldSpace.x - camera.transform.worldSpace.x + ((Camera)camera).getSize().x/2 - animation.getWidth()/2, 
			     transform.worldSpace.y - camera.transform.worldSpace.y + ((Camera)camera).getSize().y/2 - animation.getHeight()/2);

		
		/*
		//If origins are not centered
		transform.setScreenSpace(transform.worldSpace.x - camera.transform.worldSpace.x + ((Camera)camera).getSize().x/2, 
							     transform.worldSpace.y - camera.transform.worldSpace.y + ((Camera)camera).getSize().y/2);
		*/
		
		animation.draw(transform.screenSpace.x, transform.screenSpace.y);
		
	}
	
	public void setAnimation(String anim) {
		if(AssetManager.animations.get(anim) != null) {
			animation = AssetManager.animations.get(anim);
		} else {
			animation = nullSprite;
		}
	}
	
	public Animation getAnimation() {
		return animation;
	}
	
}
