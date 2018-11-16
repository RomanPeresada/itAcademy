package task1.num3;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Storage {
    private List<Integer> bytesFromFile;
    private boolean isDone;
    private String pathToFile;
    private Collection<Integer> theLongestSequence;
    private int firstIndexOfSequence;
    private int secondIndexOfSequence;


    public Collection<Integer> getTheLongestSequence() {
        return Collections.unmodifiableCollection(theLongestSequence);
    }

    public String getPathToFile() {
        return pathToFile;
    }

    public void setPathToFile(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public boolean isDone() {
        return isDone;
    }

    public List<Integer> getBytesFromFile() {
        return Collections.unmodifiableList(bytesFromFile);
    }

    public void setBytesFromFile(List<Integer> bytesFromFile) {
        this.bytesFromFile = bytesFromFile;
    }

    public void setTheLongestSequence(Collection<Integer> theLongestSequence) {
        this.theLongestSequence = theLongestSequence;
    }

    public int getFirstIndexOfSequence() {
        return firstIndexOfSequence;
    }

    public int getSecondIndexOfSequence() {
        return secondIndexOfSequence;
    }

    public void setFirstIndexOfSequence(int firstIndexOfSequence) {
        this.firstIndexOfSequence = firstIndexOfSequence;
    }

    public void setSecondIndexOfSequence(int secondIndexOfSequence) {
        this.secondIndexOfSequence = secondIndexOfSequence;
    }
}
