package com.shoollessons.school.lessons.book;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/books")
@AllArgsConstructor
public class BookController {
    private final BookServices bookServices;

    @GetMapping
    public List<Book> getBooks() {
        return bookServices.findAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookServices.findBookByID(id);
    }

    @GetMapping("delete/{id}")
    public Book deleteBookById(@PathVariable Long id) {
        return bookServices.deleteBookById(id);
    }
}
