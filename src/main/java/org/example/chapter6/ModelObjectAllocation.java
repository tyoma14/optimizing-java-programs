package org.example.chapter6;

/**
 * Created by Artyom Zheltyshev on 09.05.2024
 * Модель аллокации объекта
 */
public class ModelObjectAllocation implements Runnable {

    private final int[][] allocated;
    private final int lifeTime;

    public ModelObjectAllocation(final int x, final int y, final int liveFor) {
        allocated = new int[x][y];
        lifeTime = liveFor;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(lifeTime);
            System.out.println(System.currentTimeMillis() + ": " + allocated.length);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
