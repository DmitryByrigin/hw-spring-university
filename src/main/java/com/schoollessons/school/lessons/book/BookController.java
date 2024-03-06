package com.schoollessons.school.lessons.book;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
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

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable Long id) {
        bookServices.deleteBookById(id);
    }

    @PutMapping("/{id}")
    public Book updateBookById(@RequestBody Book book, @PathVariable Long id ) {
        return bookServices.updateBookById(id, book);
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookServices.createBook(book);
    }
}
