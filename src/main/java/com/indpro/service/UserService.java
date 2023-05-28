package com.indpro.service;

import com.indpro.dto.UserRequest;
import com.indpro.entity.User;

import java.util.Optional;

public interface UserService {

    public User save(UserRequest user);

    public Optional<User> findByUsername(String userName);

    public User findUserById(Long id);

}
