package aiCareerCoach.model.careerPath;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Document(collection="career_path")
public class CareerPathWrapper {
    @Id
    private String id;
    private List<CareerOptionForGraduates> pathForGraduate;
    private List<CareerOptionForStudent> pathForStudent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<CareerOptionForGraduates> getPathForGraduate() {
        return pathForGraduate;
    }

    public void setPathForGraduate(List<CareerOptionForGraduates> pathForGraduate) {
        this.pathForGraduate = pathForGraduate;
    }

    public List<CareerOptionForStudent> getPathForStudent() {
        return pathForStudent;
    }

    public void setPathForStudent(List<CareerOptionForStudent> pathForStudent) {
        this.pathForStudent = pathForStudent;
    }
}
