package ConcurrencyTask;

import java.util.LinkedList;
import java.util.Queue;

class Task {
	private final int taskId;
	private final int submittedBy;
	private final long timeStamp;

	enum Status {
		PENDING, PROCESSING, COMPLETED
	}

	private Status status;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getTaskId() {
		return taskId;
	}

	public int getSubmittedBy() {
		return submittedBy;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public Task(int taskId, int submittedBy, Status status) {
		super();
		this.taskId = taskId;
		this.submittedBy = submittedBy;
		this.timeStamp = System.currentTimeMillis();
		this.status = status;
	}

}

class TaskQueue {
	private final Queue<Task> taskQueue = new LinkedList<>();
	private final int capacity = 10;

	public synchronized void put(Task task) {
		while (taskQueue.size() == capacity) {
			try {
				wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				e.printStackTrace();
			}
			System.out.println("Producer" + Thread.currentThread().getName() + " waiting to produce");
		}
		taskQueue.add(task);
		System.out.println(Thread.currentThread().getName() + " PRODUCED task " + task.getTaskId() + " | size="
				+ taskQueue.size());
		notifyAll();
	}

	public synchronized Task take() {
		while (taskQueue.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				Thread.currentThread().interrupt();
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " waiting to consume");
		}
		Task polledTask = taskQueue.poll();
		System.out.println(Thread.currentThread().getName() + " CONSUMED task " + polledTask.getTaskId() + " | size="
				+ taskQueue.size());
		notifyAll();
		return polledTask;

//			System.out.println(Thread.currentThread().getName() + " FOUND QUEUE EMPTY");

	}

}

class Producer implements Runnable {
	int t = 100;
	private final TaskQueue taskQueue;
	private static int taskCounter = 0;

	public Producer(TaskQueue taskQueue) {
		super();
		this.taskQueue = taskQueue;
	}

	@Override
	public void run() {

		for (int i = 0; i < 5; i++) {
			Task task = new Task(taskCounter++, 12, Task.Status.PENDING);
			taskQueue.put(task);
//				Thread.sleep(500); // simulate work
		}

	}

}

class Consumer implements Runnable {
	private final TaskQueue taskQueue;

	public Consumer(TaskQueue taskQueue) {
		super();
		this.taskQueue = taskQueue;
	}

	@Override
	public void run() {

		for (int i = 0; i < 5; i++) {
			Task t = taskQueue.take();
			System.out.println(t.getTaskId());
//				Thread.sleep(800); // simulate processing
		}

	}

}

public class ConcurrencyTaskClass {

	public static void main(String[] args) throws InterruptedException {
		TaskQueue taskQueue = new TaskQueue();

		Thread p1 = new Thread(new Producer(taskQueue), "Producer-1");
		Thread p2 = new Thread(new Producer(taskQueue), "Producer-2");

		Thread c1 = new Thread(new Consumer(taskQueue), "Consumer-1");
		Thread c2 = new Thread(new Consumer(taskQueue), "Consumer-2");

		p1.start();
		Thread.sleep(30);
		p2.start();
		Thread.sleep(30);
		c1.start();
		Thread.sleep(30);
		c2.start();

	}

}
