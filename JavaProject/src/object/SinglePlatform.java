package object;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SinglePlatform extends Platform {
	
	protected ImageView body;
	
	public SinglePlatform(String ImagePath, double x, double y, double width, double height) {
		super(x, y, width, height);
		body = new ImageView(new Image(ImagePath, width, height, false, true));
	}

	public ImageView getBody() {
		return body;
	}
	
}