package object;

public class Effect extends GameObject {
	
	public Effect(String imagePath, long time, double x, double y, double width, double height) {
		super(imagePath, x, y, width, height);
		holdStage(time);
	}
	
	public Effect(String imagePath, long time, double x, double y, double width, double height, boolean flipX, boolean flipY) {
		this(imagePath, time, x, y, width, height);
		if (flipX) {
			setScaleX(-1);
		}
		if (flipY) {
			setScaleY(-1);
		}
	}
	
	public void changeStage() {
		remove();
	}

}
