package application;

import object.GameObject;

public class Delay extends Thread {
	
	private long time;
	private double data;
	private GameObject object;
	
	public Delay(long time) {
		this.time = time;
		start();
	}
	
	public Delay(long time, double data) {
		this.time = time;
		this.data = data;
		start();
	}
	
	public Delay(long time, GameObject object) {
		this.time = time;
		this.object = object;
		start();
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(time);
			end();
		} catch (InterruptedException e) {
			
		}
	}
	
	public void end() {
		
	}

	public double getData() {
		return data;
	}

	public GameObject getObject() {
		return object;
	}

	public void setObject(GameObject object) {
		this.object = object;
	}
	
}