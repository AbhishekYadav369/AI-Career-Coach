package aiCareerCoach.controller;

import aiCareerCoach.model.userInfo.Users;

import aiCareerCoach.security.jwt.JwtService;
import aiCareerCoach.security.securityService.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("career")
@CrossOrigin("*")
public class AuthenticationEndPoint {
    /*
     This is controller which accepts Register form react(UI)
     client and map it to POJO and pass the call to UserService.
    */
    private final RegisterService registerService ;
    private final AuthenticationManager authenticationManager ;
    private final JwtService jwtService ;
    @Autowired
    public AuthenticationEndPoint(RegisterService registerService,
                                  AuthenticationManager authenticationManager,
                                  JwtService jwtService) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.registerService = registerService;
    }
    @PostMapping("/register")
    public ResponseEntity<String> registerNewUser(@RequestBody Users user) {
        if (user.getUsername()!=null &&user.getPassword()!=null) {
            registerService.saveUser(user);
            return ResponseEntity.ok("User registered successfully");
        }
        return ResponseEntity.badRequest().body("Please provide Valid Response.");

    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody Users   user) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if(authentication.isAuthenticated())
            return ResponseEntity.ok(jwtService.generateToken(user.getUsername()));

        return ResponseEntity.badRequest().body("Invalid username or password.");

    }

}
