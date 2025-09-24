package aiCareerCoach.controller;

import aiCareerCoach.model.careerPath.CareerOptionForGraduates;
import aiCareerCoach.model.careerPath.CareerOptionForStudent;
import aiCareerCoach.model.careerPath.CareerPathWrapper;
import aiCareerCoach.model.quizResponse.QuizDataInput;
import aiCareerCoach.services.careerPathService.CareerPathService;
import aiCareerCoach.services.quizService.ExtractingQuizData;
import aiCareerCoach.services.quizService.QuizResponseService;
import aiCareerCoach.services.quizService.PromptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

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
    private final CareerPathWrapper wrapper;
    private final CareerPathService careerPathService;


    @Autowired
    public QuizResponseController(PromptService promptService,ExtractingQuizData
                       extractingQuizData,QuizResponseService quizResponseService,
                    CareerPathWrapper wrapper,CareerPathService careerPathService ) {
        this.promptService = promptService;
        this.quizResponseService = quizResponseService;
        this.wrapper = wrapper;
        this.careerPathService = careerPathService;
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
    public ResponseEntity<List<?>>finalizePath(@RequestParam String feedback
            ,@RequestParam String quizId){
        if (feedback!= null ) {
            if (feedback.equalsIgnoreCase("more")) {
                return ResponseEntity.ok(promptService
                        .userFeedbackForLlm(feedback, quizId));
            } else {
                List<?> response = promptService.userFeedbackForLlm(feedback, quizId);
                if (quizResponseService.getGrade(quizId).equalsIgnoreCase("GRADUATE")) {
                    wrapper.setPathForGraduate(response.stream()
                            .filter(CareerOptionForGraduates.class::isInstance) // keep only valid ones
                            .map(CareerOptionForGraduates.class::cast)          // cast safely
                            .collect(Collectors.toList()));
                    return ResponseEntity.ok(List.of(careerPathService.saveCareerOption(wrapper)));

                }


                wrapper.setPathForStudent(response.stream()
                        .filter(CareerOptionForStudent.class::isInstance) // keep only valid ones
                        .map(CareerOptionForStudent.class::cast)          // cast safely
                        .collect(Collectors.toList()));
                return ResponseEntity.ok(List.of(careerPathService.saveCareerOption(wrapper)));

            }
        }

        return ResponseEntity.badRequest().build();
    }

}


