package com.shoollessons.school.lessons.borrowings;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/borrowings")
@AllArgsConstructor
public class BorrowingController {
    private final BorrowingServices borrowingServices;

    @GetMapping
    public List<Borrowing> getBorrowings() {
        return borrowingServices.findAllBorrowings();
    }

    @GetMapping("/{id}")
    public Borrowing getBorrowingById(@PathVariable Long id) {
        return borrowingServices.findBorrowingByID(id);
    }

    @DeleteMapping("/{id}")
    public Borrowing deleteBorrowingById(@PathVariable Long id) {
        return borrowingServices.deleteBorrowingById(id);
    }

    @PostMapping
    public Borrowing createBorrowing(@RequestBody CreateBorrowingDTO borrowing) {
        return borrowingServices.createBorrowing(borrowing);
    }
}
