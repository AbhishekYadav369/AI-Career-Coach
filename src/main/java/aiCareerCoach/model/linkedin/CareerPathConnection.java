package aiCareerCoach.model.linkedin;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document(collection = "career_path_connections")
public class CareerPathConnection {
    @Id
    private String id;
    private String careerPath;
    private List<LinkedInProfile> topConnections;

    //Getter And Setter

    public String getCareerPath() {
        return careerPath;
    }

    public void setCareerPath(String careerPath) {
        this.careerPath = careerPath;
    }

    public List<LinkedInProfile> getTopConnections() {
        return topConnections;
    }

    public void setTopConnections(List<LinkedInProfile> topConnections) {
        this.topConnections = topConnections;
    }
}
