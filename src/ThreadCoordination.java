class ThreadSleepsDemo implements Runnable {

	@Override
	public void run() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Worker Done");

	}

}

class InterruptAndThreadControl implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			System.out.println("printing i " + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
//				e.printStackTrace(); //Swallowing

				System.out.println("Thread interrupted, shutting down!");
				return;
			}
		}

	}

}

public class ThreadCoordination {

	public static void main(String[] args) throws InterruptedException {
		Runnable runnable = new ThreadSleepsDemo();
		Thread t1 = new Thread(runnable);
		t1.start();
		t1.join();
		System.out.println("Main resumes");

		Runnable runnable2 = new InterruptAndThreadControl();
		Thread t2 = new Thread(runnable2);
		t2.start();
		Thread.sleep(3000);
		t2.interrupt();
		System.out.println("t2 interrupted");
	}

}
