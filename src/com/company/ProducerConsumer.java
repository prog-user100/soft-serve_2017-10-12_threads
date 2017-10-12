package com.company;

public class ProducerConsumer {

    private long currentTimeMillis = 0;

    public static void main(String[] args) {
        ProducerConsumer pc = new ProducerConsumer();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                pc.produce();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                pc.consume();
            }
        });

        t1.start();
        t2.start();

    }

    /*public void produce2() {
        while (true) {
            if(this.currentTimeMillis == 0) {
                this.currentTimeMillis = System.currentTimeMillis();
                System.out.println("produced time millis = " + currentTimeMillis);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }*/

    /*public void consume2() {
        while (true) {
            if(currentTimeMillis != 0) {
                System.out.println("consumed time millis = " + currentTimeMillis);
                currentTimeMillis = 0;
            }
        }
    }*/

    public void produce() {
            while (true) {
                if(this.currentTimeMillis == 0) {
                    this.currentTimeMillis = System.currentTimeMillis();
                    System.out.println("produced time millis = " + currentTimeMillis);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }

    public void consume() {
        while (true) {
            if(currentTimeMillis != 0) {
                System.out.println("consumed time millis = " + currentTimeMillis);
                currentTimeMillis = 0;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}


