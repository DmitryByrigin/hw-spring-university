package com.shoollessons.school.lessons.book;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@AllArgsConstructor
public class BookServices {
    private final List<Book> BOOKS = new ArrayList<>(List.of(
            Book.builder()
                    .Id(1L)
                    .name("Название книги 1")
                    .description("Описание книги 1")
                    .content("Содержание книги 1")
                    .build(),
            Book.builder()
                    .Id(2L)
                    .name("Название книги 2")
                    .description("Описание книги 2")
                    .content("Содержание книги 2")
                    .build()
    ));


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
}
