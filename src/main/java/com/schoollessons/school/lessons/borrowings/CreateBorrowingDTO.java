package com.schoollessons.school.lessons.borrowings;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data

public class CreateBorrowingDTO {
    private Long customerId;
    private Long bookId;
}
