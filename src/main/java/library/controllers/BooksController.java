package library.controllers;

import library.dao.BookDAO;
import library.dao.PersonDAO;
import library.models.Book;
import library.models.Person;
import library.services.BooksService;
import library.services.PeopleService;
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
    private final BookValidator bookValidator;

    private final BooksService booksService;

    private final PeopleService peopleService;

    @Autowired
    public BooksController(BookValidator bookValidator, BooksService booksService, PeopleService peopleService) {
        this.bookValidator = bookValidator;
        this.booksService = booksService;
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String showAll(Model model) {
        model.addAttribute("books", booksService.findAll());
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

        booksService.saveBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", booksService.findById(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        bookValidator.validate(book, bindingResult);
        if(bindingResult.hasErrors())
            return "books/edit";

        booksService.updateBook(id, book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String showBook(Model model, @PathVariable("id") int id, @ModelAttribute("person") Person person) {
        model.addAttribute("book", booksService.findById(id));
        model.addAttribute("personId", booksService.findOwnerByBookId(id));
        model.addAttribute("people", peopleService.findAll());
        return "books/show";
    }

    @PatchMapping("/{id}/edit")
    public String deletePersonBook(@PathVariable("id") int id){
        booksService.deletePersonBook(id);
        return "redirect:/books/{id}";
    }

    @PostMapping("/{id}")
    public String updateBook(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        booksService.updatePersonBook(id, person);
        return "redirect:/books/{id}";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/books";
    }

}
