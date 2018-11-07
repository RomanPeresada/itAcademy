package task1.num1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Storage storage = new Storage();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a start number");
        int usersStartNumber = scanner.nextInt();
        System.out.println("Enter a finish number");
        int usersFinishNumber = scanner.nextInt();
        System.out.println("Enter a count of threads count");
        int countOfThreads = scanner.nextInt();
        Thread[] threads = new Thread[countOfThreads];
        int step;


        if (usersFinishNumber - usersStartNumber >= 0 && usersFinishNumber - usersStartNumber <= 100) {
            step = 10;
        } else if (usersFinishNumber - usersStartNumber > 100 && usersFinishNumber - usersStartNumber <= 1000) {
            step = 50;
        } else if (usersFinishNumber - usersStartNumber > 1000 && usersFinishNumber - usersStartNumber <= 100_000) {
            step = 400;
        } else {
            step = 700;
        }

        int startBound = usersStartNumber;
        int finishBound;
        if (usersStartNumber + step > usersFinishNumber) {
            finishBound = usersFinishNumber;
        } else {
            finishBound = usersStartNumber + step;
        }
        while (finishBound != usersFinishNumber) {
            for (int i = 0; i < threads.length; i++) {
                if (threads[i] == null || !threads[i].isAlive()) {
                    threads[i] = new Thread(new SearchingSimpleNumbers(startBound, finishBound, storage));
                    startBound = finishBound + 1;
                    if (finishBound + step > usersFinishNumber) {
                        finishBound = usersFinishNumber;
                    } else {
                        finishBound = finishBound + step;
                    }
                    threads[i].start();
                    threads[i].join();
                }
            }
        }

        // storage.showNumbers();
        System.out.println("Count of simple numbers = " + storage.getSimpleNumbers().size());
    }
}