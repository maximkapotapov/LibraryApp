package library.services;

import library.models.Book;
import library.models.Person;
import library.repositories.BookRepository;
import org.hibernate.Session;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final BookRepository bookRepository;

    private final EntityManager entityManager;

    public BooksService(BookRepository bookRepository, EntityManager entityManager) {
        this.bookRepository = bookRepository;
        this.entityManager = entityManager;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> findByTitleOfBook(String titleOfBook) {
        return bookRepository.findByTitleOfBook(titleOfBook);
    }

    public Book findById(int id) {
        return bookRepository.findById(id).get();
    }

    @Transactional
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void updateBook (int id, Book book) {
        book.setBookId(id);
        bookRepository.save(book);
    }

    public Person findOwnerByBookId(int id) {
        Session session = entityManager.unwrap(Session.class);
        Book book = session.get(Book.class, id);
        return book.getOwner();
    }

    @Transactional
    public void deletePersonBook(int id) {
        Session session = entityManager.unwrap(Session.class);
        Book book = session.get(Book.class, id);
        book.setOwner(null);
    }

    @Transactional
    public void updatePersonBook(int id, Person person) {
        Session session = entityManager.unwrap(Session.class);
        Book book = session.get(Book.class, id);
        book.setOwner(person);
        book.setTakenAt(new Date());
    }

    @Transactional
    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    public List<Book> booksPerPage(int page, int booksPerPage) {
        return bookRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
    }

    public List<Book> sortByYear() {
        return bookRepository.findAll((Sort.by("ageOfBook")));
    }

    public List<Book> booksPerPageSorted(int page, int booksPerPage) {
        return bookRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("ageOfBook"))).getContent();
    }

    public Book findBookByTitle(String keyword) {
        if(keyword != null) {
            return bookRepository.findBookByTitleOfBookStartingWith(keyword);
        }
        return null;
    }

    public Person findOwnerOfBook(Book book) {
        if(book != null) {
            Session session = entityManager.unwrap(Session.class);
            session.merge(book);
            Person owner = book.getOwner();
            session.close();
            return owner;
        }
        return null;
    }
}
