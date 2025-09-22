package aiCareerCoach.repository.quizData;

import aiCareerCoach.model.quizQuestion.QuizSectionWiseByGrade;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuizQuestion extends MongoRepository<QuizSectionWiseByGrade, String> {
        QuizSectionWiseByGrade findByGrade(String grade);
}
