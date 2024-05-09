package org.example.chapter6;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Artyom Zheltyshev on 09.05.2024
 * Модель для исследования влияния выделения памяти на сборку мусора
 */
public class ModelAllocator implements Runnable {

    private volatile boolean shutdown = false;
    private double chanceOfLongLived = 0.02;    // Вероятность создания долгоживущего объекта, который скорее всего попадёт в старое поколение
    private int multiplierForLongLived = 20;    // Во столько раз долгоживущий объект живёт дольше, чем обычный
    private int x = 1024;                       // Объём одной аллокации - x
    private int y = 1024;                       // Объём одной аллокации - y
    private int mbPerSec = 50;                  // Частота аллокаций в секунду
    private int shortLivedMs = 100;             // Кол-во мс, которое живёт обычный объект
    private int nThreads = 8;                   // Размер пула потоков
    private Executor exec = Executors.newFixedThreadPool(nThreads);

    @Override
    public void run() {
        final int mainSleep = (int) (1000.0 / mbPerSec);

        while (!shutdown) {
            for (int i = 0; i < mbPerSec; i++) {
                ModelObjectAllocation to = new ModelObjectAllocation(x, y, lifetime());
                exec.execute(to);

                try {
                    Thread.sleep(mainSleep);
                } catch (InterruptedException ex) {
                    shutdown = true;
                }
            }
        }
    }

    // Случайное время жизни объекта: малое или длительное
    public int lifetime() {
        if (Math.random() < chanceOfLongLived) {
            return multiplierForLongLived * shortLivedMs;
        }
        return shortLivedMs;
    }

    public static void main(String[] args) {
        new ModelAllocator().run();
    }
}
