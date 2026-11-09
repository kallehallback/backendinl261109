package se.yrgo.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import se.yrgo.domain.Book;

@Controller
public class BookController {

    private List<Book> getBooks() {
        List<Book> books = new ArrayList<>();

        Book book1 = new Book("titel1", "författare1", "fantasy");
        Book book2 = new Book("titel2", "författare2", "deckare");
        Book book3 = new Book("titel3", "författare3", "fantasy");
        Book book4 = new Book("titel4", "författare4", "deckare");

        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);

        return books;
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        Date date = new Date();

        model.addAttribute("date", date);

        return "home";
    }

    @GetMapping("/books")
    public String booksPage(Model model) {
        model.addAttribute("books", getBooks());

        return "booklist";
    }

    @GetMapping("/genre")
    public String genrePage(
            @RequestParam(value = "type", required = false) String type,
            Model model) {

        if (type == null) {
            model.addAttribute("books", getBooks());

            return "booklist";
        }

        List<Book> filteredBooks = getBooks().stream()
                .filter(book -> book.genre().equalsIgnoreCase(type))
                .toList();

        model.addAttribute("books", filteredBooks);

        return "booklist";
    }
}