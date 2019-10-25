package menu;

import java.util.HashMap;

public class WorldMap {
	
	private HashMap<String, Map> mapList = new HashMap<String, Map>();
	private Map cerrentMap;
	
	public void addMap(String name, Map map) {
		mapList.put(name, map);
		map.setWorldMap(this);
	}

	public HashMap<String, Map> getMapList() {
		return mapList;
	}
	
	public void goTo() {
		
	}

	public Map getCerrentMap() {
		return cerrentMap;
	}

	public void setCerrentMap(Map cerrentMap) {
		this.cerrentMap = cerrentMap;
	}

}
