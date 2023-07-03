package library.services;

import library.models.Book;
import library.models.Person;
import library.repositories.BookRepository;
import library.repositories.PersonRepository;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PersonRepository personRepository;

    private final EntityManager entityManager;

    public PeopleService(PersonRepository personRepository, EntityManager entityManager) {
        this.personRepository = personRepository;
        this.entityManager = entityManager;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Optional<Person> findByFullName(String fullName) {
        return personRepository.findByFullName(fullName);
    }

    public Person findById(int id) {
        return personRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Person person) {
        personRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        updatedPerson.setPersonId(id);
        personRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id) {
        personRepository.deleteById(id);
    }

    public List<Book> findByOwnerId(int id) {
        Session session = entityManager.unwrap(Session.class);
        Person person = session.get(Person.class, id);
        return new ArrayList<>(person.getBooks());
    }

}
