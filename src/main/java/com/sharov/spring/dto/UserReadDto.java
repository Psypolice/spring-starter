package com.sharov.spring.dto;

import com.sharov.spring.database.entity.Role;
import lombok.Value;

import java.time.LocalDate;

@Value
public class UserReadDto {
    Long id;
    String username;
    String firstname;
    String lastname;
    String image;
    LocalDate birthDate;
    Role role;
    CompanyReadDto company;
}
