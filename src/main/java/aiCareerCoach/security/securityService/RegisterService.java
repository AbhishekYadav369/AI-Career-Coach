//package aiCareerCoach.security.security;
//
//import aiCareerCoach.model.userInfo.Users;
//import aiCareerCoach.security.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//@Service
//public class RegisterService {
//    private final UserRepository repository;
//         @Autowired
//         public RegisterService(UserRepository repository) {
//             this.repository = repository;
//         }
//
//         public Users saveUser(Users user) {
//            user.setPassword(new BCryptPasswordEncoder(12).encode(user.getPassword()));
//            return repository.save(user) ;
//
//        }
//
//
//}
//

package aiCareerCoach.security.securityService;

import aiCareerCoach.model.userInfo.Users;
import aiCareerCoach.security.UserRepository;
import aiCareerCoach.security.principal.UsernameAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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
        try {
            user.setPassword(new BCryptPasswordEncoder(12).encode(user.getPassword()));
            return repository.save(user);
        } catch (DuplicateKeyException e) {
            // Throw custom exception when username already exists
            throw new UsernameAlreadyExistsException("User already exists with username: " + user.getUsername());
        }
    }
}

