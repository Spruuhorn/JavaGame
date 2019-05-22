package gui;

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
	
	private class Slice extends GameObject {

		public Slice(int x, int y) {
			super(x, y);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void update() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public NineSlicedGUI(int x, int y, int width, int height, Renderable toSlice) {
		super(x, y);
		if(toSlice instanceof Image) {
			Image image = ((Image) toSlice);
			this.slices = NineSlicer.slice(image);
			double sliceWidth = image.getWidth()/3;
			double sliceHeight = image.getHeight()/3;
			this.rows = (int) ((height/sliceHeight) % 1 == 0? height/sliceHeight : (height/sliceHeight) + 1);
			this.cols = (int) ((height/sliceWidth) % 1 == 0? height/sliceWidth : (height/sliceWidth) + 1);
			System.out.println("Sliced " + rows + " " + cols);
		}
	}
	
	public NineSlicedGUI(int x, int y, int rows, int cols) {
		super(x, y);
	}

	@Override
	public void update() {
		
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
