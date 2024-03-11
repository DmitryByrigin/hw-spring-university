package com.schoollessons.school.lessons.borrowings;

import com.schoollessons.school.lessons.book.Book;
import com.schoollessons.school.lessons.book.BookRepository;
import com.schoollessons.school.lessons.user.User;
import com.schoollessons.school.lessons.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class BorrowingServices {
    private final BorrowingRepository borrowingRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public List<Borrowing> findAllBorrowings() {
        return borrowingRepository.findAll();
    }

    public Borrowing findBorrowingByID(Long id) {
        Optional<Borrowing> borrowing = borrowingRepository.findById(id);
        return borrowing.orElse(null);
    }

    public void deleteBorrowingById(Long id) {
        borrowingRepository.deleteById(id);
    }

    public Borrowing createBorrowing(BorrowingDTO borrowingDTO) {
        Optional<User> customer = userRepository.findById(borrowingDTO.getCustomerId());
        Optional<Book> book = bookRepository.findById(borrowingDTO.getBookId());
        if (customer.isPresent() && book.isPresent()) {
            Borrowing borrowing = new Borrowing();
            borrowing.setCustomer(customer.get());
            borrowing.setBook(book.get());
            return borrowingRepository.save(borrowing);
        }
        return null;
    }
}