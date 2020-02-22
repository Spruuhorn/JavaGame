package util;

import java.util.HashSet;
import java.util.Set;

import org.newdawn.slick.geom.Line;

import scene.TalesOfEvanusLauncher;

public class ShapeDrawer {
	
	public static boolean drawGrid = false;
	public static boolean drawColliders = false;
	
	public static Set<Line> grid;
	
	private static final int UNITS = 80;
	
	static {
		
		int width = TalesOfEvanusLauncher.width;
		int height = TalesOfEvanusLauncher.height;
		
		int xo = width/2;
		int yo = height/2;
		
		grid = new HashSet<>();
		
		//Center Lines
		Line line = new Line(0, yo, width, yo);
		grid.add(line);
		line = new Line(xo, 0, xo, height);
		grid.add(line);
		
		//Horz Up
		for(int horz = yo - UNITS; horz > 0; horz -= UNITS) {
			Line l = new Line(0, horz, width, horz);
			grid.add(l);
		}
		
		//Vert Right
		for(int vert = xo + UNITS; vert < width; vert += UNITS) {
			Line l = new Line(vert, 0, vert, height);
			grid.add(l);
		}
		
		//Horz Down
		for(int horz = yo + UNITS; horz < height; horz += UNITS) {
			Line l = new Line(0, horz, width, horz);
			grid.add(l);
		}
		
		//Vert Left
		for(int vert = xo - UNITS; vert > 0; vert -= UNITS) {
			Line l = new Line(vert, 0, vert, height);
			grid.add(l);
		}
	}
	
	public static void toggleGrid() {
		drawGrid = !drawGrid;
	}
	
	public static void toggleColliders() {
		drawColliders = !drawColliders;
	}
}
