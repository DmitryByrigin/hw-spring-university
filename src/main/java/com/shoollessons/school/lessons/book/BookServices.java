package com.shoollessons.school.lessons.book;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookServices {
    private final BookRepository bookRepository;

    public BookServices(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Book findBookByID(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    public Book updateBookById(Long id, Book newBook) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setCount(newBook.getCount());
            book.setIsbn(newBook.getIsbn());
            book.setAuthorFirstname(newBook.getAuthorFirstname());
            book.setTitle(newBook.getTitle());
            book.setAuthorLastname(newBook.getAuthorLastname());
            return bookRepository.save(book);
        }
        return null;
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }
}

