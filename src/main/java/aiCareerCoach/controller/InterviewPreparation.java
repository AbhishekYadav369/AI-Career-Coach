package aiCareerCoach.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("career")
@CrossOrigin("*")
public class InterviewPreparation {

    @GetMapping("/interview")
    public ResponseEntity<String> getMockInterview()
    {
        return ResponseEntity.ok( "mock interview coming soon");
    }
}
