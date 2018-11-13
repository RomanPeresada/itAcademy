package task1.num1;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class SplitThreadsUtil {

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

    public static List<List<Pair<Integer, Integer>>> fillAndGetListOfRequiredValues(int usersStartNumber, int usersFinishNumber, int amountOfThreads) {
        int step = getStep(usersStartNumber, usersFinishNumber);
        int startBound = usersStartNumber;
        int finishBound = getInitialFinishBound(usersStartNumber, usersFinishNumber, step);
        List<List<Pair<Integer, Integer>>> listForAllThreads = new ArrayList<>();
        int counter = 0; //необходим для того чтобы,когда finishBound == usersFinishNumber,сделать еще одну итерацию
        int positionIndexOfThread = 0;

        while (finishBound != usersFinishNumber || counter < 2) {
            if (positionIndexOfThread >= amountOfThreads) {
                positionIndexOfThread = 0;
            }
            List<Pair<Integer, Integer>> listForOneThread = new ArrayList<>();
            if (listForAllThreads.size() <= positionIndexOfThread) {
                listForOneThread.add(new Pair<>(startBound, finishBound));
                listForAllThreads.add(listForOneThread);
            } else {
                listForAllThreads.get(positionIndexOfThread).add(new Pair<>(startBound, finishBound));
            }
            startBound = finishBound + 1;
            finishBound = getNextFinishBound(finishBound, usersFinishNumber, step);
            if (finishBound == usersFinishNumber) {
                counter++;
            }
            positionIndexOfThread++;
        }
        return listForAllThreads;
    }

    private static int getInitialFinishBound(int usersStartNumber, int usersFinishNumber, int step) {
        int finishBound;
        if (usersStartNumber + step > usersFinishNumber) {
            finishBound = usersFinishNumber;
        } else {
            finishBound = usersStartNumber + step;
        }
        return finishBound;
    }

    private static int getNextFinishBound(int currentFinishBound, int usersFinishNumber, int step) {
        if (currentFinishBound + step > usersFinishNumber) {
            currentFinishBound = usersFinishNumber;
        } else {
            currentFinishBound += step;
        }
        return currentFinishBound;
    }
}
