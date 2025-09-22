package aiCareerCoach.controller;

import aiCareerCoach.model.resumeBuilder.ResumeDTO;
import aiCareerCoach.services.resumeService.ResumePromptBuilderService;
import aiCareerCoach.services.resumeService.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("career")
@CrossOrigin("*")
public class ResumeBuilderEndPoint {
    private final ResumeService resumeService;
    private final ResumePromptBuilderService resumePrompt;
    @Autowired
    public ResumeBuilderEndPoint(ResumeService resumeService,
                                 ResumePromptBuilderService resumePrompt) {
        this.resumeService = resumeService;
        this.resumePrompt = resumePrompt;
    }

    @PostMapping("/resume")
    public ResponseEntity<String> generateResume(@RequestBody ResumeDTO resumeDTO,@RequestParam("path") String careerPath) throws Exception {
        if (careerPath!=null) {
            return ResponseEntity.ok(resumePrompt.generateResumePrompt(resumeDTO,careerPath));
        }
        return ResponseEntity.badRequest().build();
    }
    @GetMapping("/resume")
    public ResponseEntity<byte[]> getResume(@RequestParam String id){
        if (id!=null) {
            return ResponseEntity.ok(resumeService.getResume(id));
        }
        return  ResponseEntity.badRequest().build();
    }

}
