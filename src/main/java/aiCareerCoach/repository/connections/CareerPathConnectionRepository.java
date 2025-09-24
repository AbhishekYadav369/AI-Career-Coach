package aiCareerCoach.repository.connections;

import aiCareerCoach.model.linkedin.CareerPathConnection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CareerPathConnectionRepository extends MongoRepository<CareerPathConnection, String> {
    CareerPathConnection findByCareerPath(String careerPath);
}
