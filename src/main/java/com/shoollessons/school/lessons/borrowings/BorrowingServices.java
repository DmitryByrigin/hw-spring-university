package com.shoollessons.school.lessons.borrowings;

import com.shoollessons.school.lessons.borrowings.Borrowing;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@AllArgsConstructor
public class BorrowingServices {
    private final List<Borrowing> BORROWINGS = new ArrayList<>();

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

    public Borrowing createBorrowing(Borrowing borrowing) {
        borrowing.setId(BORROWINGS.size() + 1L);
        BORROWINGS.add(borrowing);
        return borrowing;
    }
}

