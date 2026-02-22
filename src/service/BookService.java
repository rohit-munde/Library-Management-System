package service;

import entity.Book;
import util.DataStore;

import java.util.Scanner;

public class BookService {
    private final DataStore<Book> bookStore = new DataStore<>();
    private final Scanner sc = new Scanner(System.in);

    public void chooseBookMenu() {
        System.out.println("Book management menu opened");
        boolean running = true;
        while (running) {
           displayMenu();
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline
            switch (choice) {
                case 1:
                    add();
                    break;
                case 2:
                    viewAllBooks();
                    break;
                case 3:
                    update();
                    break;
                case 4:
                    remove();
                    break;
                case 0:
                    System.out.println("Returning to main menu...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private void displayMenu() {
        System.out.println("1. Add a new book");
        System.out.println("2. View all books");
        System.out.println("3. Update a book");
        System.out.println("4. Delete a book");
        System.out.println("0. Return to main menu");
        System.out.print("Enter your choice: ");
    }

    private void add() {
        System.out.print("Book name: ");
        String title = sc.nextLine();
        System.out.print("Author: ");
        String author = sc.nextLine();
        System.out.print("ISBN: ");
        String isbn = sc.nextLine();
        System.out.print("Publication year: ");
        int publicationYear = sc.nextInt();
        sc.nextLine(); // consume newline
        Book book = new Book(title, author, isbn, publicationYear);
        bookStore.create(book.getId(), book);
        System.out.println("✓ Book added: " + book);
    }

    private void viewAllBooks() {
            bookStore.printAll();
//        var allBooks = bookStore.readAll();
//        if (allBooks.isEmpty()) {
//            System.out.println("No books in library.");
//            return;
//        }
//        System.out.println("\n--- All Books ---");
//        allBooks.values().forEach(System.out::println);
    }

    private void update() {
        System.out.print("Enter book ID to update: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline

        Book book = bookStore.read(id);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        System.out.print("New title (leave blank to keep): ");
        String title = sc.nextLine();
        if (!title.isBlank()) book.setTitle(title);

        System.out.print("New author (leave blank to keep): ");
        String author = sc.nextLine();
        if (!author.isBlank()) book.setAuthor(author);

        System.out.print("New ISBN (leave blank to keep): ");
        String isbn = sc.nextLine();
        if (!isbn.isBlank()) book.setIsbn(isbn);

        System.out.print("New publication year (0 to keep): ");
        int year = sc.nextInt();
        sc.nextLine(); // consume newline
        if (year > 0) book.setPublicationYear(year);

        bookStore.update(id, book);
        System.out.println("✓ Book updated: " + book);
    }

    public void remove() {
        System.out.print("Enter book ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline

        Book removed = bookStore.delete(id);
        if (removed == null) {
            System.out.println("Book not found.");
        } else {
            System.out.println("✓ Book deleted: " + removed);
        }
    }
}
