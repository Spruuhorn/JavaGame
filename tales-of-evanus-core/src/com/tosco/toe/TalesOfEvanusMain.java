package com.tosco.toe;

import com.badlogic.gdx.ApplicationAdapter;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import interfaces.Updateable;
import managers.GameManager;
import managers.HUDManager;
import managers.InputManager;
import physics.Physics;

/*
 * To Do:
 * 1. Learn how to maange files for the asset manager
 * 2. Create dialogue boxes
 * 3. Figure out how to manage the maps
 * 4. Probably need a seperate camera for HUD (X)
 */

/* convert setting methods for colliders to setCenter() / find out why physics dont work */

public class TalesOfEvanusMain extends ApplicationAdapter {
	
	public static boolean debug = false;
	
	private float zoom = 1000;
	
	private SpriteBatch batch;
	private SpriteBatch HUDBatch;
	private OrthographicCamera camera;
	private ShapeRenderer shapeRenderer;
	
	/* Temporarily made static */
	private GameMap map;
	
	private Player player;
	private Entity fake1, fake2, fake3;
	
	@Override
	public void create () {
		
		shapeRenderer = new ShapeRenderer();
		
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
		
		batch = new SpriteBatch();
		HUDBatch = new SpriteBatch();
		
		map = new GameMap("BLAH BLAH BLAH");
		GameManager.setCurrentMap(map);
		
		
		Gdx.input.setInputProcessor(new InputManager());
		
		player = new Player(0, 128, new Texture("PlayerRunLeft.png"));
		
		// does not have a collider and collider is never created
		fake1 = new StaticEntity(0, 0, new Texture("flowers.png"), "Dummy");
		fake1 = new StaticEntity(64, 0, new Texture("flowers.png"), "Dummy");
		fake1 = new StaticEntity(64, 200, new Texture("flowers.png"), "Dummy");
		
		GameManager.setPlayer(player);
	}

	@Override
	public void render () {
		
		// For animation times
		GameManager.elapsedTime += Gdx.graphics.getDeltaTime();
		
		Gdx.gl.glClearColor(0f, .1f, .33f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		// Game Map render
		//gameManager.getCurrentMap().render(camera);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		
		Physics.update();
		
		for(GameObject gameObject : GameManager.getGameObjects()) {
			if(gameObject.isActive()) {
				if(gameObject instanceof Entity) {
					((Entity) gameObject).draw(batch, camera);
				}
				if(gameObject instanceof Updateable) {
					((Updateable) gameObject).update();
				}
			}
		}
		
		camera.position.set(player.x, player.y, 0);
		camera.update();
		
		//gameManager.getCurrentMap().checkEntityCollision(p);
		
		batch.end();
		
		HUDBatch.begin();
		for(int layer : HUDManager.HUDElements.keySet()) {
			for(HUD hud : HUDManager.HUDElements.get(layer)) {
				if(hud.isActive()) {
					hud.draw(HUDBatch);
				}
			}
		}
		HUDBatch.end();
		
		// Draw rectangles ---DEBUGGING---
		if(debug) {
			shapeRenderer.begin(ShapeType.Line);
			shapeRenderer.setColor(Color.GREEN);
			shapeRenderer.rect(Physics.RECT_TEST.x, Physics.RECT_TEST.y, Physics.RECT_TEST.width, Physics.RECT_TEST.height);
			for(GameObject gameObject : GameManager.getGameObjects()) {
				if(gameObject.isActive()) {
					gameObject.getCollider().draw(shapeRenderer);
				}
			}
			for(int layer : HUDManager.HUDElements.keySet()) {
				for(HUD hud : HUDManager.HUDElements.get(layer)) {
					if(hud.isActive()) {
						hud.drawRectangle(shapeRenderer);
					}
				}
			}
			shapeRenderer.end();
		}
		
		/*
		if (Gdx.input.isTouched()) {
			camera.translate(-Gdx.input.getDeltaX(), Gdx.input.getDeltaY());
			camera.update();
		}
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			camera.viewportWidth *= 1.1;
			camera.viewportHeight *= 1.1;
		}
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			camera.viewportWidth /= 1.1;
			camera.viewportHeight /= 1.1;
		}
		*/
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
	
}
