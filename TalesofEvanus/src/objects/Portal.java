package objects;

import other.Transform;

import parents.PhysicalObject;

public class Portal extends PhysicalObject {
	
	private String map, portalDestination;

	//Constructor for interactable (Press key to go through)
	public Portal(String anim, Transform trans) {
		super(anim, "", trans);
		this.tag = PhysicalObject.PORTAL;
		
	}
	
	//Constructor for non-interactable (Detection Zone)
	public Portal(Transform trans) {
		super("", trans);
		this.tag = PhysicalObject.PORTAL;
		
	}
	
	public String getMapDestination() {
		return map;
	}
	
	public String getPortalDestination() {
		return portalDestination;
	}
	
	public void setMapDestination(String map) {
		this.map = map;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setPortalDestination(String portal) {
		this.portalDestination = portal;
	}
}
