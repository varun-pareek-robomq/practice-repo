
class BankAccount implements Runnable {
	int balance = 100;

	public void withdraw(int amount) {
		if (amount <= balance) {
			synchronized (this) {
				balance -= amount;
			}
			System.out.println(
					Thread.currentThread().getName() + " withdrawing " + amount + ", remaining balance: " + balance);

		} else {
			System.out.println("Balance insufficient! Thread " + Thread.currentThread().getName() + " failed");
		}
	}

	@Override
	public void run() {
		withdraw(60);

	}
}

public class FineVsCoarseGrained {

	public static void main(String[] args) {
		Runnable bankAccount = new BankAccount();
		Thread t1 = new Thread(bankAccount, "t1");
		Thread t2 = new Thread(bankAccount, "t2");
		t1.start();
		t2.start();

	}

}
