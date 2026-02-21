package util;

public class IdGenerator {
    private static int bookId = 1000;

    private IdGenerator() {
    }

    public static synchronized int nextBookId() {
        return bookId++;
    }
}
