package com.Project.api.repository;

import com.Project.api.entity.User;
import com.Project.api.entity.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IUserRepo extends JpaRepository<User, Integer> {
    boolean existsByIdentityNo(String identityNo);
    List<User> findAllByRole(Role role);

    List<User> findAllByRoleAndIdIsNotIn(Role role, List<Integer> idList);

}
