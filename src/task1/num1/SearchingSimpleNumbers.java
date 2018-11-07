package task1.task1_1;

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
        if (startNumber == finishNumber) return;

        long start = System.currentTimeMillis();
        if (startNumber < 2) {
            startNumber = 2;
        }
        for (int number = startNumber; number <= finishNumber; number++) {
            boolean isSimple = true;
            for (int i = 2; i <= finishNumber / 2; i++) {

                if (number > i && i < finishNumber / 2 && number % i == 0) {
                    isSimple = false;
                }
            }
            if (isSimple) {
                synchronized (storage) {
                    storage.addNumber(number);
                }
            }
        }
        long finish = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + ", time = " + (finish - start));
    }

    @Override
    public void run() {
        findSimpleNumbers();
    }

}
