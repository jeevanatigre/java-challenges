package com.indpro.service;

import com.indpro.entity.MyUserDetails;
import com.indpro.entity.User;
import com.indpro.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IndproUserDetailsService implements UserDetailsService {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userJpaRepository.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("User not found for the given username" + username));
        return user.map(MyUserDetails::new).get();
    }
}
