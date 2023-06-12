package library.controllers;

import library.dao.BookDAO;
import library.dao.PersonDAO;
import library.models.Book;
import library.models.Person;
import library.util.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;
    private final BookValidator bookValidator;

    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO, BookValidator bookValidator) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
        this.bookValidator = bookValidator;
    }

    @GetMapping()
    public String showAll(Model model) {
        model.addAttribute("books", bookDAO.showAll());
        return "books/index";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String addBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);
        if(bindingResult.hasErrors())
            return "books/new";

        bookDAO.addBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        bookValidator.validate(book, bindingResult);
        if(bindingResult.hasErrors())
            return "books/edit";

        bookDAO.updateBook(book, id);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String showBook(Model model, @PathVariable("id") int id, @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookDAO.show(id));
        model.addAttribute("personId", bookDAO.showPerson(id));
        model.addAttribute("people", personDAO.index());
        return "books/show";
    }

    @PatchMapping("/{id}/edit")
    public String deletePersonBook(@PathVariable("id") int id){
        bookDAO.deletePersonBook(id);
        return "redirect:/books/{id}";
    }

    @PostMapping("/{id}")
    public String updateBook(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        bookDAO.updatePersonBook(id, person);
        return "redirect:/books/{id}";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }

}
