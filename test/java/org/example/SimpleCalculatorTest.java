package org.example;

import junit.framework.TestCase;

public class SimpleCalculatorTest extends TestCase {

    public void testAdd() {
        SimpleCalculator sc = new SimpleCalculator();
        assertEquals(7,sc.add(3,4));

    }public void testMultiply() {
        SimpleCalculator sc = new SimpleCalculator();
        assertEquals(12,sc.multiply(3,4));

    }public void testSubtract() {
        SimpleCalculator sc = new SimpleCalculator();
        assertEquals(-1,sc.subtract(3,4));

    }public void testDivide() {
        SimpleCalculator sc = new SimpleCalculator();
        assertEquals((double)Integer.MAX_VALUE,sc.divide(3,0));

    }
}