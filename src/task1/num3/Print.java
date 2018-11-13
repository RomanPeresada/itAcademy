package task1.num3;

import java.util.Scanner;

public class Print implements Runnable {
    private final Storage storage;

    public Print(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        synchronized (storage) {
            while (!storage.isDone()) {
                try {
                    Thread.sleep(23);
                    System.out.println("Enter a path to file");
                    String path = new Scanner(System.in).nextLine();
                    if (path.isEmpty()) {
                        storage.setDone(true);
                        storage.notify();
                        break;
                    }
                    storage.setPathToFile(path);
                    storage.notify();
                    storage.wait();
                    if (!storage.getBytesFromFile().isEmpty()) {
                        System.out.println("Current size of the longest sequence: " + storage.getTheLongestSequence().size());
                        System.out.println("This sequence: " + storage.getTheLongestSequence());
                        System.out.println("First index of occurrence: " + storage.getFirstIndexOfSequence());
                        System.out.println("Second index of occurrence: " + storage.getSecondIndexOfSequence());
                        System.out.println("Bytes from file: " + storage.getBytesFromFile() + "\n");
                    } else {
                        System.out.println("File is empty!\n");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
