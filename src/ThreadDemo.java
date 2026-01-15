
public class ThreadDemo extends Thread {

	@Override
	public void run() {
		Thread t1 = new Thread();
		t1.start();
		for (int i = 0; i < 10_000; i++) {
			System.out.println("Pehle Chalega ye");
		}

		Thread t2 = new Thread();
		t2.start();
		for (int i = 0; i < 10_000; i++) {
			System.out.println("Dusra Chalega ye");
		}

		Thread t3 = new Thread();
		t3.start();
		for (int i = 0; i < 10_000; i++) {
			System.out.println("Teesra Chalega ye");

//		try {
////			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		}
	}

	public static void main(String[] args) {
//		AnotherThreadDemo t1 = new AnotherThreadDemo();
////		t1.start();
//		System.out.println("The current state of another thread is " + t1.getState());
//		System.out.println("The current state of main thread is " + Thread.currentThread().getState());
//		for (int i = 0; i < 10_00_00_000; i++) {
//			System.out.println("World");
//		}

//		tcute();

		ThreadDemo n = new ThreadDemo();
		n.run();

	}

}
