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
        int step = getStep(usersStartNumber, usersFinishNumber);
        int startBound = usersStartNumber;
        int finishBound;
        if (usersStartNumber + step > usersFinishNumber) {
            finishBound = usersFinishNumber;
        } else {
            finishBound = usersStartNumber + step;
        }
        int counter = 0;
        while (finishBound != usersFinishNumber || counter < 2) {
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
                    if (finishBound == usersFinishNumber) {
                        counter++;
                    }
                }
            }
        }
        // storage.showNumbers();
        System.out.println("Count of simple numbers = " + storage.getSimpleNumbers().size());
    }

    private static int getStep(int start, int finish) {
        int step;
        if (finish - start >= 0 && finish - start <= 10) {
            step = 5;
        } else if (finish - start > 10 && finish - start <= 100) {
            step = 8;
        } else if (finish - start > 100 && finish - start <= 1000) {
            step = 45;
        } else if (finish - start > 1000 && finish - start <= 10000) {
            step = 100;
        } else {
            step = 700;
        }
        return step;
    }

}