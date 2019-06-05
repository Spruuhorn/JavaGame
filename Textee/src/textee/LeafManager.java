package textee;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import textee.TextLeaf.TextOption;

public class LeafManager {
	
	public static List<TextLeaf> leaves;
	
	private static int id;
	private static boolean connecting;
	
	private static TextLeaf currentTextLeaf;
	
	static {
		leaves = new ArrayList<>();
		id = 0;
		connecting = false;
	}
	
	public static void update() {
		for(TextLeaf leaf : leaves) {
			leaf.drawLines();
		}
	}
	
	public static void add(TextLeaf leaf) {
		leaves.add(leaf);
	}
	
	public static void remove(TextLeaf leaf) {
		leaves.remove(leaf);
	}
	
	public static void connect(TextLeaf leaf1, TextLeaf leaf2) {
		if(!leaf1.equals(leaf2)) {
			leaf1.getSelectedTextOption().makeConnection(leaf2);
		}
	}
	
	public static TextLeaf getCurrentTextLeaf() {
		return currentTextLeaf;
	}
	
	public static int getID() {
		return ++id;
	}

	public static boolean isConnecting() {
		return connecting;
	}
	
	public static void setConnecting(boolean a) {
		connecting = a;
	}
	
	public static void setCurrentTextLeaf(TextLeaf leaf) {
		currentTextLeaf = leaf;
	}
	
	public static void clear() {
		for(TextLeaf leaf : leaves) {
			leaf.clear();
		}
		leaves.clear();
	}
	
	public static TextLeaf getLeaf(String id) {
		for(TextLeaf leaf : leaves) {
			if(leaf.getNodeID().equals(id)) {
				return leaf;
			}
		}
		return null;
	}
	
	public static boolean isActive(TextLeaf l) {
		for(TextLeaf leaf : leaves) {
			if(leaf.equals(l)) {
				return true;
			}
		}
		return false;
	}
	
	@SuppressWarnings("rawtypes")
	public static void loadTree(File file) {
		
		JSONParser parser = new JSONParser();
			
		try {
			
			/* We are going to have to do two iterations over the keys.
			 * One iteration loop instantiates all the nodes, and the second will connect 
			 * all nodes to each other. 
			 */
			
			JSONObject JSONFile = (JSONObject) parser.parse(new FileReader(file));
			Iterator JSONObjectIterator = JSONFile.keySet().iterator();
			
			/* Iterate through objects in the file */
			while(JSONObjectIterator.hasNext()) {
			    String key = (String) JSONObjectIterator.next();
			    
			    JSONArray JSONleaf = (JSONArray) JSONFile.get(key);
			    
			    /* Each key iteration represents a new leaf to instantiate */
			    
			    TextLeaf leaf = null;
			    
			    for(Object JSONdata : JSONleaf) {
			    	
					if(JSONdata instanceof JSONObject) {
						
						JSONObject data = (JSONObject) JSONdata;
						
						if(data.get("type").equals("data")) {
							
							double xo = Double.parseDouble( (String) data.get("x") );
							double yo = Double.parseDouble( (String) data.get("y") );
							
							leaf = new TextLeaf(xo, yo);
						}
						
						if(data.get("type").equals("textbox")) {
							
							String text = (String) data.get("text");
							
							leaf.setText(text);
						}
						
					}
			    	
			    }
			    
			    leaf.setNodeID(key);
			    
			}
			
			JSONObjectIterator = JSONFile.keySet().iterator();
			
			/* Iterate through objects in the file */
			while(JSONObjectIterator.hasNext()) {
			    String key = (String) JSONObjectIterator.next();
			    
			    JSONArray JSONleaf = (JSONArray) JSONFile.get(key);
			    
			    TextLeaf leaf = getLeaf(key);
			    
			    for(Object JSONdata : JSONleaf) {
			    	
					if(JSONdata instanceof JSONObject) {
						
						JSONObject data = (JSONObject) JSONdata;
						
						if(data.get("type").equals("option")) {
							
							String text = (String) data.get("text");
							String path = (String) data.get("path");
							
							leaf.addOption(text, path);
						}
						
					}
			    	
			    }
			    
			}
		
		}  catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	@SuppressWarnings("unchecked")
	public static void saveTree() {
        JSONObject body = new JSONObject();
        
        for(TextLeaf leaf : leaves) {
        	
        	/* Represents a leaf - with main text and option */
            JSONArray textNode = new JSONArray();
        	
            JSONObject data = new JSONObject();
            data.put("y", String.valueOf(leaf.getY()));
            data.put("x", String.valueOf(leaf.getX()));
            data.put("type", "data");
            textNode.add(data);
            
            /* Add the main text of this leaf */
            JSONObject mainText = new JSONObject();
            mainText.put("text", leaf.getText());
            mainText.put("type", "textbox");
            textNode.add(mainText);
            
            /* Add all options to the JSONObject of this leaf */
            for(TextOption option : leaf.getTextOptions()) {
                JSONObject o = new JSONObject();
                o.put("path", option.getNode());
                o.put("text", option.getText());
                o.put("type", "option");
                
                textNode.add(o);
            }
            
            body.put(leaf.getNodeID(), textNode);
        }
        
    	// parent component of the dialog
    	JFrame parentFrame = new JFrame();
    	 
    	JFileChooser fileChooser = new JFileChooser();
    	fileChooser.setDialogTitle("Save");   
    	 
    	int userSelection = fileChooser.showSaveDialog(parentFrame);
    	 
    	if (userSelection == JFileChooser.APPROVE_OPTION) {
    	    File fileToSave = fileChooser.getSelectedFile();
            //Write JSON file
            try (FileWriter file = new FileWriter(fileToSave.getName() + ".json")) {
     
                file.write(body.toJSONString());
                file.flush();
     
            } catch (IOException e) {
                e.printStackTrace();
            }
    	    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
    	}
        
	}
}
