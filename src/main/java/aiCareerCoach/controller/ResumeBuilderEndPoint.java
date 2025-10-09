package aiCareerCoach.controller;

import aiCareerCoach.model.resumeBuilder.ResumeDTO;
import aiCareerCoach.services.resumeService.ResumePromptBuilderService;
import aiCareerCoach.services.resumeService.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;

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
    public ResponseEntity<byte[]> generateResume(@RequestBody ResumeDTO resumeDTO,
                      @RequestParam String careerPath,@RequestParam String userId) throws Exception {
        if (!userId.isEmpty()) {
             resumePrompt.generateResumePrompt(resumeDTO,careerPath,userId);
           return ResponseEntity.ok(resumeService.getResume(userId));
        }
        return ResponseEntity.badRequest().build();
    }
}
