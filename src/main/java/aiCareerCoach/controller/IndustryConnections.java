package aiCareerCoach.controller;

import aiCareerCoach.model.linkedin.CareerPathConnection;
import aiCareerCoach.services.industryConnection.CareerPathConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("career")
//@CrossOrigin("*")
public class IndustryConnections {
    private final CareerPathConnectionService service;
    @Autowired
    public IndustryConnections(CareerPathConnectionService service) {
        this.service = service;
    }

    @GetMapping("/connection")
    public ResponseEntity<CareerPathConnection> getIndustryConnection
            (@RequestParam String careerPath){
        if(careerPath.isEmpty()){
        return ResponseEntity.badRequest().build();
    }
    return ResponseEntity.ok(service.getConnectionsByCareerPath(careerPath));
}


}