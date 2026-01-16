import java.util.ArrayList;
import java.util.List;

class Buffer {
	private final List<Integer> items = new ArrayList<>();
	private final int capacity = 5;

	public void addElement(int item) {
		items.add(item);
	}

	public int getCapacity() {
		return capacity;
	}

	public List<Integer> getItems() {
		return items;
	}
}

class Producer implements Runnable {
	private final Buffer buffer;

	public Producer(Buffer buffer) {
		this.buffer = buffer;
	}

	@Override
	public void run() {
		int value = 1;

	}

}

class Consumer {

}

public class ProducerConsumerDemo {

	public static void main(String[] args) {
		Buffer buffer = new Buffer();

	}

}
