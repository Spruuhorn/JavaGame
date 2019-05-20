package ui;

import java.util.ArrayList;

import java.util.Arrays;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import managers.UIManager;
import parents.UIElement;
import utilities.Time;

public class TextWindow extends UIElement {
	
	public final static int ROLL = 0;
	public final static int NO_ROLL = 1;
	public final static int CENTER = 2;
	
	private final int padding = UIManager.NINE_SLICE_DIM;
	private final int spacing = UIManager.font.getHeight();
	private final int maxWidth = 16;
	
	private int textOriginX;
	private int textOriginY;
	
	public int gridWidth;
	public int gridHeight;
	
	private ArrayList<String> textLines;
	private ArrayList<String> rollingTextLines;
	
	private int lineMarker;
	private int charMarker;
	private String rollingLine;
	private int textDrawState;
	private boolean rolling;
	private Sound rollingSound;
	
	public TextWindow(String text, float x, float y, int state) {
		
		super(x,y);
		
		this.textDrawState = state;
		
		textLines = new ArrayList<>();
		rollingTextLines = new ArrayList<>();
		
		lineMarker = 0;
		rollingLine = "";
		rolling = true;
		
		gridWidth  = (UIManager.font.getWidth(text) + (2 * padding)) / UIManager.NINE_SLICE_DIM;
		
		if(gridWidth > maxWidth) {
		
			textLines = cutString(text);
			
		} else {
			
			textLines.add(text);
			
		}
		
		calculateWidth();
		calculateHeight();
		
		try {
			rollingSound = new Sound("Sound/RollingText.wav");
		} catch (SlickException e) {e.printStackTrace();}
		
		for(int i = 0; i < textLines.size(); i++) {
			rollingTextLines.add("");
		}
	}
	
	public void draw(Graphics graphics) {
		
		//super.draw(graphics);
		
		/*
		 * Constants:
		 * 32 - size of nine slices (32x32)
		 * 2 - guarantees the box is 2x2 to make a complete box
		 */
		
		/*
		 * Draw the box
		 */
		for(int row = 0; row < gridHeight; row++) {
			for(int col = 0; col < gridWidth; col++) {
				Image border = getNineSliceTile(row, col);
				border.draw(transform.screenSpace.x + (col * border.getWidth()), transform.screenSpace.y + (row * border.getHeight()));
			}
		}
		
		switch(textDrawState) {
		case NO_ROLL:
			/* Draw the ALL text outright */
			for(int string = 0; string < textLines.size(); string++) {
				
				String line = textLines.get(string);
				
				UIManager.font.drawString(transform.screenSpace.x + padding + textOriginX,
								          transform.screenSpace.y + ((string * spacing) + padding) + textOriginY, 
								          line);
			}
			break;
			
		case ROLL:
			/* Rolling Text */
			for(int string = 0; string < rollingTextLines.size(); string++) {
				
				String line = rollingTextLines.get(string);
				
				UIManager.font.drawString(transform.screenSpace.x + padding + textOriginX,
								          transform.screenSpace.y + ((string * spacing) + padding) + textOriginY, 
								          line);
			}
			break;
			
		}
	}

	public void update() {
		
		if(Time.time % 25 == 0 && textDrawState == ROLL) {
			rollText();
		}
		
	}
	
	public int getWidth() {
		return gridWidth * UIManager.NINE_SLICE_DIM;
	}	
	
	public int getHeight() {
		return gridHeight * UIManager.NINE_SLICE_DIM;
	}
	
	public void setTextDrawState(int state) {
		textDrawState = state;
	}
	
