package com.aicareercoach.services.springSecurity.security;

import com.aicareercoach.model.authentication.Users;
import com.aicareercoach.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class RegisterService {
    private final UserRepository repository;
         @Autowired
         public RegisterService(UserRepository repository) {
             this.repository = repository;
         }

         public Users saveUser(Users user) {
            user.setPassword(new BCryptPasswordEncoder(12).encode(user.getPassword()));
            return repository.save(user) ;

        }


}

