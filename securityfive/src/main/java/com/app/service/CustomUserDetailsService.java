package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.entity.Users;
import com.app.repo.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Users user = repository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User Not Found"));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password("{noop}" + user.getPassword())
                .authorities("USER")
                .build();
    }
}