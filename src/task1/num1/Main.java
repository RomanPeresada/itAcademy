package task1.task1_1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Storage storage = new Storage();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a start number");
        int startNumber = scanner.nextInt();
        System.out.println("Enter a finish number");
        int finishNumber = scanner.nextInt();
        System.out.println("Enter a count of threads count");
        int countOfThreads = scanner.nextInt();
        Thread[] threads = new Thread[countOfThreads];
        int step;


        if (finishNumber - startNumber >= 0 && finishNumber - startNumber <= 100) {
            step = 10;
        } else if (finishNumber - startNumber > 100 && finishNumber - startNumber <= 1000) {
            step = 50;
        } else if (finishNumber - startNumber > 1000 && finishNumber - startNumber <= 100_000) {
            step = 400;
        } else {
            step = 700;
        }
        int startIndex = startNumber;
        int finishIndex;
        if (startNumber + step > finishNumber) {
            finishIndex = finishNumber;
        } else {
            finishIndex = startNumber + step;
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new SearchingSimpleNumbers(startIndex, finishIndex, storage));
            startIndex = finishIndex + 1;
            if (finishIndex + step > finishNumber) {
                finishIndex = finishNumber;
            } else {
                finishIndex = finishIndex + step;
            }
            threads[i].start();
        }

        while (finishIndex != finishNumber) {
            for (int i = 0; i < threads.length; i++) {
                if (!threads[i].isAlive()) {
                    threads[i] = new Thread(new SearchingSimpleNumbers(startIndex, finishIndex, storage));
                    startIndex = finishIndex + 1;
                    if (finishIndex + step > finishNumber) {
                        finishIndex = finishNumber;
                    } else {
                        finishIndex = finishIndex + step;
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
