package com.Project.api.service.implementation;


import com.Project.api.common.GeneralException;
import com.Project.api.entity.User;
import com.Project.api.entity.enums.Role;
import com.Project.api.repository.IUserRepo;
import com.Project.api.service.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {


    private IUserRepo userRepo;

    public UserService(IUserRepo iUserRepo) {
        this.userRepo = iUserRepo;
    }

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            if (user.getIdentityNo() == null || user.getIdentityNo().length() != 11){
                throw new GeneralException("Invalid identity number!!!");
            }

            if (userRepo.existsByIdentityNo(user.getIdentityNo())){
                throw new GeneralException("Identity number already exist!!");
            }
        }
        return userRepo.save(user);
    }

    @Override
    public User getById(Integer id) throws GeneralException {

        Optional<User> userOptional = userRepo.findById(id);

        if (userOptional.isEmpty()) {
            throw new GeneralException("User not Found");
        }

        return userOptional.get();
    }

    @Override
    public List<User> getAll() {
        return userRepo.findAll();
    }

    @Override
    public Page<User> getAll(Pageable pageable) {
        return userRepo.findAll(pageable);
    }

    @Override
    public void delete(Integer id) {
        if (!userRepo.existsById(id)) {
            throw new GeneralException("User not found.");
        }
        userRepo.deleteById(id);
    }

    @Override
    public List<User> getUserByRole(Role role) {
        return userRepo.findAllByRole(role);
    }

    @Override
    public List<User> getPotentialUsers(List<Integer> ids) {

        if (ids.isEmpty()) {
            return getUserByRole(Role.STUDENT);
        }
        return userRepo.findAllByRoleAndIdIsNotIn(Role.STUDENT, ids);
    }
}
