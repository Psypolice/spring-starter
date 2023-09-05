package com.sharov.spring.dto;

import com.sharov.spring.database.entity.Role;
import com.sharov.spring.validation.UserInfo;
import com.sharov.spring.validation.group.CreateAction;
import com.sharov.spring.validation.group.UpdateAction;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Value
@FieldNameConstants
@UserInfo(groups = UpdateAction.class)
public class UserCreateEditDto {
    @Email
    String username;

    @NotBlank(groups = CreateAction.class)
    String rawPassword;

    @Size(min = 3, max = 64)
    String firstname;

    String lastname;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate birthDate;

    Role role;

    Integer companyId;

    MultipartFile image;
}
