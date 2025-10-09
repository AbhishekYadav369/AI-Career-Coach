package aiCareerCoach.controller;

import aiCareerCoach.model.userInfo.Users;

import aiCareerCoach.security.jwt.JwtService;
import aiCareerCoach.security.securityService.RegisterService;
import aiCareerCoach.services.userServiceApp.UserDataWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private final UserDataWrapper userData ;
    @Autowired
    public AuthenticationEndPoint(RegisterService registerService,JwtService jwtService,
                                  AuthenticationManager authenticationManager,
                                UserDataWrapper userData  ) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.registerService = registerService;
        this.userData = userData;
    }
    @PostMapping("/register")
    public ResponseEntity<String> registerNewUser(@RequestBody Users user) {
        if (user.getUsername()!=null &&user.getPassword()!=null) {
            registerService.saveUser(user);
            return ResponseEntity.ok("User registered successfully");
        }
        return ResponseEntity.badRequest().body("Please provide Valid Response.");

    }
/*
Client get both bearerToken along with userId
*/

    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> loginUser(@RequestBody Users user) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if(authentication.isAuthenticated()){
            Map<String,String> map = new HashMap<>();
                    map.put("Bearer Token",jwtService.generateToken(user.getUsername()));
                    map.put("User ID",userData.getUserId(user.getUsername()));
            return ResponseEntity.ok(map);
        }
        return ResponseEntity.badRequest().build();
    }
    @GetMapping("/user")
    public ResponseEntity<Users> getUser(@RequestParam String userId) {
        if(!userId.isEmpty() && userData.checkUserById(userId)){
            return ResponseEntity.ok(userData.getUser(userId));
        }
        return ResponseEntity.badRequest().build();
    }


}
