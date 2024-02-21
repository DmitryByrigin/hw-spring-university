package com.shoollessons.Shool.lessons.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private Long Id;
    private String name;
    private String description;
    private String content;
}
