package aiCareerCoach.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("career")
@CrossOrigin("*")
public class IndustryConnections {
    @GetMapping("/industryConnection")
    public String getIndustryConnection(){
        return "industry connection coming soon";
    }
}
