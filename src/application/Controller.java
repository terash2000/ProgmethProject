package application;

import object.Actionable;

import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class Controller {
	
	private static AnimationTimer gameLoop;
	private static boolean left, right, up, down, jump, attack, dash;
	private static int direction;
	
	public static void startTimer() {
		gameLoop = new AnimationTimer() {
			@Override
			public void handle(long now) {
				Main.setSceneWidth(Main.cerrentStage.getWidth());
				if(Main.cerrentStage.isFullScreen()) {
					Main.setSceneHeight(Main.cerrentStage.getHeight());
				}else {
					Main.setSceneHeight(Main.cerrentStage.getHeight() - 30);
				}
				updateHero();
				updateObject();
			}
		};
		gameLoop.start();
	}
	
	public static void setKey() {
		Main.cerrentStage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override 
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case LEFT:  
					left = true;
					break;
				case RIGHT: 
					right = true;
					break;
				case UP:
					up = true;
					break;
				case DOWN:  
					down = true;
					break;
				case A:    
					jump = true;
					break;
				case S:
					attack = true;
					break;
				case D:
					dash = true;
					break;
				default:	   
					break;
				}
			}
		});
		Main.cerrentStage.getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {
		    @Override 
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case LEFT:  
					left = false;
					break;
				case RIGHT: 
					right = false; 
					break;
				case UP:
					up = false;
					break;
				case DOWN:  
					down = false; 
					break;
				case A:    
					jump = false;
					break;
				case S:
					attack = false;
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
	
	private static void updateHero(){
		direction = 0;
		if (left) {
			direction += -1;
			if(!right) {
				Main.hero.setTurnLeft(true);
			}
		}
		if (right) {
			direction += 1;
			if(!left) {
				Main.hero.setTurnLeft(false);
			}
		}
		Main.hero.walk(direction);
		if (jump) {
			Main.hero.jumping();
		}else {
			Main.hero.stopJump();
		}
		if (dash) {
			Main.hero.dash();
		}
		if(attack) {
			if(down && !up) {
				Main.hero.downwardSlash();
			}else if(up && !down){
				Main.hero.upperSlash();
			}else {
				Main.hero.frontAttack();
			}
		}
		Main.hero.move();
	}
	
	private static void updateObject(){
		for(Actionable i:new ArrayList<Actionable>(Main.worldMap.getObjectList())) {
			i.action();
		}
	}

}
