package gui;

import java.util.HashSet;
import java.util.Set;

import org.newdawn.slick.Image;
import org.newdawn.slick.Renderable;

import entities.GameObject;

public class NineSlicedGUI extends GameObject {

	public static final int TOP_LEFT = 0;
	public static final int TOP_MID = 1;
	public static final int TOP_RIGHT = 2;
	public static final int MID_LEFT = 3;
	public static final int MID = 4;
	public static final int MID_RIGHT = 5;
	public static final int BOT_LEFT = 6;
	public static final int BOT_MID = 7;
	public static final int BOT_RIGHT = 8;
	
	private int rows, cols;
	private Image[] slices;
	private Set<Slice> setOfSlices;
	
	{
		setOfSlices = new HashSet<>();
	}
	
	private class Slice extends GameObject {

		public Slice(int x, int y, Image image) {
			super(x, y, image);
		}
		
		@Override
		public void update() {
			
		}
	}
	
	public NineSlicedGUI(int x, int y, int width, int height, Renderable toSlice) {
		super(x, y);
		if(toSlice instanceof Image) {
			Image image = ((Image) toSlice);
			this.slices = NineSlicer.slice(image);
			double sliceWidth = image.getWidth()/3;
			double sliceHeight = image.getHeight()/3;
			this.rows = (int) ((height/sliceHeight) % 1 == 0? height/sliceHeight : (height/sliceHeight) + 1); // Check for height overflow
			this.cols = (int) ((width/sliceWidth) % 1 == 0? width/sliceWidth : (width/sliceWidth) + 1); // Check for width overflow
			
			for(int row = 0; row < rows; row++) {
				for(int col = 0; col < cols; col++) {
					Slice slice = new Slice((int)((col * sliceWidth) - (x + (cols * sliceWidth))), (int) ((row * sliceHeight) - (y + (rows * sliceHeight))), getNineSliceTile(row, col));
					setOfSlices.add(slice);
				}
			}
		}
	}
	
	public NineSlicedGUI(int x, int y, int rows, int cols) {
		super(x, y);
	}

	@Override
	public void update() {
		
	}
	
	@Override
	public void remove() {
		for(Slice slice: setOfSlices) {
			slice.remove();
		}
		GameObject.GAMEOBJECTS.remove(this);
	}
	
	private Image getNineSliceTile(int row, int col) {
		if(row == 0 && col == 0) {
			return slices[TOP_LEFT];
		} else if (row == 0 && col == cols - 1) {
			return slices[TOP_RIGHT];
		} else if (row == rows - 1 && col == 0) {
			return slices[BOT_LEFT];
		} else if (row == rows - 1 && col == cols - 1) {
			return slices[BOT_RIGHT];
		} else if (col == 0) {
			return slices[MID_LEFT];
		} else if (row == 0) {
			return slices[TOP_MID];
		} else if (row == rows - 1) {
			return slices[BOT_MID];
		} else if (col == cols - 1) {
			return slices[MID_RIGHT];
		}  else {
			return slices[MID];
		}
	}
}
