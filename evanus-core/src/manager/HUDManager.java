package manager;

import java.util.HashMap;

import java.util.Set;
import java.util.HashSet;
import java.util.Map;

import tosco.evanus.HUD;

public class HUDManager {

	public static Map<Integer, Set<HUD>> HUDElements;
	static {
		HUDElements = new HashMap<Integer, Set<HUD>>();
	}
	
	public static void add(HUD element) {
		int z = element.getZ();
		if(HUDElements.containsKey(z)) {
			HUDElements.get(z).add(element);
		} else {
			Set<HUD> layer = new HashSet<HUD>();
			layer.add(element);
			HUDElements.put(z, layer);
		}
	}
}
