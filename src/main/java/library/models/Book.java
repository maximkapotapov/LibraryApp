package library.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;

public class Book {
    private int bookId;

    //private int personId;

    @NotEmpty(message = "Title should not be empty!")
    private String titleOfBook;

    @NotEmpty(message = "Author should not be empty!")
    private String authorOfBook;

    @Max(value = 2024, message = "Incorrect year of publishing the book")
    private int ageOfBook;

    public Book() {

    }


    public Book(int bookId, String titleOfBook, String authorOfBook, int ageOfBook) {
        this.bookId = bookId;
        this.titleOfBook = titleOfBook;
        this.authorOfBook = authorOfBook;
        this.ageOfBook = ageOfBook;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitleOfBook() {
        return titleOfBook;
    }

    public void setTitleOfBook(String titleOfBook) {
        this.titleOfBook = titleOfBook;
    }

    public String getAuthorOfBook() {
        return authorOfBook;
    }

    public void setAuthorOfBook(String authorOfBook) {
        this.authorOfBook = authorOfBook;
    }

    public int getAgeOfBook() {
        return ageOfBook;
    }

    public void setAgeOfBook(int ageOfBook) {
        this.ageOfBook = ageOfBook;
    }
}
