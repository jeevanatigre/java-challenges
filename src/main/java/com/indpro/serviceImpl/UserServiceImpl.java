package com.indpro.serviceImpl;

import com.indpro.dto.UserRequest;
import com.indpro.entity.User;
import com.indpro.repository.UserJpaRepository;
import com.indpro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserJpaRepository userJpaRepository;

    @Override
    public User save(UserRequest userRequest) {
        return userJpaRepository.save(User.build(0L, userRequest.getUsername(), userRequest.getFirstName(),
                userRequest.getLastName(), userRequest.getPassword(), userRequest.getRoles(), userRequest.getEmail(),
                userRequest.getMobileNo(), userRequest.getCanEdit()));
    }

    @Override
    public Optional<User> findByUsername(String userName) {
        return userJpaRepository.findByUsername(userName);
    }

    public User findUserById(Long id) {
        return userJpaRepository.findById(id).get();
    }
}
