package tosco.evanus;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;

import physics.CollisionMap;

public class GameMap {
	
	private final int SIZE = 64;
	private final int COL_SPAN = 3;
	
	private TmxMapLoader loader;
	private TiledMap map;
	private MapProperties mapProps;
	private OrthogonalTiledMapRenderer renderer;
	
	private Set<GameObject> objects;
	private CollisionMap collisionMap;
	
	public GameMap(String mapName) {
		/*
		loader = new TmxMapLoader();
		map = loader.load(mapName);
		mapProps = map.getProperties();
		*/
		objects = new HashSet<GameObject>();
		//renderer = new OrthogonalTiledMapRenderer(map);
		
		// Create collision map
		collisionMap = new CollisionMap(5, 5, objects);
	}
	
	/*
	private class Tile extends Entity {
		
		public Tile(float x, float y, Texture sprite) {
			super(x, y, sprite);
		}

		@Override
		public void draw(SpriteBatch batch, OrthographicCamera camera) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public boolean checkEntityCollision(Entity entity) {
		// Only 1 (0th) layer for now
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(0);
		
		// Calculate collisions in 6 tile span
		for(int row = (int) (entity.getY() - (SIZE * COL_SPAN)) / SIZE; row < (int) (entity.getY() + (SIZE * COL_SPAN)) / SIZE; row++) {
			for(int col = (int) (entity.getX() - (SIZE * COL_SPAN)) / SIZE; col < (int) (entity.getX() + (SIZE * COL_SPAN)) / SIZE; col++) {
				Cell cell = layer.getCell(col, row);
				if(cell != null) {
					System.out.println("Colliding with " + cell.getTile().getId());
					return true;
				}
			}
		}
		return false;
	}
	*/
	
	public Set<GameObject> getGameObjects() {
		return objects;
	}
	
	public CollisionMap getCollisionMap() {
		return collisionMap;
	}
	
	public void addNewGameObject(GameObject object) {
		if(object != null) {
			objects.add(object);
		}
	}
}
