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
                    Inventory inventory = getInventorybyBookId();
                    if (inventory != null) {
                        System.out.println(inventory);
                    } else {
                        System.out.println("No inventory found for book ID "); //TODO: add book ID to message
                    }
                    break;
                case 3:
                    borrowBook();
                    break;
                case 4:
                    returnBook();
                    break;
                case 5:
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

    public Inventory getInventorybyBookId() {
        System.out.print("Book ID to view inventory: ");
        int bookId = sc.nextInt();
        return inventoryStore.readAll().values().stream()
                .filter(inv -> inv.getBookId() == bookId && inv.getStatus() == enums.BookStatus.AVAILABLE)
                .findFirst()
                .orElse(null);
    }

    public void borrowBook() {
        System.out.println("Enter patron ID: ");
        int patronId = sc.nextInt();
        Inventory inventory = getInventorybyBookId();
        if (inventory != null) {
            inventory.setStatus(enums.BookStatus.BORROWED);
            inventory.setBorrowedByPatronId(patronId);
            inventory.setBorrowDate(java.time.LocalDate.now());
            inventoryStore.update(inventory.getCopyId(), inventory);
        } else {
            System.out.println("No available copy for book ID "); //TODO: add book ID to message
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
