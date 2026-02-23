# Library Management System

A comprehensive Java-based Library Management System designed to help librarians manage books, patrons, and lending processes efficiently. This project demonstrates Object-Oriented Programming (OOP) principles, SOLID design principles, and relevant design patterns.

## 📋 Table of Contents

- [Features](#features)
- [System Requirements](#system-requirements)
- [Project Structure](#project-structure)
- [Architecture Overview](#architecture-overview)
- [Class Diagram](#class-diagram)
- [Core Components](#core-components)
- [SOLID Principles Applied](#solid-principles-applied)
- [Design Patterns Used](#design-patterns-used)
- [How to Run](#how-to-run)
- [Usage Guide](#usage-guide)
- [Future Enhancements](#future-enhancements)

## ✨ Features

### Core Requirements (Implemented)
- ✅ **Book Management**
  - Add new books to the library
  - View all books with details
  - Update book information
  - Delete/Remove books
  - Store books by ISBN, title, author, and publication year

- ✅ **Patron Management**
  - Add new library members (patrons)
  - View all registered patrons
  - Update patron information
  - Remove patrons from the system

- ✅ **Inventory Management**
  - Add multiple copies of books to inventory
  - Track individual book copies with unique Copy IDs
  - View inventory status for each book
  - Monitor available and borrowed copies

- ✅ **Lending Process**
  - Check out books (borrow functionality)
  - Return borrowed books
  - Track which patron borrowed which copy
  - Track borrow dates for each transaction

## 🔧 System Requirements

- **Java Version**: Java 8 or higher
- **IDE**: IntelliJ IDEA (recommended)
- **Build Tool**: No external dependencies (uses pure Java)
- **Operating System**: Windows, macOS, Linux

## 📁 Project Structure

```
LibraryManagementSystem/
├── src/
│   ├── ManagementSystem.java          # Main entry point
│   ├── entity/                         # Core entities
│   │   ├── Book.java
│   │   ├── Patron.java
│   │   └── Inventory.java
│   ├── service/                        # Business logic layer
│   │   ├── BookService.java
│   │   ├── PatronService.java
│   │   ├── InventoryService.java
│   │   └── interfaces/
│   │       └── IInventoryService.java
│   ├── enums/                          # Enumerations
│   │   └── BookStatus.java
│   └── util/                           # Utility classes
│       ├── DataStore.java              # Generic data store
│       └── IdGenerator.java            # ID generation utility
├── README.md                            # This file
└── LibraryManagementSystem.iml         # IntelliJ project config
```

## 🏗️ Architecture Overview

The system follows a **three-tier architecture**:

1. **Presentation Layer**: Menu-driven command-line interface
2. **Business Logic Layer**: Services (BookService, PatronService, InventoryService)
3. **Data Layer**: DataStore utility for in-memory storage

### Data Flow

```
User Input (CLI)
     ↓
ManagementSystem (Main Menu)
     ↓
Service Classes (BookService, PatronService, InventoryService)
     ↓
Entity Classes (Book, Patron, Inventory)
     ↓
DataStore (Generic in-memory storage)
```

## 📊 Class Diagram

```
┌─────────────────────────────────────────────────────────────────┐
│                      ManagementSystem                           │
│                    (Main Entry Point)                           │
└─────────┬───────────────────────┬───────────────────────┬───────┘
          │                       │                       │
          ▼                       ▼                       ▼
┌──────────────────┐   ┌──────────────────┐   ┌────────────────────┐
│  BookService     │   │ PatronService    │   │ InventoryService   │
├──────────────────┤   ├──────────────────┤   ├────────────────────┤
│ - bookStore      │   │ - patronStore    │   │ - inventoryStore   │
│ - scanner        │   │ - scanner        │   │ - scanner          │
├──────────────────┤   ├──────────────────┤   ├────────────────────┤
│ + add()          │   │ + add()          │   │ + addInventory()   │
│ + viewAllBooks() │   │ + viewAllPatrons │   │ + viewInventory()  │
│ + update()       │   │ + update()       │   │ + borrowBook()     │
│ + remove()       │   │ + remove()       │   │ + returnBook()     │
└────────┬─────────┘   └────────┬─────────┘   └────────┬───────────┘
         │                      │                      │
         │ uses                 │ uses                 │ uses
         ▼                      ▼                      ▼
    ┌─────────┐            ┌─────────┐          ┌──────────────┐
    │  Book   │            │ Patron  │          │  Inventory   │
    ├─────────┤            ├─────────┤          ├──────────────┤
    │ - id    │            │ - id    │          │ - copyId     │
    │ - title │            │ - name  │          │ - bookId     │
    │ - author│            │ - email │          │ - status     │
    │ - isbn  │            │         │          │ - borrowedBy │
    │ - year  │            │         │          │ - borrowDate │
    └─────────┘            └─────────┘          └──────────────┘
         ▲                                             │
         │                                             │ references
         │ referenced by                               │
         │                                             ▼
         │                                      ┌──────────────┐
         │                                      │  BookStatus  │
         │                                      ├──────────────┤
         └──────────────────────────────────────│ AVAILABLE    │
                                                │ BORROWED     │
                                                └──────────────┘

┌────────────────────┐        ┌──────────────────┐
│   DataStore<T>     │        │   IdGenerator    │
├────────────────────┤        ├──────────────────┤
│ - data: Map<K,T>   │        │ + nextBookId()   │
├────────────────────┤        │ + nextPatronId() │
│ + create()         │        │ + nextCopyId()   │
│ + read()           │        └──────────────────┘
│ + readAll()        │
│ + update()         │
│ + delete()         │
│ + printAll()       │
└────────────────────┘
```

## 🔑 Core Components

### 1. **Book Entity** (`Book.java`)
Represents a book in the library.

**Attributes:**
- `id` (int): Unique identifier (auto-generated)
- `title` (String): Book title
- `author` (String): Author name
- `isbn` (String): ISBN number
- `publicationYear` (int): Year of publication

**Key Methods:**
- Getters and setters for all attributes
- `toString()`: Display book details

### 2. **Patron Entity** (`Patron.java`)
Represents a library member.

**Attributes:**
- `id` (int): Unique identifier (auto-generated)
- `name` (String): Patron name
- `email` (String): Email address

**Key Methods:**
- `setName()`, `setEmail()`: Update patron information
- `toString()`: Display patron details

### 3. **Inventory Entity** (`Inventory.java`)
Represents a physical copy of a book.

**Attributes:**
- `copyId` (int): Unique identifier for this copy
- `bookId` (int): Reference to the Book
- `status` (BookStatus): AVAILABLE or BORROWED
- `borrowedByPatronId` (Integer): Patron ID if borrowed
- `borrowDate` (LocalDate): Date when borrowed

**Key Methods:**
- Getters and setters for managing copy status
- Track borrowing information in real-time

### 4. **BookService** (`BookService.java`)
Handles all book-related operations.

**Responsibilities:**
- Add new books
- View all books
- Update book information
- Delete books
- Menu-driven interaction

### 5. **PatronService** (`PatronService.java`)
Manages patron operations.

**Responsibilities:**
- Add new patrons
- View all patrons
- Update patron details
- Remove patrons
- Menu-driven interaction

### 6. **InventoryService** (`InventoryService.java`)
Manages book inventory and lending.

**Responsibilities:**
- Add inventory copies for books
- View inventory status for each book
- Process book checkouts (borrowing)
- Process book returns
- Display inventory in tabular format

### 7. **DataStore** (`DataStore.java`)
Generic in-memory data storage utility.

**Features:**
- Type-safe generic storage using `Map<K, T>`
- CRUD operations (Create, Read, Update, Delete)
- Print all entries in formatted table

### 8. **IdGenerator** (`IdGenerator.java`)
Generates unique IDs for all entities.

**Methods:**
- `nextBookId()`: Generate unique book IDs
- `nextPatronId()`: Generate unique patron IDs
- `nextCopyId()`: Generate unique copy IDs
- Thread-safe counter using `synchronized`

### 9. **BookStatus Enum** (`BookStatus.java`)
Defines possible states for book copies.

**States:**
- `AVAILABLE`: Copy is available for borrowing
- `BORROWED`: Copy is currently with a patron

## 🎯 SOLID Principles Applied

### 1. **Single Responsibility Principle (SRP)**
Each class has one reason to change:
- `Book` manages book data
- `Patron` manages patron data
- `Inventory` manages copy information
- `BookService` handles book operations only
- `PatronService` handles patron operations only
- `InventoryService` handles inventory operations only

### 2. **Open/Closed Principle (OCP)**
- Services implement interfaces (`IInventoryService`)
- Can extend functionality through inheritance
- New features can be added by extending, not modifying

### 3. **Liskov Substitution Principle (LSP)**
- Services can be substituted with their implementations
- Interface contracts are properly maintained

### 4. **Interface Segregation Principle (ISP)**
- `IInventoryService` interface segregates specific operations
- Clients depend on focused, specific interfaces

### 5. **Dependency Inversion Principle (DIP)**
- Services depend on `DataStore` abstraction, not concrete implementations
- High-level modules (services) don't depend on low-level modules (entities)

## 🎨 Design Patterns Used

### 1. **Generic Repository Pattern**
**File**: `DataStore.java`

Provides abstraction for data persistence:
```java
public class DataStore<T> {
    private final Map<K, T> data = new HashMap<>();
    
    public void create(K key, T value) { ... }
    public T read(K key) { ... }
    public void update(K key, T value) { ... }
    public T delete(K key) { ... }
}
```

**Benefits:**
- Type-safe storage
- Reusable across all entity types
- Decouples business logic from storage implementation

### 2. **Singleton Pattern** (Implied)
**File**: `IdGenerator.java`

Thread-safe ID generation:
```java
public class IdGenerator {
    private static volatile int bookIdCounter = 0;
    private static volatile int patronIdCounter = 0;
    private static volatile int copyIdCounter = 0;
    
    public synchronized static int nextBookId() { ... }
    public synchronized static int nextPatronId() { ... }
    public synchronized static int nextCopyId() { ... }
}
```

**Benefits:**
- Ensures unique IDs across the application
- Thread-safe counter mechanism
- Prevents duplicate ID generation

### 3. **Factory Pattern** (Implicit)
Entity constructors act as factories:
```java
new Book(title, author, isbn, year)
new Patron(name, email)
new Inventory(bookId)
```

**Benefits:**
- Centralized object creation
- Automatic ID assignment
- Initialization of default values

## 🚀 How to Run

### 1. **Using IntelliJ IDEA**
```bash
1. Open the project in IntelliJ IDEA
2. Right-click on ManagementSystem.java
3. Click "Run 'ManagementSystem.main()'"
```

### 2. **Using Command Line**
```bash
cd /Users/rohitmunde/IdeaProjects/LibraryManagementSystem

# Compile
javac -d out/production/LibraryManagementSystem src/ManagementSystem.java \
      src/entity/*.java src/service/*.java src/enums/*.java src/util/*.java

# Run
java -cp out/production/LibraryManagementSystem ManagementSystem
```

### 3. **From the Project Root**
```bash
cd src
javac -d ../out ManagementSystem.java entity/*.java service/*.java enums/*.java util/*.java
cd ../out
java ManagementSystem
```

## 📖 Usage Guide

### Main Menu
```
--- Library Management System ---
1. Manage Books
2. Manage Patrons
3. Manage Inventory
0. Exit
Enter your choice: 
```

### Book Management (Option 1)
```
1. Add a new book
2. View all books
3. Update a book
4. Delete a book
0. Return to main menu
```

**Example - Add Book:**
```
Book name: Effective Java
Author: Joshua Bloch
ISBN: 978-0134685991
Publication year: 2018
✓ Book added: Book{Id=1, Title='Effective Java', Author='Joshua Bloch', ...}
```

### Patron Management (Option 2)
```
1. Add a new patron
2. View all patrons
3. Update a patron
4. Delete a patron
0. Return to main menu
```

**Example - Add Patron:**
```
Patron name: John Doe
Email: john@example.com
✓ Patron added: Patron{id=1, name='John Doe', email='john@example.com'}
```

### Inventory Management (Option 3)
```
1. Add inventory for a book
2. View inventory for a book
3. Borrow a book
4. Return a book
0. Return to main menu
```

**Example - Add Inventory:**
```
Book ID: 1
Number of copies to add: 3
```

**Example - View Inventory:**
```
Copy ID    Book ID    Status          Borrowed By          Borrow Date    
----------------------------------------------------------------------
1          1          AVAILABLE       N/A                  N/A            
2          1          BORROWED        5                    2026-01-15     
3          1          AVAILABLE       N/A                  N/A
```

**Example - Borrow Book:**
```
Enter patron ID: 5
Enter book ID to borrow: 1
Book borrowed successfully! Copy ID: 1
```

**Example - Return Book:**
```
Enter copy ID to return: 1
✓ Book returned successfully
```

## 🔐 Key Implementation Details

### Thread-Safe ID Generation
```java
public synchronized static int nextBookId() {
    return ++bookIdCounter;
}
```
- Uses `synchronized` keyword for thread safety
- Prevents duplicate IDs in multi-threaded environments

### Menu-Driven Architecture
```java
boolean running = true;
while (running) {
    displayMenu();
    int choice = scanner.nextInt();
    switch(choice) {
        // Handle options
    }
}
```
- Provides continuous interaction
- Clean separation of menu logic

### Tabular Display
```java
System.out.println(String.format("%-10s %-10s %-15s %-20s %-15s",
    inv.getCopyId(), inv.getBookId(), inv.getStatus(), ...));
```
- Left-aligned formatted output
- Professional data presentation

## 🎓 Learning Outcomes

This project demonstrates:
- ✅ Object-Oriented Design principles
- ✅ SOLID principles implementation
- ✅ Design pattern usage
- ✅ Generics and type safety
- ✅ Collection API usage (HashMap, List, Stream)
- ✅ Exception handling patterns
- ✅ Menu-driven application design
- ✅ Code organization and modularity

## 🔮 Future Enhancements

### Core Enhancements
- [ ] **Lending Service**: Separate lending transactions from inventory
- [ ] **Search Functionality**: Search books by title, author, ISBN
- [ ] **Borrowing History**: Track complete borrowing history per patron
- [ ] **Due Dates**: Implement loan periods and overdue tracking
- [ ] **Reservations**: Allow patrons to reserve borrowed books

### Advanced Features
- [ ] **Multi-branch Support**: Support multiple library branches
- [ ] **Book Transfer**: Transfer books between branches
- [ ] **Notification System**: Alert patrons when reserved books are available
- [ ] **Recommendation System**: Recommend books based on borrowing history
- [ ] **Logging Framework**: Integrate SLF4J or Log4J
- [ ] **Persistence**: Add database integration (MySQL/PostgreSQL)

### Technical Improvements
- [ ] Unit tests using JUnit 5
- [ ] Logging implementation
- [ ] GUI using JavaFX or Swing
- [ ] REST API using Spring Boot
- [ ] Docker containerization

## 📝 Code Quality Standards

- **Naming Conventions**: Follow Java naming conventions
- **Code Comments**: Meaningful comments for complex logic
- **Encapsulation**: Private fields with public getters/setters
- **Error Handling**: Graceful handling of invalid inputs
- **Consistency**: Consistent code style throughout

## 📞 Contact & Support

For questions or suggestions, please create an issue in the GitHub repository.

## 📜 License

This project is open source and available for educational purposes.

---

**Last Updated**: February 23, 2026  
**Version**: 1.0.0  
**Status**: Core Features Complete

