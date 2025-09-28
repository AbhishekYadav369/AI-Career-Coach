package aiCareerCoach.controller;

import aiCareerCoach.model.roadmap.SkillsRoadmapResponse;
import aiCareerCoach.services.roadmapService.GapAnalysisAndRoadMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("career")
@CrossOrigin("*")
public class GapAnalysisRoadmapController {
    private final GapAnalysisAndRoadMap gapAnalysis;
    @Autowired
    public GapAnalysisRoadmapController(GapAnalysisAndRoadMap gapAnalysis) {
        this.gapAnalysis = gapAnalysis;
    }

    @PostMapping("/roadmap")
    public ResponseEntity<String> generateRoadMap(@RequestParam String timeline,
                       @RequestParam String userId) {
        if (!timeline.isEmpty()&& !userId.isEmpty()) {
            return ResponseEntity.ok(gapAnalysis.analyzeGapsAndGenerateRoadMap(timeline,userId));
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/roadmap")
    public ResponseEntity<SkillsRoadmapResponse> getRoadmapById(@RequestParam String userId) {
        if (!userId.isEmpty()) {
            return ResponseEntity.ok(gapAnalysis.getRoadmapById(userId));
        }
        return ResponseEntity.badRequest().build();
    }

}
