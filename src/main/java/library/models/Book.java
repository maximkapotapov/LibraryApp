package library.models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    @NotEmpty(message = "Title should not be empty!")
    @Column(name = "title_of_book")
    private String titleOfBook;

    @NotEmpty(message = "Author should not be empty!")
    @Column(name = "author_of_book")
    private String authorOfBook;

    @Max(value = 2023, message = "Incorrect year of publishing the book")
    @Column(name = "age_of_book")
    private int ageOfBook;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    private Person owner;

    public Book() {

    }


    public Book(String titleOfBook, String authorOfBook, int ageOfBook) {
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

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}
