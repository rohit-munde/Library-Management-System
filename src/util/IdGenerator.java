package util;

public class IdGenerator {
    private static int bookId = 1000;
    private static int copyId = 2000;
    private static int patronId = 3000;

    private IdGenerator() {
    }

    public static synchronized int nextBookId() {
        return bookId++;
    }

    public static synchronized int nextCopyId() {
        return copyId++;
    }

    public static synchronized int nextPatronId() {
        return patronId++;
    }
}
