package entity;

import enums.BookStatus;
import util.IdGenerator;
import java.time.LocalDate;

public class Inventory {
    private final int copyId;
    private final int bookId;
    private BookStatus status;
    private Integer borrowedByPatronId;
    private LocalDate borrowDate;

    public Inventory(int bookId) {
        this.copyId = IdGenerator.nextCopyId();
        this.bookId = bookId;
        this.status = BookStatus.AVAILABLE;
        this.borrowedByPatronId = null;
    }

    public int getCopyId() {
        return copyId;
    }

    public int getBookId() {
        return this.bookId;
    }

//    public void setBookId(int bookId) {
//        this.bookId = bookId;
//    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

//    public Integer getBorrowedByPatronId() {
//        return borrowedByPatronId;
//    }

    public void setBorrowedByPatronId(Integer patronId) {
        this.borrowedByPatronId = patronId;
    }

//    public LocalDate getBorrowDate() {
//        return borrowDate;
//    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    @Override
    public String toString() {
        return "Inventory{" + "copyId=" + copyId + ", bookId=" + bookId + ", status=" + status + ", borrowedByPatronId=" + borrowedByPatronId + ", borrowDate=" + borrowDate + '}';
    }
}
