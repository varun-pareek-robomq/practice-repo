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

public class ThreadCoordination {

	public static void main(String[] args) throws InterruptedException {
		Runnable runnable = new ThreadSleepsDemo();
		Thread t1 = new Thread(runnable);
		t1.start();
		t1.join();
		System.out.println("Main resumes");

	}

}
