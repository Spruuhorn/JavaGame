package util;

import java.util.HashSet;
import java.util.Set;

import org.newdawn.slick.geom.Line;

import scene.TalesOfEvanusLauncher;

public class ShapeDrawer {
	
	public static Set<Line> grid;
	
	private static final int UNITS = 100;
	
	static {
		
		int width = TalesOfEvanusLauncher.width;
		int height = TalesOfEvanusLauncher.height;
		
		grid = new HashSet<>();
		
		for(int vert = 0; vert < width/UNITS; vert++) {
			Line line = new Line(vert * UNITS, 0, vert * UNITS, height);
			grid.add(line);
		}
		
		for(int horz = 0; horz < height/UNITS; horz++) {
			Line line = new Line(0, horz * UNITS, width, horz * UNITS);
			grid.add(line);
		}
	}
}
