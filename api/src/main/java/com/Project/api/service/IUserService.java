package com.Project.api.service;

import com.Project.api.entity.User;
import com.Project.api.entity.enums.Role;

import java.util.List;

public interface IUserService extends IService<User>{

    List<User> getUserByRole(Role role);

    List<User> getPotentialUsers(List<Integer> ids);
}
