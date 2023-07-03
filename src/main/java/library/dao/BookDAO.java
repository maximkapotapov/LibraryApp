package library.dao;

import library.models.Book;
import library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public BookDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Book> showAll() {
//        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
//    }
//
//    public Optional<Book> show(String titleOfBook) {
//        return jdbcTemplate.query("SELECT * FROM Book WHERE title_of_book=?",
//                new Object[]{titleOfBook}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny();
//    }
//
//    public void addBook(Book book) {
//        jdbcTemplate.update("INSERT INTO Book(title_of_book, author_of_book, age_of_book) VALUES (?, ?, ?)",
//                book.getTitleOfBook(), book.getAuthorOfBook(), book.getAgeOfBook());
//    }
//
//    public Book show (int id) {
//        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id=?", new Object[]{id},
//                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
//    }
//
//    public void updateBook(Book book, int id) {
//        jdbcTemplate.update("UPDATE Book SET title_of_book=?, author_of_book=?, age_of_book=? WHERE book_id=?",
//                book.getTitleOfBook(), book.getAuthorOfBook(), book.getAgeOfBook(), id);
//    }
//
//    public Person showPerson(int id) {
//        return jdbcTemplate.query(
//                "SELECT full_name FROM Person JOIN Book ON person.person_id = book.person_id WHERE book_id=?",
//                new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
//    }
//
//    public void deletePersonBook(int id) {
//        jdbcTemplate.update("UPDATE Book SET person_id = NULL WHERE book_id=?", id);
//    }
//
//    public  void updatePersonBook(int id, Person person) {
//        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE book_id=?",
//                person.getPersonId(), id);
//    }
//
//    public void delete(int id) {
//        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", id);
//    }
}
