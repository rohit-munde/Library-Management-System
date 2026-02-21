import entity.Book;

public class ManagementSystem {
    public static void main(String[] args) {
        Book book = new Book("Clean Code", "Robert C. Martin", "9780132350884", 2008);
        System.out.println(book);
    }
}
