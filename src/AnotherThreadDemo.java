
class AnotherThreadDemo extends Thread {

	@Override
	public void run() {
		for (int i = 0; i < 10_00_00_000; i++) {
			System.out.println("Hello");

		}

	}

}
