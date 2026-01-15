
class ThreadExtending extends Thread {
	public ThreadExtending(String name) {
		super(name);
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println(
					"Hi I am a thread and my name is " + Thread.currentThread().getName() + " Count is " + (i + 1));
		}
	}

}

class RunnableImplmenting implements Runnable {
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println(
					"Hi I am a thread and my name is " + Thread.currentThread().getName() + " Count is " + (i + 1));
		}
	}

}

public class ThreadvsRunnable {

	public static void main(String[] args) {
		ThreadExtending t1 = new ThreadExtending("Thread 1");
		Runnable runnable = new RunnableImplmenting();
		Thread t2 = new Thread(runnable, "Thread 2");

		t1.start();
		t2.start();

	}

}
