package application;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import object.Enemy;
import object.Hero;

public class Controller {
	
	private static AnimationTimer timer;
	private static boolean left, right, down, jump, dash;
	private static int direction;
	private static Stage cerrentStage;
	
	public static void addTimer(Hero hero) {
		timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				Main.setSceneWidth(cerrentStage.getWidth());
				if(cerrentStage.isFullScreen()) {
					Main.setSceneHeight(cerrentStage.getHeight());
				}else {
					Main.setSceneHeight(cerrentStage.getHeight() - 30);
				}
				direction = 0;
				if (left) {
					direction += -1;
					if(!right) {
						hero.setTurnLeft(true);
					}
				}
				if (right) {
					direction += 1;
					if(!left) {
						hero.setTurnLeft(false);
					}
				}
				hero.setMovement(direction);
				if (down) {
					hero.diving();
				}else {
					hero.setFallSpeedLimit(true);
				}
				if (jump) {
					hero.jumping();
				}else {
					hero.stopJump();
				}
				if (dash) {
					hero.dash();
				}
				hero.move();
				for(Enemy i:hero.getMap().getEnemyList()) {
					i.action();
				}
			}
		};
		timer.start();
	}
	
	public static void setKey(Stage stage) {
		cerrentStage = stage;
		stage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override 
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case LEFT:  
					left = true;
					break;
				case RIGHT: 
					right = true;
					break;
				case DOWN:  
					down = true;
					break;
				case A:    
					jump = true;
					break;
				case D:
					dash = true;
					break;
				default:	   
					break;
				}
			}
		});
		stage.getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {
		    @Override 
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case LEFT:  
					left = false;
					break;
				case RIGHT: 
					right = false; 
					break;
				case DOWN:  
					down = false; 
					break;
				case A:    
					jump = false;
					break;
				case D:
					dash = false;
					break;
				default:	   
					break;
				}
			}
		});
	}

}
