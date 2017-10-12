package com.company;

import java.util.Scanner;
import java.util.concurrent.*;

/**
 * Create a system that reads lines from a prompt
 * and displays the length of the line using
 * the following highly improbable length calculation method:
 */
public class StringLength {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //creates the executor service
        ExecutorService executorService = Executors. newFixedThreadPool (3); //submits a task to be executed asynchronously
        while(scanner.hasNextLine()) {
            Future<String> future = executorService.submit(new Callable<String>() {
                @Override public String call() throws Exception {
                    System.out.println(Thread.currentThread().getName() + " is submitted");
                    return read();
                } });
            if(future.get().equals("!quit")) {
                executorService.shutdown();
                scanner.close();
                break;
            }
        }
        System.out.println("end of main");
    }

    public static String read() {
        String str = scanner.nextLine();
        showLength(str);
        return str;
    }


    /**
     Given the highly intensive nature of this calculation,
     you should farm off this work into a thread pool to make the most of your CPU.
     You could submit a new Runnable for each String,
     or have a BlockingQueue of Strings that a fixed set of Runnables draws from.

     Once that's all done, try adding functionality
     o that you can quit your program by typing a magic string like
     "!quit" and having that cancel in-progress tasks and shut down your executor.
     */
    public static void showLength(String str) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted, was calculating length of <" + str + ">");
            Thread.currentThread().interrupt();
            return;
        }

        System.out.println("Length of <" + str + "> is " + str.length());
    }

}

