package org.example;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
public class RateLimiterTest {
    RateLimiter limiter;

    @Before
    public void setup(){
        limiter = new RateLimiter();
    }

    @Test
    public void testShouldAllowRequestsUnderLimit() {
        // Given: Fresh user
        String user = "user123";
        RateLimiter obj = new RateLimiter();
        for (int i = 0; i < 5; i++) {
        assertTrue(obj.allowRequest(user));
        }

        // When: Make 5 requests
        // Then: All should be allowed
    }
    @Test
    public void testShouldAllowRequestsFromDifferentUsers() {
        String user1 = "Varun";
        String user2 = "Nothing";
        RateLimiter obj = new RateLimiter();
        assertTrue(obj.allowRequest(user1));
        assertTrue(obj.allowRequest(user2));
    }
    @Test
    public void testShouldBlockSixthRequestWithinWindow(){
        RateLimiter obj = new RateLimiter();
        String user = "Varun";
        for (int i = 0; i < 5; i++) {
            assertTrue(obj.allowRequest(user));
        }
        assertFalse(obj.allowRequest(user));
    }
    @Test
    public void testShouldAllowRequestAfterWindowExpires() throws InterruptedException {
        RateLimiter obj = new RateLimiter();
        String user = "Varun";
        for (int i = 0; i < 5; i++) {
            assertTrue(obj.allowRequest(user));
        }
        assertFalse(obj.allowRequest(user));
//        Wait for 6.1 seconds
        Thread.sleep(59_00);
        assertFalse(obj.allowRequest(user));
        Thread.sleep(2_00);
        assertTrue(obj.allowRequest(user));
    }
    @Test
    public void testShouldHandleExactlyAtWindowBoundary() throws InterruptedException {
        RateLimiter obj = new RateLimiter();
        String user = "Varun";
        for (int i = 0; i < 5; i++) {
            assertTrue(obj.allowRequest(user));
        }
        assertFalse(obj.allowRequest(user));
//        Request for 6 seconds
        Thread.sleep(60_00);
        assertTrue(obj.allowRequest(user));
        Thread.sleep(1_00);
        assertTrue(obj.allowRequest(user));
    }

    @Test
    public void normalTests(){
//        int age;
//        age = 19;
//        assertTrue("The age is less than 18", age>18);
        // message you want to show if the test fails!
//        int[] arr = {1,2,3,4};
//        int[] arr2 = {2,4,6,8};
//        assertArrayEquals(arr,arr2);
        List<Integer> obj=new ArrayList<>();
        obj=null;
        System.out.println(obj);
        assertNotNull("The object is null", obj);
    }


    @Test
    public void exceptionTests(){
        assertThrows(IllegalArgumentException.class, ()-> {
            int i = 10 / 0;

        });
    }





}