	private void rollText() {
		//If the lineMarker has not reached the end of the textLines
		if(lineMarker != textLines.size()) {
			
			//Get the line that is to be read
			String fullLine = textLines.get(lineMarker);
			
			//Get the next character in the line
			char nextChar = fullLine.charAt(charMarker);
			
			//Add the char to the empty string
			rollingLine += String.valueOf(nextChar);
			
			//Set the line to the new string
			rollingTextLines.set(lineMarker, rollingLine);
			
			charMarker++;
			
			//If the charMarker has reached the end of the string
			if(charMarker == fullLine.length()) {
				charMarker = 0;
				lineMarker++;
				rollingLine = "";
			}	
			
			if(charMarker % 3 == 0 && !String.valueOf(nextChar).equals(" ")) {
				rollingSound.play();
			}
		} else {
			rolling = false;
		}
	}
	
	private Image getNineSliceTile(int row, int col) {
		if(row == 0 && col == 0) {
			
			return UIManager.nineSlices.get(UIManager.TOP_LEFT);

		} else if (row == 0 && col == gridWidth - 1) {
			
			return UIManager.nineSlices.get(UIManager.TOP_RIGHT);
			
		} else if (row == gridHeight - 1 && col == 0) {
			
			return UIManager.nineSlices.get(UIManager.BOT_LEFT);
			
		} else if (row == gridHeight - 1 && col == gridWidth - 1) {
			
			return UIManager.nineSlices.get(UIManager.BOT_RIGHT);
			
		} else if (col == 0) {
			
			return UIManager.nineSlices.get(UIManager.MID_LEFT);
			
		} else if (row == 0) {
			
			return UIManager.nineSlices.get(UIManager.TOP_MID);
			
		} else if (row == gridHeight - 1) {
			
			return UIManager.nineSlices.get(UIManager.BOT_MID);
			
		} else if (col == gridWidth - 1) {
			
			return UIManager.nineSlices.get(UIManager.MID_RIGHT);
			
		}  else {
			
			return UIManager.nineSlices.get(UIManager.MID);
			
		}
	}
	
	private ArrayList<String> cutString(String text) {
		/*
		 * Find the last word of the first line that can fit at the top, 
		 * this will determine the width of the text box.
		 */
		
		ArrayList<String> lines = new ArrayList<>();
		
		//Get the words in the String as array, convert to ArrayList
		String[] tempWords = text.split(" ");
		ArrayList<String> words = new ArrayList<>(Arrays.asList(tempWords));
				
		String line = "";
		int tempGridWidth = 0;
		
		while(words.size() != 0) {
			
			if(line.equals("")) {
				line += words.get(0);
			} else {
				line += " " + words.get(0);
			}
			words.remove(0);
			
			tempGridWidth = (UIManager.font.getWidth(line) + padding) / UIManager.NINE_SLICE_DIM;
			
			if(tempGridWidth >= maxWidth) {
				
				lines.add(line);
				line = "";
				
			}
			
			if(words.size() - 1 == 0) {
				if(line.equals("")) {
					line += words.get(0);
				} else {
					line += " " + words.get(0);
				}
				lines.add(line);
				break;
			}
			
		}

		return lines;
		
	}
	
	private void calculateWidth() {
		
		int width = (padding * 2) + ((getLongestString(textLines) - 1) * UIManager.NINE_SLICE_DIM);
		gridWidth = (int) Math.ceil(width/UIManager.NINE_SLICE_DIM);
		
		int gridWidthRaw = gridWidth * UIManager.NINE_SLICE_DIM;
		textOriginX = (gridWidthRaw - width)/2;
		
	}
	
	private void calculateHeight() {
		
		int height = (padding * 2) + (textLines.size() * UIManager.font.getHeight());
		gridHeight = (int) Math.ceil(height/UIManager.NINE_SLICE_DIM);
		
		int gridHeightRaw = gridHeight * UIManager.NINE_SLICE_DIM;
		textOriginY = (gridHeightRaw - height)/2;
		
	}
	
	private int getLongestString(ArrayList<String> strings) {
		int longest = 0;
		for(String string : strings) {
			
			int compare = (UIManager.font.getWidth(string) + (padding * 2)) / UIManager.NINE_SLICE_DIM;
			
			if(compare > longest) {
				longest = compare;
			}
		}
		
		return longest;
	}
}
