package com.schoollessons.school.lessons.borrowings;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class CreateBorrowingDTO {
    private Long customerId;
    private Long bookId;
}
