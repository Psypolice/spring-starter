package com.sharov.spring.integration.service;

import com.sharov.spring.database.entity.Role;
import com.sharov.spring.dto.UserCreateEditDto;
import com.sharov.spring.dto.UserReadDto;
import com.sharov.spring.integration.IntegrationTestBase;
import com.sharov.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RequiredArgsConstructor
public class UserServiceIT extends IntegrationTestBase {

    public static final Long USER_1 = 1L;
    public static final Integer COMPANY_1 = 1;

    private final UserService userService;

    @Test
    void findAll() {
        List<UserReadDto> result = userService.findAll();
        assertThat(result).hasSize(5);
    }

    @Test
    void findById() {
        var mayBeUser = userService.findById(USER_1);
        assertTrue(mayBeUser.isPresent());
        mayBeUser.ifPresent(user -> assertEquals("ivan@gmail.com", user.getUsername()));
    }

    @Test
    void create() {
        var userDto = new UserCreateEditDto(
                "test@gmail.com",
                "test",
                "Test",
                "Test",
                LocalDate.now(),
                Role.ADMIN,
                COMPANY_1,
                new MockMultipartFile("test", new byte[0])
        );
        var actualResult = userService.create(userDto);

        assertEquals(userDto.getUsername(), actualResult.getUsername());
        assertEquals(userDto.getFirstname(), actualResult.getFirstname());
        assertEquals(userDto.getLastname(), actualResult.getLastname());
        assertEquals(userDto.getBirthDate(), actualResult.getBirthDate());
        assertSame(userDto.getRole(), actualResult.getRole());
        assertEquals(userDto.getCompanyId(), actualResult.getCompany().id());
    }

    @Test
    void update() {
        var userDto = new UserCreateEditDto(
                "test@gmail.com",
                "test",
                "Test",
                "Test",
                LocalDate.now(),
                Role.ADMIN,
                COMPANY_1,
                new MockMultipartFile("test", new byte[0])
        );

        var actualResult = userService.update(USER_1, userDto);

        assertTrue(actualResult.isPresent());
        actualResult.ifPresent(user -> {
            assertEquals(userDto.getUsername(), user.getUsername());
            assertEquals(userDto.getFirstname(), user.getFirstname());
            assertEquals(userDto.getLastname(), user.getLastname());
            assertEquals(userDto.getBirthDate(), user.getBirthDate());
            assertSame(userDto.getRole(), user.getRole());
            assertEquals(userDto.getCompanyId(), user.getCompany().id());
        });
    }

    @Test
    void delete() {
        assertFalse(userService.delete(-12L));
        assertTrue(userService.delete(USER_1));
    }
}
