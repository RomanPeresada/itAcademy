package task1.num3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reading implements Runnable {
    private final Storage storage;

    public Reading(Storage storage) {
        this.storage = storage;
    }


    private void readBytesFromFile() throws InterruptedException, IOException {
        synchronized (storage) {
            storage.wait();
            while (!storage.isDone()) {
                File file = new File(storage.getPathToFile());
                FileInputStream fileInputStream = new FileInputStream(file);
                int temp;
                List<Integer> arrayOfBytes = new ArrayList<>();
                while ((temp = fileInputStream.read()) != -1) {
                    arrayOfBytes.add(temp);
                }
                fileInputStream.close();
                storage.setBytesFromFile(arrayOfBytes);
                searchTheLongestSequence();
                storage.notify();
                storage.wait();
            }

        }
    }

    private void searchTheLongestSequence() throws InterruptedException {
        List<Integer> bytes = storage.getBytesFromFile();
        List<List<Integer>> lists = new ArrayList<>(); //для хранения всех существующих последовательностей
        int maxLength = 0, firstIndex = 0, secondIndex = 0;
        String theLongestSequence = "";
        for (int i = 0; i < bytes.size() - 1; i++) {
            for (int j = i + 1; j < bytes.size(); j++) {
                List<Integer> currentSequence = bytes.subList(i, j);
                if (theLongestSequence.length() > currentSequence.toString().length()) {
                    continue;
                }
                if (currentSequence.toString().equals(theLongestSequence)) {
                    secondIndex = i;
                }
                if (bytes.subList(i + 1, bytes.size()).toString().contains(currentSequence.toString().substring(1, currentSequence.toString().length() - 1))) {
                    lists.add(currentSequence);
                    if (currentSequence.size() > maxLength) {
                        maxLength = currentSequence.size();
                        firstIndex = i;
                        theLongestSequence = currentSequence.toString();
                    }
                }
            }
        }
        lists.sort((o1, o2) -> Integer.compare(o2.size(), o1.size()));
        if (lists.size() > 0) {
            storage.setTheLongestSequence(lists.get(0));
            storage.setFirstIndexOfSequence(firstIndex);
            storage.setSecondIndexOfSequence(secondIndex);
        }
    }

    @Override
    public void run() {
        try {
            readBytesFromFile();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
