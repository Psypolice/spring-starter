package com.sharov.spring.database.repository;

import com.sharov.spring.database.entity.Role;
import com.sharov.spring.database.entity.User;
import com.sharov.spring.dto.PersonalInfo;
import com.sharov.spring.dto.UserFilter;

import java.util.List;

public interface FilterUserRepository {

    List<User> findAllByFilter(UserFilter userFilter);

    List<PersonalInfo> findAllByCompanyIdAndRole(Integer companyId, Role role);

    void updateCompanyAndRole(List<User> users);

    void updateCompanyAndRolenamed(List<User> users);
}
