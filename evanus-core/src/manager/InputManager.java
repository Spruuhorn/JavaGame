package manager;

import com.badlogic.gdx.Gdx;


import com.badlogic.gdx.InputProcessor;

import interfaces.Clickable;
import interfaces.Hoverable;
import physics.Physics;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import tosco.evanus.GameObject;
import tosco.evanus.HUD;
import tosco.evanus.InventoryManager;
import tosco.evanus.EvanusLauncher;

public class InputManager implements InputProcessor {

	@Override
	public boolean keyDown(int keycode) {
		if(Gdx.input.isKeyJustPressed(Keys.B)) {
			EvanusLauncher.debug = !EvanusLauncher.debug;
		}
		if(Gdx.input.isKeyJustPressed(Keys.P)) {
			Physics.APPLY = !Physics.APPLY;
		}
		if(Gdx.input.isKeyJustPressed(Keys.L)) {
			Physics.APPLY = true;
			Physics.update();
			Physics.APPLY = false;
		}
		return false;
	}
	@Override
	public boolean keyUp(int keycode) {
		return false;
	}
	@Override
	public boolean keyTyped(char character) {
		return false;
	}
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(button == Buttons.LEFT) {
			
		}	
		// O(n^2) :(
		// switch back to one data structure and sort the elements by z value. (probably 
		/*
		for(int layer : HUDManager.HUDElements.keySet()) {
			for(HUD hud : HUDManager.HUDElements.get(layer)) {
				if(hud instanceof Clickable && hud.isActive() && hud.getRectangle() != null) {
					if(hud.getRectangle().contains(screenX, Gdx.graphics.getHeight() - screenY)) {
						((Clickable) hud).onClick(screenX, Gdx.graphics.getHeight() - screenY);
					}
				}
			}
		}
		*/
		for(GameObject gameObject : GameManager.getGameObjects()) {
			if(gameObject instanceof Clickable && gameObject.isActive() && gameObject.getCollider().getHitbox() != null) {
				if(gameObject.getCollider().getHitbox().contains(screenX, Gdx.graphics.getHeight() - screenY)) {
					((Clickable) gameObject).onClick(screenX, Gdx.graphics.getHeight() - screenY);
				}
			}
		}
		return false;
	}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		for(int layer : HUDManager.HUDElements.keySet()) {
			for(HUD hud : HUDManager.HUDElements.get(layer)) {
				if(hud instanceof Hoverable && hud.isActive() && hud.getRectangle() != null) {
					if(hud.getRectangle().contains(screenX, Gdx.graphics.getHeight() - screenY)) {
						((Hoverable) hud).onHover(screenX, Gdx.graphics.getHeight() - screenY);
					} else {
						((Hoverable) hud).onHoverOff();
					}
				}
			}
		}
		for(GameObject gameObject : GameManager.getGameObjects()) {
			if(gameObject instanceof Hoverable && gameObject.isActive() && gameObject.getCollider().getHitbox() != null) {
				if(gameObject.getCollider().getHitbox().contains(screenX, Gdx.graphics.getHeight() - screenY)) {
					((Hoverable) gameObject).onHover(screenX, Gdx.graphics.getHeight() - screenY);
				} else {
					((Hoverable) gameObject).onHoverOff();
				}
			}
		}
		
		//Handle Inventory Manager
		if(InventoryManager.getSelected() != null) {
			InventoryManager.getSelected().setX(screenX);
			InventoryManager.getSelected().setY(Gdx.graphics.getHeight() - screenY);
		}
		
		return false;
	}
	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
