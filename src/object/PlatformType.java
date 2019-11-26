package object;

public enum PlatformType {
	
	Platform1,
	Platform2,
	FloorPlatform1,
	FloorPlatform2,
	SidePlatform1,
	SidePlatform2;
	
	public String getPath() {
		switch(this) {
		case Platform1:
			return ClassLoader.getSystemResource("Platform/Platform1.png").toString();
		case Platform2:
			return ClassLoader.getSystemResource("Platform/Platform2.png").toString();
		case FloorPlatform1:
			return ClassLoader.getSystemResource("Platform/FloorPlatform1.jpg").toString();
		case FloorPlatform2:
			return ClassLoader.getSystemResource("Platform/FloorPlatform2.jpg").toString();
		case SidePlatform1:
			return ClassLoader.getSystemResource("Platform/SidePlatform1.jpg").toString();
		case SidePlatform2:
			return ClassLoader.getSystemResource("Platform/SidePlatform2.png").toString();
		default:
			return "";
		}
	}
	
	public boolean repeatX() {
		switch(this) {
		case FloorPlatform1:
			return true;
		case FloorPlatform2:
			return true;
		default:
			return false;
		}
	}
	
	public boolean repeatY() {
		switch(this) {
		case SidePlatform1:
			return true;
		case SidePlatform2:
			return true;
		default:
			return false;
		}
	}
	
	public double getWidth() {
		switch(this) {
		case Platform1:
			return 174;
		case Platform2:
			return 79;
		default:
			return 0;
		}
	}
	
	public double getHeight() {
		switch(this) {
		case Platform1:
			return 75;
		case Platform2:
			return 47;
		default:
			return 0;
		}
	}

}
