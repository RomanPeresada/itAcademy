package task1.num2;

import javafx.util.Pair;
import task1.num1.SearchingSimpleNumbers;
import task1.num1.Storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Storage storage = new Storage();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a start number");
        int usersStartNumber = scanner.nextInt();
        System.out.println("Enter a finish number");
        int usersFinishNumber = scanner.nextInt();
        System.out.println("Enter a count of threads count");
        int countOfThreads = scanner.nextInt();
        int step = getStep(usersStartNumber, usersFinishNumber);
        List<List<Pair<Integer, Integer>>> listList = fillAndGetListOfRequiredValues(usersStartNumber, usersFinishNumber, step, countOfThreads);
        // в listList хранятся списки диапазовов для каждого потока

        List<Future> futures = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(countOfThreads);
        for (int i = 0; i < countOfThreads; i++) {
            if (i < listList.size()) {
                futures.add(executorService.submit(new SearchingSimpleNumbers(listList.get(i), storage)));
            }
        }
        for (Future future : futures) {
            future.get();
        }
        executorService.shutdown();
        System.out.println("Amount of simple numbers = " + storage.getSimpleNumbers().size());
        // storage.showNumbers();
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

    private static List<List<Pair<Integer, Integer>>> fillAndGetListOfRequiredValues(int usersStartNumber, int usersFinishNumber, int step, int countOfThreads) {
        int startBound = usersStartNumber;
        int finishBound;
        if (usersStartNumber + step > usersFinishNumber) {
            finishBound = usersFinishNumber;
        } else {
            finishBound = usersStartNumber + step;
        }
        List<List<Pair<Integer, Integer>>> listList = new ArrayList<>();
        int counter = 0; //необходим для того чтобы,когда finishBound == usersFinishNumber,сделать еще одну итерацию
        int indexOfThread = 0;
        while (finishBound != usersFinishNumber || counter < 2) {
            if (indexOfThread >= countOfThreads) {
                indexOfThread = 0;
            }
            List<Pair<Integer, Integer>> pairList = new ArrayList<>();
            if (listList.size() <= indexOfThread) {
                pairList.add(new Pair<>(startBound, finishBound));
                listList.add(pairList);
            } else {
                listList.get(indexOfThread).add(new Pair<>(startBound, finishBound));
            }
            startBound = finishBound + 1;
            if (finishBound + step > usersFinishNumber) {
                finishBound = usersFinishNumber;
            } else {
                finishBound = finishBound + step;
            }
            if (finishBound == usersFinishNumber) {
                counter++;
            }
            indexOfThread++;
        }
        return listList;
    }

}