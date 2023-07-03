package library.services;

import library.models.Book;
import library.models.Person;
import library.repositories.BookRepository;
import library.repositories.PersonRepository;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
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
    }

    @Transactional
    public void delete(int id) {
        bookRepository.deleteById(id);
    }
}
