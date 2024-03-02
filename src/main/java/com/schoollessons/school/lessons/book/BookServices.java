package com.schoollessons.school.lessons.book;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
@AllArgsConstructor
public class BookServices {
    private final List<Book> BOOKS = new ArrayList<>(List.of(
//            Book.builder()
//                    .Id(1L)
//                    .name("Название книги 1")
//                    .description("Описание книги 1")
//                    .content("Содержание книги 1")
//                    .build(),
//            Book.builder()
//                    .Id(2L)
//                    .name("Название книги 2")
//                    .description("Описание книги 2")
//                    .content("Содержание книги 2")
//                    .build()
    ));

    private final AtomicLong lastIndex = new AtomicLong(1);


    public List<Book> findAllBooks() {
        return BOOKS;
    }

    public Book findBookByID (Long id) {
        return BOOKS.stream().filter(element -> element.getId().equals(id)).findFirst().orElse(null);
    }

    public Book deleteBookById (Long id) {
        Iterator<Book> iterator = BOOKS.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getId().equals(id)) {
                iterator.remove();
                return book;
            }
        }
        return null;
    }

    public Book updateBookById(Long id, Book book) {
        Book existBook = findBookByID(id);
        existBook.setCount(book.getCount());
        existBook.setIsbn(book.getIsbn());
        existBook.setAuthorFirstname(book.getAuthorFirstname());
        existBook.setTitle(book.getTitle());
        existBook.setAuthorLastname(book.getAuthorLastname());
        return book;
    }

    public Book createBook(Book book) {
        book.setId(lastIndex.getAndIncrement());
        BOOKS.add(book);
        return book;
    }
}
