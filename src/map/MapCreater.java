package map;

import application.Music;
import monster.*;
import object.GamePlatform;
import object.PlatformType;

public class MapCreater {
	
	public static World createWorld() {
		World world = new World();
		world.addMap(MapName.Starter, createStarterMap());
		world.addMap(MapName.False_Knight_room, createFalseKnightRoom());
		world.addMap(MapName.Town, createTown());
		world.addMap(MapName.Cave, createCave());
		world.addMap(MapName.Dark_Cave, createDarkCave());
		world.addMap(MapName.Crystal_Cave, createCrystalCave());
		return world;
	}
	
	private static Map createStarterMap() {
		Map map = new Map(2560,1440);
		map.setRightMap(new Gate(MapName.False_Knight_room, 20, 1015));
		map.addBackground(
				ClassLoader.getSystemResource("Background/Eclipse_Sky.jpg").toString(), 1920, 1080);
		map.addBackground(
				ClassLoader.getSystemResource("Background/Kiln_of_the_First_Flame.png").toString(), 2080, 1240);
		map.addPlatform(new GamePlatform(PlatformType.Platform1, 700, 670));
		map.addPlatform(new GamePlatform(PlatformType.Platform1, 1000, 880));
		map.addPlatform(new GamePlatform(PlatformType.Platform1, 1500, 570));
		map.addPlatform(new GamePlatform(PlatformType.Platform2, 500, 420));
		map.addPlatform(new GamePlatform(PlatformType.FloorPlatform1, 0, 1260, 2560, 218));
		map.addPlatform(new GamePlatform(PlatformType.SidePlatform1, 0, 0, 200, 1370));
		map.addPlatform(new GamePlatform(PlatformType.SidePlatform1, 2410, 0, 200, 1020, true, true));
		map.addEnemy(new Vengefly(400, 200));
		map.addEnemy(new Vengefly(1900, 600));
		map.addEnemy(new Vengefly(1700, 900));
		map.setMusic(Music.Friend_shitai);
		return map;
	}
	
	private static Map createFalseKnightRoom() {
		Map map = new Map(2240,1260);
		map.setLeftMap(new Gate(MapName.Starter, 2460, 1175));
		map.setRightMap(new Gate(MapName.Town, 20, 1175));
		map.addBackground(
				ClassLoader.getSystemResource("Background/Sky.jpg").toString(), 1920, 1080);
		map.addBackground(
				ClassLoader.getSystemResource("Background/Castle.png").toString(), 2080, 1240);
		map.addPlatform(new GamePlatform(PlatformType.FloorPlatform2, 0, 1100, 2240, 218));
		map.addPlatform(new GamePlatform(PlatformType.SidePlatform2, 0, 0, 100, 900));
		map.addPlatform(new GamePlatform(PlatformType.SidePlatform2, 2140, 0, 100, 900, true, false));
		map.addEnemy(new False_Knight(800, -1000));
		map.setMusic(Music.Friend_shitai);
		return map;
	}
	
	private static Map createTown() {
		Map map = new Map(2560,1440);
		map.setLeftMap(new Gate(MapName.False_Knight_room, 2140, 1015));
		map.setLowerMap(new Gate(MapName.Cave, 250, 0));
		map.addBackground(
				ClassLoader.getSystemResource("Background/Cloud.jpg").toString(), 1920, 1080);
		map.addBackground(
				ClassLoader.getSystemResource("Background/Archdragon_Peak.png").toString(), 2080, 1240);
		map.addPlatform(new GamePlatform(PlatformType.FloorPlatform1, 0, 1260, 2160, 218));
		map.addPlatform(new GamePlatform(PlatformType.SidePlatform1, -50, 0, 200, 1020, false, true));
		map.addPlatform(new GamePlatform(PlatformType.SidePlatform1, 2360, 0, 200, 1440, true, false));
		map.setMusic(Music.Friend_shitai);
		return map;
	}
	
