package application;

public class Delay extends Thread {
	
	private long time;
	private double data;
	
	public Delay(long time) {
		this.time = time;
		start();
	}
	
	public Delay(long time, double data) {
		this.time = time;
		this.data = data;
		start();
	}
	
	@Override
	public void run() {
		try {
			sleep(time);
		} catch (InterruptedException e) {
			
		} finally {
			end();
		}
	}
	
	public void end() {
		
	}

	public double getData() {
		return data;
	}
	
}