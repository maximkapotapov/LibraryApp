package library.util;

import library.models.Book;
import library.services.BooksService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class BookValidator implements Validator {

    private final BooksService booksService;

    public BookValidator(BooksService booksService) {
        this.booksService = booksService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Book.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Book book = (Book) o;

        if(booksService.findByTitleOfBook(book.getTitleOfBook()).isPresent()) {
            errors.rejectValue("titleOfBook", "", "This book is already existing");
        }

    }
}
