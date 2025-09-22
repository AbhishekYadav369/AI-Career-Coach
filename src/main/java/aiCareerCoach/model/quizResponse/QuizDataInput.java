package aiCareerCoach.model.quizResponse;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document(collection = "quiz_data") // This will create/use a MongoDB collection named "quiz_data"
public class QuizDataInput {

    @Id
    private String id; // MongoDB will use ObjectId here
    private String grade;
    private Map<String, List<Questions>> sections;

    // Constructors
    public QuizDataInput() {}

    public QuizDataInput(String grade,Map<String, List<Questions>> sections) {
        this.sections = sections;
        this.grade = grade;
    }

    // Getters & Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
