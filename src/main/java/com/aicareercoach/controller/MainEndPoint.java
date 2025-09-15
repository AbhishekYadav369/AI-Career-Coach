package com.aicareercoach.controller;

import com.aicareercoach.model.initialQuiz.QuizData;
import com.aicareercoach.services.aiModel.PromptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("career")
@CrossOrigin("*")
public class MainEndPoint {
    private final PromptService promptService;

    @Autowired
    public MainEndPoint(PromptService promptService) {
        this.promptService = promptService;
    }

    /*
     This is controller which accepts QuizResponse form react(UI)
     client and map it to POJO and pass the call to processResponseService.
    */
    @PostMapping("/initialQuiz/{useCase}")
    public ResponseEntity<List<?>> initialQuiz(@RequestBody QuizData quizData,
                                                    @PathVariable String useCase) {
        if (!quizData.getSections().isEmpty() && useCase != null) {
             return ResponseEntity.ok(promptService.processQuizData(quizData, useCase));
        }
        return ResponseEntity.badRequest().body(List.of("Please provide Valid Response."));
    }


    @PutMapping("/initialQuiz")
    public ResponseEntity<String> initialQuiz() {
        return ResponseEntity.ok("You have successfully reattempted the Quiz.");
    }
    @GetMapping("/resume")
    public String getResume() {
        return "resume";
    }
    @GetMapping("/coverLetter")
    public String getCoverLetter() {
        return "cover letter";
    }
    @GetMapping("/industryConnection")
    public String getIndustryConnection(){
        return "industry connection";
    }
    @GetMapping("/roadMap")
    public String generateRoadMap(){
        return "road map";
    }


}