	private static Map createCave() {
		Map map = new Map(1800,2400);
		map.setUpperMap(new Gate(MapName.Town, 2220, 1440));
		map.setLeftMap(new Gate(MapName.Dark_Cave, 2000, 1135));
		map.setRightMap(new Gate(MapName.Crystal_Cave, 20, 1115));
		map.addBackground(
				ClassLoader.getSystemResource("Background/Cave.jpg").toString(), 1500, 2000);
		map.addPlatform(new GamePlatform(PlatformType.Platform1, 800, 1900));
		map.addPlatform(new GamePlatform(PlatformType.Platform1, 1300, 1600));
		map.addPlatform(new GamePlatform(PlatformType.Platform1, 1000, 880));
		map.addPlatform(new GamePlatform(PlatformType.Platform1, 600, 1100));
		map.addPlatform(new GamePlatform(PlatformType.Platform1, 700, 500));
		map.addPlatform(new GamePlatform(PlatformType.Platform2, 1000, 1400));
		map.addPlatform(new GamePlatform(PlatformType.Platform2, 1300, 700));
		map.addPlatform(new GamePlatform(PlatformType.Platform2, 400, 1800));
		map.addPlatform(new GamePlatform(PlatformType.FloorPlatform1, 0, 2200, 1800, 218));
		map.addPlatform(new GamePlatform(PlatformType.FloorPlatform1, 400, -100, 1800, 218, true, true));
		map.addPlatform(new GamePlatform(PlatformType.SidePlatform1, 0, 0, 200, 1400, false, true));
		map.addPlatform(new GamePlatform(PlatformType.SidePlatform1, 0, 1600, 200, 730));
		map.addPlatform(new GamePlatform(PlatformType.SidePlatform1, 180, 400, 250, 500));
		map.addPlatform(new GamePlatform(PlatformType.SidePlatform1, 1600, 0, 200, 2000, true, false));
		map.addEnemy(new Vengefly(1100, 1600));
		map.addEnemy(new Vengefly(1300, 1000));
		map.addEnemy(new Glimback(500, 2020));
		map.setMusic(Music.Friend_shitai);
		map.setDarkArea(true);
		return map;
	}
	
	private static Map createDarkCave() {
		Map map = new Map(2100,1400);
		map.setRightMap(new Gate(MapName.Cave, 20, 1515));
		map.addBackground(
				ClassLoader.getSystemResource("Background/Dark_Cave.jpg").toString(), 1650, 1100);
		map.addPlatform(new GamePlatform(PlatformType.FloorPlatform1, 0, 1220, 2100, 218));
		map.addPlatform(new GamePlatform(PlatformType.SidePlatform1, 0, 0, 200, 1330));
		map.addPlatform(new GamePlatform(PlatformType.SidePlatform1, 1950, 0, 200, 1020, true, false));
		map.addEnemy(new Glimback(500, 1040));
		map.setMusic(Music.Friend_shitai);
		map.setDarkArea(true);
		return map;
	}
	
	private static Map createCrystalCave() {
		Map map = new Map(2400,1350);
		map.setLeftMap(new Gate(MapName.Cave, 1700, 2115));
		map.addBackground(
				ClassLoader.getSystemResource("Background/Crystal_Cave.jpg").toString(), 1600, 900);
		map.addPlatform(new GamePlatform(PlatformType.Platform1, 700, 600));
		map.addPlatform(new GamePlatform(PlatformType.Platform1, 1500, 800));
		map.addPlatform(new GamePlatform(PlatformType.FloorPlatform1, 0, 1200, 2400, 218));
		map.addPlatform(new GamePlatform(PlatformType.SidePlatform1, 0, 0, 200, 1000));
		map.addPlatform(new GamePlatform(PlatformType.SidePlatform1, 2200, 0, 200, 1300, true, false));
		map.addEnemy(new Illya(1200, 800));
		map.setMusic(Music.Friend_shitai);
		return map;
	}
	
}
