package other;

public class Time {
	
	public static int time = 0;
	
	public static void passTime() {
		time++;
		
		if(time >= Integer.MAX_VALUE) {
			time = 0;
		}
	}
	
	public static void waitForSeconds(int seconds) {
		new Thread(new Timer(seconds)).start();
	}
	
	private static class Timer implements Runnable{

		private int wait;
		
		public Timer(int seconds) {
			wait = seconds * 1000;
		}
		
		@Override
		public void run() {
			
			try {
				System.out.println("uh");
				Thread.sleep(wait);
				System.out.println("oh");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
}
