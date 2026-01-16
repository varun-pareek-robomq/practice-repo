import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ThreadA implements Runnable {
	private SharedResource1 sharedResource1;
	private SharedResource2 sharedResource2;

	private Lock l1;
	private Lock l2;

	public ThreadA(SharedResource1 sharedResource1, SharedResource2 sharedResource2, Lock l1, Lock l2) {

		this.sharedResource1 = sharedResource1;
		this.sharedResource2 = sharedResource2;
		this.l1 = l1;
		this.l2 = l2;
	}

//	Lock l = new ReentrantLock(); Doing this makes a lock of each Thread (Never deadlock chance)

	@Override
	public void run() {

		try {
			if (l1.tryLock(300, TimeUnit.MILLISECONDS)) {
				try {
					if (l2.tryLock(300, TimeUnit.MILLISECONDS)) {
						try {
							sharedResource1.setData(1);
						} finally {
							l2.unlock();
						}

					}

					else {
						// Could not get lock 2
						System.out.println("Will try later, as could not get lock 2");
					}
				} finally {
					l1.unlock();
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Thread 1 acquired lock 1 ");

		try {

			Thread.sleep(50);
			l2.lock();
			System.out.println("Thread 1 acquired lock 2");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}

class ThreadB implements Runnable {
	private SharedResource1 sharedResource1;
	private SharedResource2 sharedResource2;
	private Lock l1;
	private Lock l2;

	public ThreadB(SharedResource1 sharedResource1, SharedResource2 sharedResource2, Lock l1, Lock l2) {
		this.sharedResource1 = sharedResource1;
		this.sharedResource2 = sharedResource2;
		this.l1 = l1;
		this.l2 = l2;
	}

	@Override
	public void run() {
		try {
			if (l1.tryLock(300, TimeUnit.MILLISECONDS)) {
				try {
					if (l2.tryLock(300, TimeUnit.MILLISECONDS)) {
						try {
							sharedResource1.setData(112);
						} finally {
							l2.unlock();
						}

					}

					else {
						// Could not get lock 2
						System.out.println("Will try later, as could not get lock 2");
					}
				} finally {
					l1.unlock();
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			try {
				Thread.sleep(30);
				l1.lock();
				System.out.println("Thread 2 acuired lock 11");
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}
}

class SharedResource1 {
	private int data;

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public SharedResource1(int data) {
		this.data = data;
	}

}

class SharedResource2 {
	private int data;

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public SharedResource2(int data) {
		this.data = data;
	}

}

public class IntentionalDeadlock {

	public static void main(String[] args) throws InterruptedException {
		SharedResource1 sharedResource1 = new SharedResource1(20);
		SharedResource2 sharedResource2 = new SharedResource2(34);

		Lock lock1 = new ReentrantLock();
		Lock lock2 = new ReentrantLock();

		Thread t1 = new Thread(new ThreadA(sharedResource1, sharedResource2, lock1, lock2), "ThreadA");
		Thread t2 = new Thread(new ThreadB(sharedResource1, sharedResource2, lock1, lock2), "ThreadB");

		System.out.println("Thread a status; " + t1.getState() + " and Thread B status: " + t2.getState());

		t1.start();
//		Thread.sleep(40);
		t2.start();
//		Thread.sleep(40);

		System.out.println("Thread a status; " + t1.getState() + " and Thread B status: " + t2.getState());
	}

}
