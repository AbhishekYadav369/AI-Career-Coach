package com.aicareercoach.service.security;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService {
    private final UserRepository repository;

    @Autowired
    public UserDetailService(UserRepository repository) {
        this.repository = repository;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user= repository.findByUsername(username);

        if (user==null) {
            System.out.println("User 404");
            throw new UsernameNotFoundException("User 404");
        }
        return User;
    }

}