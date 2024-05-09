package org.example.chapter3;

/**
 * Created by Artyom Zheltyshev on 01.05.2024
 * Исследование накладных расходов планировщика
 */
public class ThreadScheduling {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 2000; i++) {
            Thread.sleep(2);
        }
        long end = System.currentTimeMillis();
        System.out.println("Millis elapsed: " + (end - start) / 4000.0);
    }

}
