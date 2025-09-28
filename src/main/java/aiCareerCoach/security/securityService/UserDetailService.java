package aiCareerCoach.security.securityService;
import aiCareerCoach.security.principal.UserPrincipal;
import aiCareerCoach.model.userInfo.Users;
import aiCareerCoach.repository.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {
    private final UserRepository repository;

    @Autowired
    public UserDetailService(UserRepository repository) {
        this.repository = repository;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user= repository.findByUsername(username);

        if (user==null) {
            throw new UsernameNotFoundException("User 404");
        }
        return new UserPrincipal(user);
    }

}