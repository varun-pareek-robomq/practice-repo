import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

class RateLimiter {
	private final int MAX_REQUESTS = 5;
	private final long WINDOW_SIZE = 60_000;

	private final Map<String, Deque<Long>> userRequests = new HashMap<>();

	public boolean allowRequest(String userId) {
		long now = System.currentTimeMillis();

		userRequests.putIfAbsent(userId, new ArrayDeque<>());

		Deque<Long> timestamps = userRequests.get(userId);

		while (!timestamps.isEmpty() && now - timestamps.peekFirst() > WINDOW_SIZE) {
			timestamps.pollFirst();
		}

		if (timestamps.size() < MAX_REQUESTS) {
			timestamps.addLast(now);
			return true;
		}
		return false;
	}

}

public class APIRateLimiting {

	public static void main(String[] args) {
		RateLimiter limiter = new RateLimiter();
		String user = "user123";

		for (int i = 0; i <= 7; i++) {
			System.out.println("Request" + i + ": " + (limiter.allowRequest(user) ? "Allowed" : "Blocked"));

		}

	}

}
