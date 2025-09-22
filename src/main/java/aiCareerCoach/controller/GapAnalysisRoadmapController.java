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

    @GetMapping("/roadMap")
    public ResponseEntity<SkillsRoadmapResponse> generateRoadMap(@RequestParam String timeline,
                       @RequestParam String quizId, @RequestParam String pathId) {
        if (timeline != null||( quizId!=null && pathId!=null)) {
            return ResponseEntity.ok(gapAnalysis.analyzeGapsAndGenerateRoadMap(timeline,quizId,pathId));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/roadmap")
    public ResponseEntity<String> getRoadMapId(@RequestParam SkillsRoadmapResponse response) {
        if (response != null) {
            return ResponseEntity.ok( gapAnalysis.getRoadmapId(response));
        }
        return ResponseEntity.badRequest().build();
    }
    @GetMapping("/getRoadmap")
    public ResponseEntity<SkillsRoadmapResponse> getRoadmapById(@RequestParam String roadmapId) {
        if (roadmapId != null) {
            return ResponseEntity.ok(gapAnalysis.getRoadmapById(roadmapId));
        }
        return ResponseEntity.badRequest().build();
    }

}
