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
        int startIndex = startNumber;
        int finishIndex = finishNumber / threads.length;
        int difference = finishIndex;
        for (int i = 0; i < threads.length; i++) {
            if (i == (threads.length - 1)) {
                threads[i] = new Thread(new SearchingSimpleNumbers(startIndex, finishNumber, storage));
                threads[i].start();
            } else {
                threads[i] = new Thread(new SearchingSimpleNumbers(startIndex, finishIndex, storage));
                startIndex = finishIndex + 1;
                finishIndex += difference;
                threads[i].start();
            }
        }

        for (Thread thread : threads) {
            thread.join();
        }
        // storage.showNumbers();
        System.out.println("Count of simple numbers = " + storage.getSimpleNumbers().size());
    }
}
