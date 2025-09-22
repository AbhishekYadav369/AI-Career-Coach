package aiCareerCoach.controller;

import aiCareerCoach.model.quizResponse.QuizDataInput;
import aiCareerCoach.services.careerPathService.CareerOptionForStudentService;
import aiCareerCoach.services.careerPathService.CareerOptionForGraduateService;
import aiCareerCoach.services.quizService.ExtractingQuizData;
import aiCareerCoach.services.quizService.QuizResponseService;
import aiCareerCoach.services.quizService.PromptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
/**
 * MainEndPoint is a REST controller that handles HTTP requests related to career coaching functionalities.
 * It provides endpoints for initial quizzes, resume generation, industry connections, roadmaps, and mock interviews.
 * The controller interacts with the PromptService to process quiz data and user feedback.
 */


@RestController
@RequestMapping("career")
@CrossOrigin("*")
public class QuizResponseController {
    private final PromptService promptService;
    private final ExtractingQuizData extractingQuizData;
    private final QuizResponseService quizResponseService;
    private final CareerOptionForGraduateService service;
    private final CareerOptionForStudentService studentService;


    @Autowired
    public QuizResponseController(PromptService promptService,ExtractingQuizData extractingQuizData,QuizResponseService quizResponseService,
           CareerOptionForGraduateService service, CareerOptionForStudentService studentService ) {
        this.promptService = promptService;
        this.quizResponseService = quizResponseService;
        this.service = service;
        this.studentService = studentService;
        this.extractingQuizData = extractingQuizData;
    }

   @PostMapping("/paths")
    public ResponseEntity<List<?>> getCareerPaths(@RequestParam String quizId) {
        if (quizId!= null && quizResponseService.existsById(quizId)) {
            return ResponseEntity.ok(extractingQuizData.fetchQuizResponse(quizId));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/quizData")
    public ResponseEntity<String>saveQuizResponse(@RequestBody QuizDataInput quiz){
        if(!quiz.getSections().isEmpty()){
            return ResponseEntity.ok(quizResponseService.saveQuizResponse(quiz));
        }
        return ResponseEntity.badRequest().build();
    }


    @GetMapping("/paths")
    public ResponseEntity<List<?>>finalizePath(@RequestParam String feedback){
        if (feedback!= null ) {
            List<?> response= promptService.userFeedbackForLlm(feedback);

            if(feedback.equalsIgnoreCase("more"))
                return ResponseEntity.ok(response);
            else{
                   if (promptService.getUseCase().equalsIgnoreCase("GRADUATE"))
                        return ResponseEntity.ok(List.of(service.
                                 saveCareerOption(response)));

                else
                    return ResponseEntity.ok(List.of(studentService.
                            saveCareerOption(response)));
            }

        }
        return ResponseEntity.badRequest().build();
    }


}