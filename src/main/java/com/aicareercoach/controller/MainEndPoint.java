package com.aicareercoach.controller;

import com.aicareercoach.model.InitialQuiz.QuizData;

import com.aicareercoach.services.aiModel.PromptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("career")
@CrossOrigin("*")
public class MainEndPoint {
    private final PromptService processResponseService;

    @Autowired
    public MainEndPoint(PromptService processResponseService) {
        this.processResponseService = processResponseService;
    }

    /*
     This is controller which accepts QuizResponse form react(UI)
     client and map it to POJO and pass the call to processResponseService.
    */
    @PostMapping("/initialQuiz")
    public ResponseEntity<String> initialQuiz(@RequestBody QuizData quizData) {
        if (!quizData.getSections().isEmpty()) {
            processResponseService.processQuizData(quizData);
            return ResponseEntity.ok("Your Response has been recorded successfully.");
        }
        return ResponseEntity.badRequest().body("Please provide Valid Response.");
    }


    @PutMapping("/initialQuiz")
    public ResponseEntity<String> initialQuiz() {
        return ResponseEntity.ok("You have successfully reattempted the Quiz.");
    }

}