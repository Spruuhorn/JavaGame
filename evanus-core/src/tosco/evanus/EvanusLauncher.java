package tosco.evanus;

import com.badlogic.gdx.ApplicationAdapter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import interfaces.Updateable;
import manager.GameManager;
import manager.HUDManager;
import manager.InputManager;
import physics.Physics;

/*
 * To Do:
 * 1. Learn how to maange files for the asset manager
 * 2. Create dialogue boxes
 * 3. Figure out how to manage the maps
 * 4. Probably need a seperate camera for HUD (X)
 */

/* convert setting methods for colliders to setCenter() / find out why physics dont work */

public class EvanusLauncher extends ApplicationAdapter {
	
	public static boolean debug = false;
	
	private float zoom = 1000;
	
	private SpriteBatch batch;
	private SpriteBatch HUDBatch;
	private OrthographicCamera camera;
	private ShapeRenderer shapeRenderer;
	
	/* Temporarily made static */
	private GameMap map;
	
	private Player player;
	
	private int[][] tmap = {{1,1,1,1,1,1,1,1,1,1},
							{0,0,0,0,0,0,0,0,0,1},
							{0,1,1,0,0,0,0,0,0,0},
							{0,0,1,1,1,1,1,1,1,0}};
	
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
		
		player = new Player(128, 256, new Texture("PlayerRunLeft.png"));
		
		Texture t = new Texture("flowers.png");
		for(int row = 0; row < tmap.length; row++) {
			for(int col = 0; col <tmap[0].length; col++) {
				if(tmap[row][col] == 1)
					new StaticEntity(col * 64, row * 64, t);
			}
		}
		
		GameManager.setPlayer(player);
	}

	@Override
	public void render () {
		
		// For animation times
		GameManager.elapsedTime += Gdx.graphics.getDeltaTime();
		
		Gdx.gl.glClearColor(0f, .1f, .33f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		camera.position.set(player.x, player.y, 0);
		camera.update();
		
		
		// The collider/image lag behind one another, this didnt happen before because
		// I would update the rectangle and draw the image in the same draw call
		
		// Apply gravity - check collisions - move colliders back on collision - move game object to collider
		Physics.update();
		
		for(GameObject gameObject : GameManager.getGameObjects()) {
			if(gameObject.isActive()) {
				if(gameObject instanceof Updateable) {
					((Updateable) gameObject).update();
				}
				if(gameObject instanceof Entity) {
					((Entity) gameObject).draw(batch, camera);
				}
				gameObject.move();
			}
		}
		
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
			shapeRenderer.setProjectionMatrix(camera.combined);
			shapeRenderer.begin(ShapeType.Line);
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
		*/
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			camera.viewportWidth *= 1.1;
			camera.viewportHeight *= 1.1;
		}
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			camera.viewportWidth /= 1.1;
			camera.viewportHeight /= 1.1;
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		HUDBatch.dispose();
	}
	
}
