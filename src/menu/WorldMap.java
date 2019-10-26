package menu;

import java.util.HashMap;

public class WorldMap {
	
	private HashMap<MapName, Map> mapList = new HashMap<MapName, Map>();
	private Map cerrentMap;
	
	public void addMap(MapName name, Map map) {
		mapList.put(name, map);
		map.setWorldMap(this);
	}
	
	public void goTo() {
		
	}
	
	public HashMap<MapName, Map> getMapList() {
		return mapList;
	}

	public Map getCerrentMap() {
		return cerrentMap;
	}

	public void setCerrentMap(Map cerrentMap) {
		this.cerrentMap = cerrentMap;
	}

}
