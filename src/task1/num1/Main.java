package task1.num1;

import javafx.util.Pair;

import java.util.List;
import java.util.Scanner;

import static task1.num1.SplitThreadsUtil.fillAndGetListOfRequiredValues;

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

        List<List<Pair<Integer, Integer>>> listList = fillAndGetListOfRequiredValues(usersStartNumber, usersFinishNumber, countOfThreads);
        // в listList хранятся списки диапазовов для каждого потока

        for (int i = 0; i < threads.length; i++) {
            if (i < listList.size()) {
                threads[i] = new Thread(new SearchSimpleNumbers(listList.get(i), storage));
                threads[i].start();
                threads[i].join();
            }
        }
        System.out.println("Amount of simple numbers = " + storage.getSimpleNumbers().size());
    }
}