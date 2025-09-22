package aiCareerCoach.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("career")
@CrossOrigin("*")
public class InterviewPreparation {

    @GetMapping("/mockInterview")
    public String getMockInterview(){
        return "mock interview coming soon";
    }
}
