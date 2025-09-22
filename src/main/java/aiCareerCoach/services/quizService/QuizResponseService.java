package aiCareerCoach.services.quizService;

import aiCareerCoach.model.quizResponse.QuizDataInput;
import aiCareerCoach.repository.quizData.QuizData;
import org.springframework.stereotype.Service;

@Service
public class QuizResponseService {

        private String responseId;
        private final QuizData repository;

        public QuizResponseService(QuizData repository) {
            this.repository = repository;
        }

        public String saveQuizResponse(QuizDataInput quizDataInput) {
            QuizDataInput saved= repository.save(quizDataInput);
            this.responseId=saved.getId();
            return responseId;
        }

        public QuizDataInput getQuizResponseById(String id) {
            return repository.findById(id).orElse(null);
        }

        public String getResponseId() {
                 return responseId;
        }

    // Check if a quiz response exists for a given ID
         public boolean existsById(String id) {
            return repository.existsById(id);
         }

}

