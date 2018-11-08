package task1.num1;

import javafx.util.Pair;

import java.util.List;

public class SearchingSimpleNumbers implements Runnable {
    private final List<Pair<Integer,Integer>> pairs;
    private final Storage storage;

    public SearchingSimpleNumbers( List<Pair<Integer, Integer>> pairs, Storage storage) {
        this.pairs = pairs;
        this.storage = storage;
    }

    public void findSimpleNumbers() {
        for (Pair<Integer, Integer> pair : pairs) {
            int startNumber = pair.getKey();
            int finishNumber = pair.getValue();

            if (startNumber > finishNumber) return;
            int counter = 0;
            long start = System.currentTimeMillis();
            if (startNumber < 2) {
                startNumber = 2;
            }
            for (int number = startNumber; number <= finishNumber; number++) {
                boolean isSimple = true;
                for (int i = 2; i <= finishNumber / 2; i++) {
                    if (number > i && number % i == 0) {
                        isSimple = false;
                    }
                }
                if (isSimple) {
                    synchronized (storage) {
                        storage.addNumber(number);
                        counter++;
                    }
                }
            }
            long finish = System.currentTimeMillis();
            System.out.printf("%s from %d to %d, time of execution = %dms, amount of simple numbers = %d\n", Thread.currentThread().getName(), startNumber, finishNumber, (finish - start),counter);

        }
    }

    @Override
    public void run() {
        findSimpleNumbers();
    }

}
