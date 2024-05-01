package org.example.chapter3;

/**
 * Created by Artyom Zheltyshev on 30.04.2024
 * Пример использования аппаратного кэша
 */
public class Caching {

    private static final int ARR_SIZE = 2 * 1024 * 1024;
    private final int[] testData = new int[ARR_SIZE];

    private void run() {
        System.out.println("Старт: "+ System.currentTimeMillis());

        for (int i = 0; i < 15_000; i++) {
            touchEveryLine();
            touchEveryItem();
        }

        System.out.println("Разогрев закончен: " + System.currentTimeMillis());
        System.out.println("Элемент\tСтрока");

        for (int i = 0; i < 100; i++) {
            long t0 = System.nanoTime();
            touchEveryLine();
            long t1 = System.nanoTime();
            touchEveryItem();
            long t2 = System.nanoTime();
            long elItem = t2 - t1;
            long elLine = t1 - t0;
            long diff = elItem - elLine;
            System.out.println(elItem + "\t" + elLine + "\t" + (100 * diff / elLine));
        }
    }

    private void touchEveryItem() {
        for (int i = 0; i < testData.length; i++) {
            testData[i]++;
        }
    }

    private void touchEveryLine() {
        for (int i = 0; i < testData.length; i += 16) {
            testData[i]++;
        }
    }

    public static void main(String[] args) {
        Caching c = new Caching();
        c.run();
    }

}
