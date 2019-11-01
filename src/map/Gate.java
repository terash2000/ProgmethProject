package map;

import application.Main;

public class Gate {
	
	private MapName mapName;
	private double toX, toY;
	
	public Gate(MapName mapName, double toX, double toY) {
		this.mapName = mapName;
		this.toX = toX;
		this.toY = toY;
	}
	
	public void travel() {
		Main.world.setCerrentMap(mapName, toX, toY);
	}
}
