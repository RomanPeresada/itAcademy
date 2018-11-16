package task1.num1;

import java.util.*;

public class Storage {
    private Collection<Integer> simpleNumbers = new TreeSet<>();

    public void addNumber(int number) {
        simpleNumbers.add(number);
    }

    public Collection<Integer> getSimpleNumbers() {
        return Collections.unmodifiableCollection(simpleNumbers);
    }

    public void showNumbers(){
        for (Integer simpleNumber : simpleNumbers) {
            System.out.println(simpleNumber);
        }
    }

}
