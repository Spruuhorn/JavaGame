package parents;

import managers.AssetManager;
import other.Transform;

public class GameObject  {
	
	protected Transform transform;
	protected String name;
	
	private boolean active;
	
	public GameObject() {
		this.transform = new Transform(0,0);
		this.active = true;
		AssetManager.gameObjects.add(this);
	}
	
	public GameObject(Transform transform) {
		this.transform = transform;
		this.active = true;
		AssetManager.gameObjects.add(this);
	}
	
	public GameObject(String name, Transform transform) {
		this.name = name;
		this.transform = transform;
		this.active = true;
		AssetManager.gameObjects.add(this);
	}
	
	public void update() {
		//I am the giver of life
	}
	
	public String getName() {
		return name;
	}
	
	public Transform getTransform() {
		return transform;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean a) {
		active = a;
	}
	
}
