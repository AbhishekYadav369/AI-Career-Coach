package aiCareerCoach.security.security;

import aiCareerCoach.model.userInfo.Users;
import aiCareerCoach.repository.userData.UserRepository;
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

