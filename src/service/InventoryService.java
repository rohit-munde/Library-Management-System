package service;

import entity.Inventory;
import util.DataStore;

import java.util.Scanner;

public class InventoryService {
    private final DataStore<Inventory> inventoryStore = new DataStore<>();
    private final Scanner sc = new Scanner(System.in);

    public void chooseInventoryMenu() {
        System.out.println("Book inventory management menu opened");
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    addInvesntory();
                    break;
                case 2:
                    getInventorybyBookId();
                    break;
                case 3:
                    borrowBook();
                    break;
                case 4:
                    returnBook();
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
        System.out.println("1. Add inventory for a book");
        System.out.println("2. View inventory for a book");
        System.out.println("3. Borrow a book");
        System.out.println("4. Return a book");
        System.out.println("0. Return to main menu");
        System.out.print("Enter your choice: ");
    }

    public void addInvesntory() {
        System.out.print("Book ID: ");
        int bookId = sc.nextInt();
        System.out.print("Number of copies to add: ");
        int numCopies = sc.nextInt();
        for (int i = 0; i < numCopies; i++) {
            Inventory inventory = new Inventory(bookId);
            inventoryStore.create(inventory.getCopyId(), inventory);
        }
    }

    public void getInventorybyBookId() {
        System.out.print("Book ID to view inventory: ");
        int bookId = sc.nextInt();

        var inventories = inventoryStore.readAll().values().stream()
                .filter(inv -> inv.getBookId() == bookId)
                .toList();

        if (inventories.isEmpty()) {
            System.out.println("No inventory found for book ID: " + bookId);
            return;
        }

        // Print table header
        System.out.println("\n" + String.format("%-10s %-10s %-15s %-20s %-15s",
                "Copy ID", "Book ID", "Status", "Borrowed By", "Borrow Date"));
        System.out.println("-".repeat(70));

        // Print table rows
        for (Inventory inv : inventories) {
            String borrowedBy = inv.getBorrowedByPatronId() != null ?
                    String.valueOf(inv.getBorrowedByPatronId()) : "N/A";
            String borrowDate = inv.getBorrowDate() != null ?
                    inv.getBorrowDate().toString() : "N/A";

            System.out.println(String.format("%-10s %-10s %-15s %-20s %-15s",
                    inv.getCopyId(), inv.getBookId(), inv.getStatus(), borrowedBy, borrowDate));
        }
    }

    public void borrowBook() {
        System.out.print("Enter patron ID: ");
        int patronId = sc.nextInt();
        System.out.print("Enter book ID to borrow: ");
        int bookId = sc.nextInt();

        Inventory inventory = inventoryStore.readAll().values().stream()
                .filter(inv -> inv.getBookId() == bookId && inv.getStatus() == enums.BookStatus.AVAILABLE)
                .findFirst()
                .orElse(null);

        if (inventory != null) {
            inventory.setStatus(enums.BookStatus.BORROWED);
            inventory.setBorrowedByPatronId(patronId);
            inventory.setBorrowDate(java.time.LocalDate.now());
            inventoryStore.update(inventory.getCopyId(), inventory);
            System.out.println("Book borrowed successfully! Copy ID: " + inventory.getCopyId());
        } else {
            System.out.println("No available copy for book ID: " + bookId);
        }
    }

    public void returnBook() {
        System.out.println("Enter copy ID to return: ");
        int copyId = sc.nextInt();
        Inventory inventory = inventoryStore.read(copyId);
        if (inventory != null && inventory.getStatus() == enums.BookStatus.BORROWED) {
            inventory.setStatus(enums.BookStatus.AVAILABLE);
            inventory.setBorrowedByPatronId(null);
            inventory.setBorrowDate(null);
            inventoryStore.update(copyId, inventory);
        } else {
            System.out.println("Invalid copy ID or book is not currently borrowed.");
        }
    }
}
