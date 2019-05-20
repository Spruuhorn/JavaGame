package other;

import java.io.File;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import managers.AssetManager;
import objects.Portal;
import parents.GameObject;
import parents.PhysicalObject;
import parents.Sprite;
import objects.Camera;

public class MapLoader {
	
	private static final String IMAGE_EXT = ".png";
	
	/* BLUE */
	private static final float PORTAL = 69f/255f;
	private static final float PLAYER_POINT = 222f/255f;
	
	public static HashMap<String, Map> maps = new HashMap<String, Map>();
	public static Map currentActiveMap;
	
	public static String currentMap;
	
	public static void loadMap(String mapName) {
		
		currentMap = mapName;
		
		for(GameObject object : currentActiveMap.getActiveTiles()) {
			object.setActive(false);
		}
		
		currentActiveMap = maps.get(mapName);
		
		for(GameObject object : currentActiveMap.getActiveTiles()) {
			object.setActive(true);
		}
		
		try {
			Image map = new Image("Maps/" + mapName + IMAGE_EXT, false, Image.FILTER_NEAREST);
			
			for(int row = 0; row < map.getHeight(); row++) {
				for(int col = 0; col < map.getWidth(); col++) {
				
					try {
						Color pixel = map.getColor(row, col);
						
						if(pixel.b == PLAYER_POINT) {
							AssetManager.getAsset("Player").getTransform().worldSpace.x = row * 64;
							AssetManager.getAsset("Player").getTransform().worldSpace.y = col * 64;
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						e.printStackTrace();
					}
			
				}
			}
			
		} catch (SlickException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	public static void loadMap(Portal portal) {
		
		String mapName = portal.getMapDestination();
		
		currentMap = mapName;
		
		for(GameObject object : currentActiveMap.getActiveTiles()) {
			object.setActive(false);
		}
		
		currentActiveMap = maps.get(mapName);
		
		for(GameObject object : currentActiveMap.getActiveTiles()) {
			object.setActive(true);
		}
		
		String destinationPortal = portal.getPortalDestination();
		
		GameObject dest = AssetManager.getAsset(destinationPortal);
		
		AssetManager.getAsset("Player").getTransform().worldSpace.x = dest.getTransform().worldSpace.x;
		AssetManager.getAsset("Player").getTransform().worldSpace.y = dest.getTransform().worldSpace.y;
		
		((Camera) AssetManager.getAsset("Main Camera")).goTo();
		System.out.println();
		
	}
	
	public static void init() {
		
	    File[] files = new File("Maps").listFiles();
	    
	    for(File file : files) {
	    	
			try {
				
		    	String fileName = file.getName();
		    	
		    	String mapName = fileName.substring(0, fileName.length() - IMAGE_EXT.length());
		    	
				Image map = new Image("Maps/" + fileName, false, Image.FILTER_NEAREST);
				
				ArrayList<GameObject> tilesToAdd = new ArrayList<>();
				
				ArrayList<Portal> portals = new ArrayList<>();
				
				for(int row = 0; row < map.getHeight(); row++) {
					for(int col = 0; col < map.getWidth(); col++) {
					
						try {
							
							Color pixel = map.getColor(row, col);
							tilesToAdd.addAll(loadTile(pixel, row, col));
							
							if(pixel.b == PORTAL) {
								GameObject portal = new Portal("Door", new Transform(row * 64, col * 64));
								portal.setActive(false);
								portals.add((Portal) portal);
								tilesToAdd.add(portal);
							}
							
							
						} catch (ArrayIndexOutOfBoundsException e) {
							e.printStackTrace();
						}
				
					}
				}
				
				if(!portals.isEmpty())
					configurePortals(portals, mapName);
				
				Map mapObject = new Map(map.getWidth(), map.getHeight(), tilesToAdd);
				
				maps.put(mapName, mapObject);
				
			} 
			
			catch (SlickException e) {e.printStackTrace();}
	    }

	}
	
	private static ArrayList<GameObject> loadTile(Color color, int row, int col) {
		
		ArrayList<GameObject> tiles = new ArrayList<>();
		
		int red   = (int) (color.r * 255);
		int green = (int) (color.g * 255);
		int blue  = (int) (color.b * 255);
		
		JSONParser parser = new JSONParser();
		
		try {
			//Have the JSON file
			JSONObject fileObject = (JSONObject) parser.parse(new FileReader("EntityData/Tile_Info.json"));
			
			//If there is something on this layer that should be placed, check the tile
			if(red != 0.0) {
				
				//Have all tileInfo for "red" tiles
				JSONArray tileInfo = (JSONArray) fileObject.get("red");
				
				for(Object t : tileInfo) {
					
					//Have tileInfo for specific tile
					if(t instanceof JSONObject) {
						
						JSONObject tile  = (JSONObject) t;
						
						int parsed = Integer.parseInt((String) tile.get("id"));
						
						if(parsed == red) {
							
							String png = (String) tile.get("png");
							Transform transform = new Transform(row * 64, col * 64);
							
							JSONArray hitboxInfo = (JSONArray) tile.get("hitbox");	
							
							int[] points = new int[4];
							points[0] = Integer.parseInt((String) hitboxInfo.get(0));
							points[1] = Integer.parseInt((String) hitboxInfo.get(1));
							points[2] = Integer.parseInt((String) hitboxInfo.get(2));
							points[3] = Integer.parseInt((String) hitboxInfo.get(3));
							
							GameObject newTile = new PhysicalObject(PhysicalObject.IMPASSABLE, png, transform, points);
							newTile.setActive(false);
							tiles.add(newTile);
							
						}
						
					}
					
				}
				
			}
			
			if(green != 0.0) {
				
				JSONArray tileInfo = (JSONArray) fileObject.get("green");
				
				for(Object t : tileInfo) {
					
					if(t instanceof JSONObject) {
						
						JSONObject tile  = (JSONObject) t;
						
						int parsed = Integer.parseInt((String) tile.get("id"));
						
						if(parsed == green) {
							
							String png = (String) tile.get("png");
							Transform transform = new Transform(row * 64, col * 64, 1);
							
							GameObject newTile = new Sprite(png, transform);
							newTile.setActive(false);
							tiles.add(newTile);
							
						}
						
					}
					
				}
				
			}
			
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tiles;
	}
	
	/*
	 * Takes in a list of portals for a given map
	 * 
	 * Assigns each portal a map destination, name, and portal destination
	 */
	private static void configurePortals(ArrayList<Portal> portals, String map) {
		
		int counter = 0;
		JSONParser parser = new JSONParser();
		try {
			JSONObject fileObject = (JSONObject) parser.parse(new FileReader("EntityData/Map_Info.json"));
			
			//Get the portal info from the respective map
			JSONArray portalInfo = (JSONArray) fileObject.get(map);
			
			//For each portal info in the array of portal info
			for(Object p : portalInfo) {
				if(p instanceof JSONObject) {
					
					Portal currentPortalWeAreLookingAt = portals.get(counter);
					
					JSONObject portalData = (JSONObject) p;
					
					currentPortalWeAreLookingAt.setMapDestination((String) portalData.get("map"));
					currentPortalWeAreLookingAt.setName((String) portalData.get("name"));
					currentPortalWeAreLookingAt.setPortalDestination((String) portalData.get("portalDestination"));
					
					counter++;
				}
				
			}
			
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
