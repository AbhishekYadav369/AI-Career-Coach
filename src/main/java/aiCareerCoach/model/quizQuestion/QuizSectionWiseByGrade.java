package aiCareerCoach.model.quizQuestion;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;
@Document("quiz_questions")
public class QuizSectionWiseByGrade{
        @Id
        private String id;
        private String grade; //eg:HSC,SSC,DIPLOMA,GRADUATE
        private Map<String, List<QuestionDTO>> sections;

        public QuizSectionWiseByGrade() {}

        public QuizSectionWiseByGrade(String grade, Map<String, List<QuestionDTO>> sections) {
            this.grade = grade;
            this.sections = sections;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public Map<String, List<QuestionDTO>> getSections() {
            return sections;
        }

        public void setSections(Map<String, List<QuestionDTO>> sections) {
            this.sections = sections;
        }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
