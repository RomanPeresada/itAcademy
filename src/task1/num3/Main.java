package task1.num3;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final Storage storage = new Storage();
        Thread thread = new Thread(new Read(storage));
        Thread thread2 = new Thread(new Print(storage));
        thread.start();
        thread2.start();
    }
}
