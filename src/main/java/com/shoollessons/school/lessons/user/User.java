package com.shoollessons.school.lessons.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class User {


    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
