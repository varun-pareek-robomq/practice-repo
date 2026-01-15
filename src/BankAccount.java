
class Withdraw700Task implements Runnable {
	private BankAccount bankAccount;

	public Withdraw700Task(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	@Override
	public void run() {
		bankAccount.withdraw(700);

	}

}

public class BankAccountAnotherExample {
	int balance = 1000;

	public synchronized void withdraw(int amount) {
		if (amount <= balance) {
			try {
				Thread.sleep(100);

			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace(); Not a good practice!!!!!!!

				Thread.currentThread().interrupt();
			}
			balance -= amount;
			System.out.println(
					Thread.currentThread().getName() + " withdrawing " + amount + ", remaining balance: " + balance);

		} else {
			System.out.println("Balnce insufficient!");
		}
	}

	public static void main(String[] args) {
		BankAccount acc = new BankAccount();

		Thread t1 = new Thread(new Withdraw700Task(acc), "Thread-1");
		Thread t2 = new Thread(new Withdraw700Task(acc), "Thread-2");
		try {
			Thread.sleep(100);
			t1.start();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t2.start();

	}
}