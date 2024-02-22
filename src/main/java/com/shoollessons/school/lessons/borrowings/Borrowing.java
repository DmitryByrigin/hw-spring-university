package com.shoollessons.school.lessons.borrowings;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Borrowing {
    private Long id;
    private Long customerId;
    private String customerName;
    private Long bookId;
    private String authorName;
    private String title;
}

