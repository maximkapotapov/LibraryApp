package library.models;

public class Book {
    private int bookId;
    private String titleOfBook;
    private String authorOfBook;
    private int ageOfBook;

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
