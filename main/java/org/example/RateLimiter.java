package org.example;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class RateLimiter {
//    private final Clock clock;
    private final Map<String, Deque<Long>> userRequests = new HashMap<>();



    public boolean allowRequest(String userId) {
        long now = System.currentTimeMillis();
//        THE ABOVE ONE IS NOT A GOOD PRACTICE
//        long now = clock.millis();

        userRequests.putIfAbsent(userId, new ArrayDeque<>());

        Deque<Long> timestamps = userRequests.get(userId);

        long WINDOW_SIZE = 6000;
        while (!timestamps.isEmpty() && now - timestamps.peekFirst() > WINDOW_SIZE) {
            timestamps.pollFirst();
        }

        int MAX_REQUESTS = 5;
        if (timestamps.size() < MAX_REQUESTS) {
            timestamps.addLast(now);
            return true;
        }
        return false;
    }

    public void throwExceptionMethod(int num){


    }


    public static void main(String[] args) {
        RateLimiter limiter = new RateLimiter();
        String user = "user123";

        for (int i = 0; i <= 7; i++) {
            System.out.println("Request" + i + ": " + (limiter.allowRequest(user) ? "Allowed" : "Blocked"));

        }
    }
    }

