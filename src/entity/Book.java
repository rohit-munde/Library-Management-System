package entity;

import util.IdGenerator;

public class Book {
    private final int id;
    private String title;
    private String author;
    private String isbn;
    private int publicationYear;

    public Book() {
        this.id = IdGenerator.nextBookId();
    }

    public Book(String title, String author, String isbn, int publicationYear) {
        this.id = IdGenerator.nextBookId();
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Book{" + "Id=" + id + ", Title='" + title + '\'' + ", Author='" + author + '\'' + ", ISBN='" + isbn + '\'' + ", Publication-Year=" + publicationYear + '}';
    }
}
