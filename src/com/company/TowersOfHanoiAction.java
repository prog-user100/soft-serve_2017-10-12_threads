package com.company;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

// http://www.javacreed.com/java-fork-join-example/   - fork join explained

public class TowersOfHanoiAction extends RecursiveAction {

    private int topN;
    private char from;
    private char inter;
    private char to;

    public TowersOfHanoiAction(int topN, char from, char inter, char to) {

        this.topN = topN;
        this.from = from;
        this.inter = inter;
        this.to = to;
    }

    @Override
    protected void compute() {
        if(topN==1) {
            System.out.println("Disk 1 from " + from + " to " + to);
            return;
        } else {
            TowersOfHanoiAction task1 = new TowersOfHanoiAction(topN - 1, from, to, inter);
            task1.fork();
            task1.join();

            System.out.println("Disk " + topN + " from " + from + " to " + to);
            TowersOfHanoiAction task2 = new TowersOfHanoiAction(topN - 1, inter, from, to);
            task2.fork();
            task2.join();
        }
    }

    public static void move(int topN, char from, char inter, char to) {
        if (topN == 1) {
            System.out.println("Disk 1 from " + from + " to " + to);
        } else {
            move(topN - 1, from, to, inter);
            System.out.println("Disk " + topN + " from " + from + " to " + to);
            move(topN - 1, inter, from, to);
        }
    }

    public static void main(String[] args) {

        System.out.println("== RECURSION ==");
        move(4, 'A', 'B', 'C');

        System.out.println();

        System.out.println("== ForkJoinPool ==");
        new ForkJoinPool().invoke(new TowersOfHanoiAction(4, 'A', 'B', 'C') );
    }

}

