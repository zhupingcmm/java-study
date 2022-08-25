package com.hero.jvm.memory;

public class StackErrorMock {
    private static int index = 1;
    private void call() {
        index++;
        call();
    }
    public static void main(String[] args) {
        StackErrorMock mock = new StackErrorMock();
        try {
            mock.call();
        } catch (Throwable e) {
            System.out.println("stack deep::" + index);
            e.printStackTrace();
        }
    }
}
