package task1.num1;

public class SearchingSimpleNumbers implements Runnable {
    private int startNumber;
    private int finishNumber;
    private final Storage storage;

    public SearchingSimpleNumbers(int startNumber, int finishNumber, Storage storage) {
        this.startNumber = startNumber;
        this.finishNumber = finishNumber;
        this.storage = storage;
    }

    public void findSimpleNumbers() {
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
        System.out.printf("%s from %d to %d, time of execution = %dms, count of simple numbers = %d\n", Thread.currentThread().getName(), startNumber, finishNumber, (finish - start),counter);

    }

    @Override
    public void run() {
        findSimpleNumbers();
    }

}
