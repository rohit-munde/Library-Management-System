package service;

import entity.Patron;
import util.DataStore;

import java.util.Scanner;

public class PatronService {
    private final DataStore<Patron> patronStore = new DataStore<>();
    private final Scanner sc = new Scanner(System.in);

    public void choosePatronMenu() {
        System.out.println("Patron management menu opened");
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
                    viewAllPatrons();
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
        System.out.println("1. Add a new patron");
        System.out.println("2. View all patrons");
        System.out.println("3. Update a patron");
        System.out.println("4. Delete a patron");
        System.out.println("0. Return to main menu");
        System.out.print("Enter your choice: ");
    }

    private void add() {
        System.out.println("Patron name: ");
        String name = sc.nextLine();
        System.out.println("Email: ");
        String email = sc.nextLine();
        Patron patron = new Patron(name, email);
        patronStore.create(patron.getId() ,patron);
        System.out.println("✓ Patron added: " + patron);
    }

    private void viewAllPatrons() {
        patronStore.printAll();
    }

    private void update() {
        System.out.println("Enter patron ID to update: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline
        Patron existingPatron = patronStore.read(id);
        if (existingPatron == null) {
            System.out.println("Patron with ID " + id + " not found.");
            return;
        }
        System.out.println("New name (leave blank to keep current): ");
        String name = sc.nextLine();
        System.out.println("New email (leave blank to keep current): ");
        String email = sc.nextLine();
        if (!name.isEmpty()) {
            existingPatron.setName(name);
        }
        if (!email.isEmpty()) {
            existingPatron.setEmail(email);
        }
        patronStore.update(id, existingPatron);
    }

    private void remove() {
        System.out.println("Enter patron ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline
        Patron removedPatron = patronStore.delete(id);
        if (removedPatron != null) {
            System.out.println("✓ Patron deleted: " + removedPatron);
        }
    }
}
