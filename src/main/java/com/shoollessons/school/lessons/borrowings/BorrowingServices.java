package com.shoollessons.school.lessons.borrowings;

import com.shoollessons.school.lessons.book.Book;
import com.shoollessons.school.lessons.book.BookServices;
import com.shoollessons.school.lessons.user.User;
import com.shoollessons.school.lessons.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
@AllArgsConstructor
public class BorrowingServices {
    private final AtomicLong lastIndex = new AtomicLong(1);
    private final List<Borrowing> BORROWINGS = new ArrayList<>();
    private final UserService userService;
    private final BookServices bookService;


    public List<Borrowing> findAllBorrowings() {
        return BORROWINGS;
    }

    public Borrowing findBorrowingByID(Long id) {
        return BORROWINGS.stream().filter(element -> element.getId().equals(id)).findFirst().orElse(null);
    }

    public Borrowing deleteBorrowingById(Long id) {
        Iterator<Borrowing> iterator = BORROWINGS.iterator();
        while (iterator.hasNext()) {
            Borrowing borrowing = iterator.next();
            if (borrowing.getId().equals(id)) {
                iterator.remove();
                return borrowing;
            }
        }
        return null;
    }

    public Borrowing createBorrowing(CreateBorrowingDTO borrowing) {
        User existUser = userService.findUserById(borrowing.getCustomerId());
        Book existBook = bookService.findBookByID(borrowing.getCustomerId());
        Borrowing borrowing1 = Borrowing.builder()
                .title(existBook.getTitle())
                .authorName(existBook.getAuthorFirstname() + ' ' + existBook.getAuthorLastname())
                .bookId(existBook.getId())
                .customerName(existUser.getFirstName() + ' ' + existUser.getLastName())
                .customerId(existUser.getId())
                .id(lastIndex.getAndIncrement())
                .build();

        BORROWINGS.add(borrowing1);
        return borrowing1;
    }
}

