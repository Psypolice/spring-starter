package com.sharov.spring.integration.database.repository;

import com.sharov.spring.database.entity.Role;
import com.sharov.spring.database.entity.User;
import com.sharov.spring.database.repository.UserRepository;
import com.sharov.spring.dto.PersonalInfo;
import com.sharov.spring.dto.UserFilter;
import com.sharov.spring.integration.IntegrationTestBase;
import com.sharov.spring.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
class UserRepositoryTest extends IntegrationTestBase {

    private final UserRepository userRepository;

    @Test
    void checkBatch() {
        var users = userRepository.findAll();
        userRepository.updateCompanyAndRole(users);
        System.out.println();
    }

    @Test
    void checkJDBCTemplate() {
        var users = userRepository.findAllByCompanyIdAndRole(1, Role.USER);
        assertThat(users).hasSize(1);
        System.out.println();
    }

    @Test
    void checkAuditing() {
        var ivan = userRepository.findById(1L).get();
        ivan.setBirthDate(ivan.getBirthDate().plusYears(1));
        userRepository.flush();
        System.out.println();
    }

    @Test
    void checkCustomImpl() {
        UserFilter filter = new UserFilter(
                null, "%ov%", null
        );
        var users = userRepository.findAllByFilter(filter);
        System.out.println();
    }

    @Test
    void checkProjections() {
        var users = userRepository.findAllByCompanyId(1);
        assertThat(users).hasSize(2);
    }

    @Test
    void checkUpdate() {
        var ivan = userRepository.findById(1L);
        assertSame(Role.ADMIN, ivan.get().getRole());

        var resultCount = userRepository.updateRoles(Role.USER, 1L, 5L);
        assertEquals(2, resultCount);

        var ivanNew = userRepository.findById(1L);
        assertSame(Role.USER, ivanNew.get().getRole());
    }

    @Test
    void checkQueries() {
        var users = userRepository.findAllBy("a", "ov");
        assertThat(users).hasSize(3);
        System.out.println(users);
    }

}