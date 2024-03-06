package com.schoollessons.school.lessons.borrowings;

import com.schoollessons.school.lessons.book.Book;
import com.schoollessons.school.lessons.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "borrowing")
public class Borrowing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customerId")
    private User customer;
    @ManyToOne
    @JoinColumn(name = "bookId")
    private Book book;
}