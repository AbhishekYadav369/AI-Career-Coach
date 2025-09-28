package aiCareerCoach.model.quizResponse;

import java.util.List;
import java.util.Map;

 // This will create/use a MongoDB collection named "quiz_data"
public class QuizDataInput {
    // MongoDB will use ObjectId here
    private String grade;
    private Map<String, List<Questions>> sections;

    // Constructors
    public QuizDataInput() {}

    public QuizDataInput(String grade,Map<String, List<Questions>> sections) {
        this.sections = sections;
        this.grade = grade;
    }

    // Getters & Setters

    public Map<String, List<Questions>> getSections() {
        return sections;
    }

    public void setSections(Map<String, List<Questions>> sections) {
        this.sections = sections;
    }
    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
}
