package aiCareerCoach.repository.quizData;

import aiCareerCoach.model.quizResponse.QuizDataInput;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuizData extends MongoRepository<QuizDataInput,String> {

}
