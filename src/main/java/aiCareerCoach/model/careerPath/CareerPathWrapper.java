package aiCareerCoach.model.careerPath;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CareerPathWrapper {
    private List<CareerOptionForGraduates> pathForGraduate;
    private List<CareerOptionForStudent> pathForStudent;

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
