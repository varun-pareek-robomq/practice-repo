package org.example;

public class SimpleCalculator {
    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public double divide(int a, int b) {
        if(b==0){
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return (double) a/b;
    }
    public int multiply(int a,int b){
        return a*b;
    }

    public int produceMaxResult(int a,int b){
        if(a==Integer.MAX_VALUE || b==Integer.MAX_VALUE){
            throw new IllegalArgumentException("Cannot add to maximum value, Result overflow!!");
        }
        return -1;
    }


    public static void main(String[] args) {
        SimpleCalculator sc= new SimpleCalculator();
//        System.out.println(Integer.MAX_VALUE -1);
//        System.out.println(Integer.MAX_VALUE );
        try{
        System.out.println(sc.produceMaxResult(2147483647,1));
        }
        catch (IllegalArgumentException e){
            System.out.println(e);
        }

    }


}
