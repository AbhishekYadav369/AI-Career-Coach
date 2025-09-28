package aiCareerCoach.controller;

import aiCareerCoach.model.careerPath.CareerOptionForGraduates;
import aiCareerCoach.model.careerPath.CareerOptionForStudent;
import aiCareerCoach.model.careerPath.CareerPathWrapper;
import aiCareerCoach.model.quizResponse.QuizDataInput;
import aiCareerCoach.services.quizService.ExtractingQuizData;
import aiCareerCoach.services.quizService.PromptService;
import aiCareerCoach.services.userServiceApp.UserDataWrapper;
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
    private final CareerPathWrapper wrapper;
    private  final UserDataWrapper userData;


    @Autowired
    public QuizResponseController(PromptService promptService,ExtractingQuizData extractingQuizData,
                                  CareerPathWrapper wrapper,UserDataWrapper userData){
        this.promptService = promptService;
        this.wrapper = wrapper;
        this.extractingQuizData = extractingQuizData;
        this.userData = userData;
    }


    @PostMapping("/quizData")
    public ResponseEntity<String>saveQuizResponse(@RequestBody QuizDataInput quiz,
                                                  @RequestParam String userId) {
        if(!quiz.getSections().isEmpty()&& userData.checkUserById(userId)){
                userData.saveQuizData(quiz,userId);
                return ResponseEntity.ok("User Data Recorded Successfully !");
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/paths")
    public ResponseEntity<List<?>> getCareerPaths(@RequestParam String userId) {
        if (userData.checkUserById(userId)) {
            return ResponseEntity.ok(extractingQuizData.processQuizData
                    (userData.getUser(userId).getQuizResponse()));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/paths")
    public ResponseEntity<List<?>>finalizePath(@RequestParam String feedback
            ,@RequestParam String userId){
        if (feedback!= null ) {
            String grade=userData.getUser(userId).getQuizResponse().getGrade();
            if (feedback.equalsIgnoreCase("more")) {
                return ResponseEntity.ok(promptService
                        .userFeedbackForLlm(feedback, grade));
            } else {
                List<?> response = promptService.userFeedbackForLlm(feedback, grade);
                if (grade.equalsIgnoreCase("GRADUATE")) {
                    wrapper.setPathForGraduate(response.stream()
                            .filter(CareerOptionForGraduates.class::isInstance) // keep only valid ones
                            .map(CareerOptionForGraduates.class::cast)          // cast safely
                            .collect(Collectors.toList()));
                    userData.saveCareerPath(wrapper, userId);
                    return ResponseEntity.ok(List.of("Career Path for :"+ feedback +" is recorded successfully !"));

                }

                wrapper.setPathForStudent(response.stream()
                        .filter(CareerOptionForStudent.class::isInstance) // keep only valid ones
                        .map(CareerOptionForStudent.class::cast)          // cast safely
                        .collect(Collectors.toList()));
                userData.saveCareerPath(wrapper, userId);
                return ResponseEntity.ok(List.of("Career Path for :"+ feedback +" is recorded successfully !"));

            }
        }

        return ResponseEntity.badRequest().build();
    }

}


