package com.schoollessons.school.lessons.borrowings;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/borrowings")
@AllArgsConstructor
@Controller
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
    public void deleteBorrowingById(@PathVariable Long id) {
        borrowingServices.deleteBorrowingById(id);
    }

    @PostMapping
    public Borrowing createBorrowing(@RequestBody BorrowingDTO borrowingDTO) {
        return borrowingServices.createBorrowing(borrowingDTO);
    }
}
