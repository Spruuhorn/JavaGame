package ui;

import java.io.FileReader;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import scenes.Launcher;

public class DialogueManager {
	
	private static final int padding = UIManager.NINE_SLICE_DIM * 2;
	
	public static final String opening = "opening";
	
	public static void loadDialogue(String option) {
		
		for(UIElement ui : UIManager.elements) {
			ui.setActive(false);
		}
		
		int counter = 0;
		int x = 0;
		int y = 0;
		TextWindow window = null;
		
		/* Parsing Sequence:
		 * 
		 * 1. Create a parser to read the JSON file - a JSON file contains JSON arrays
		 * 2. Get the array (represents a leaf with text and text options) of JSON objects 
		 * 3. Loop through each object in the array of the leaf
		 * 4. Read a property of the JSON object
		 */
		
		JSONParser parser = new JSONParser();
		try {
			JSONObject fileObject = (JSONObject) parser.parse(new FileReader("text.json"));
			
			JSONArray leafData = (JSONArray) fileObject.get(option);
			
			for(Object texts : leafData) {
				if(texts instanceof JSONObject) {
					
					JSONObject ui = (JSONObject) texts;
					
					if(ui.get("type").equals("textbox")) {
						
						String text = (String) ui.get("text");
						
						window = new TextWindow(text, 0, 0, TextWindow.ROLL);
						x = Launcher.width/2 - window.getWidth()/2;
						y = padding / 2;
						window.getTransform().setWorldSpace((float) x, (float)y);
						y += window.getHeight() - padding;
						
					}
					
					if(ui.get("type").equals("option")) {
						
						String text = (String) ui.get("text");
						String path = (String) ui.get("path");
						
						Button button = new Button(text, path, 0, 128 + (counter * 64));
						x = Launcher.width/2 - button.getTextWindow().getWidth()/2;
						y += (2 * padding / 3) + button.getTextWindow().getHeight()/2;
						button.getTransform().setWorldSpace((float) x, (float) y);
						button.getTextWindow().setActive(false);
						button.setActive(false);
						window.choices.add(button);
						
					}
					counter++;
				}
				
			}
			
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
