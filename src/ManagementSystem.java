import service.BookService;
import service.InventoryService;
import service.PatronService;

import java.util.Scanner;

public class ManagementSystem {
    public static void main(String[] args) {
        BookService bookService = new BookService();
        PatronService patronService = new PatronService();
        InventoryService inventoryService = new InventoryService();
        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = new Scanner(System.in).nextInt();
            switch (choice) {
                case 1:
                    bookService.chooseBookMenu();
                    break;
                case 2:
                    patronService.choosePatronMenu();
                    break;
                case 3:
                    inventoryService.chooseInventoryMenu();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    public static void displayMainMenu() {
        System.out.println("\n--- Library Management System ---");
        System.out.println("1. Manage Books");
        System.out.println("2. Manage Patrons");
        System.out.println("3. Manage Inventory");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }
}
