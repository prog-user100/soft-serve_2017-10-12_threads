package com.company;

public class ProduceConsumeWithSynch {
    private long currentTimeMillis = -1L;

    void produce() throws InterruptedException {
        for(int i = 0; i < 10; i++) {
            synchronized (this) {
                System.out.println("i=" + i);
                currentTimeMillis = System.currentTimeMillis();
                System.out.println(Thread.currentThread().getName() + " produced time millis = " + currentTimeMillis);
                notify();
                wait();
            }

        }
    }

    void consume() throws InterruptedException {
        while (true) {
            synchronized (this) {
                while (currentTimeMillis == -1L) {
                    wait();
                }
                System.out.println(Thread.currentThread().getName() + " consuming following time millis " + currentTimeMillis);
                currentTimeMillis = -1L;
                System.out.println("after consumed, time millis = " + currentTimeMillis);
                notify();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ProduceConsumeWithSynch pc = new ProduceConsumeWithSynch();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t2.start();
        t1.start();

        t1.join();
        t2.join();
        System.out.println("end of main");
    }
}
