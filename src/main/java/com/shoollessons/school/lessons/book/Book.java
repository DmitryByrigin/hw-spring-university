package com.shoollessons.school.lessons.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Book {
    private Long id;
    private String authorFirstname;
    private String authorLastname;
    private String title;
    private String isbn;
    private Integer count;

    public Book() {

    }
}
