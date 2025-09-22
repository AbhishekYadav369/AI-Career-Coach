package aiCareerCoach.controller;

import aiCareerCoach.model.quizQuestion.QuizSectionWiseByGrade;
import aiCareerCoach.services.quizService.QuizQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("career")
@CrossOrigin("*")
public class QuizQuestionInitialization {

    private final QuizQuestionService quizQuestionService;
    @Autowired
    public QuizQuestionInitialization(QuizQuestionService quizQuestionService) {
        this.quizQuestionService = quizQuestionService;
    }
    @GetMapping("/quiz")
     public ResponseEntity<QuizSectionWiseByGrade>getQuizQuestionsByGrade(@RequestParam String grade){
//         @param grade the grade of the user (e.g., "SSC","HSC", "DIPLOMA", "GRADUATE")
         if(grade!=null && !grade.isEmpty()){
             return ResponseEntity.ok(quizQuestionService.getQuizQuestionsByGrade(grade));
         }
         return ResponseEntity.badRequest().build();
     }
    @PostMapping("quiz")
     public ResponseEntity<String> setQuizQuestions(@RequestBody QuizSectionWiseByGrade quiz){
            if(quiz!=null){
                return ResponseEntity.ok(quizQuestionService.saveQuizQuestions(quiz));
            }
            return ResponseEntity.badRequest().build();
     }
}
