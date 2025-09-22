package aiCareerCoach.services.quizService;

import aiCareerCoach.model.quizQuestion.QuizSectionWiseByGrade;
import aiCareerCoach.repository.quizData.QuizQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizQuestionService {
    private final QuizQuestion repository;
    @Autowired
    public QuizQuestionService(QuizQuestion repository) {
        this.repository = repository;
    }
    /*
      Save a QuizQuestionsBySection object into MongoDB
      @param quizQuestions the object to save
      @return saved object with generated mongoId
     */
    public String saveQuizQuestions(QuizSectionWiseByGrade quizQuestions) {
         QuizSectionWiseByGrade questions=repository.save(quizQuestions);
            return questions.getGrade()+" : "+questions.getId();
    }

    /*
      Fetch quiz questions by grade
      @param grade the grade of the user (e.g., "SSC","HSC", "DIPLOMA", "GRADUATE")
      @return list of QuizQuestionsBySection documents
     */
    public QuizSectionWiseByGrade getQuizQuestionsByGrade(String grade) {
        return repository.findByGrade(grade);
    }
}
