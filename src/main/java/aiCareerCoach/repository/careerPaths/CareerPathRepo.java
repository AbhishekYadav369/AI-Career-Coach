package aiCareerCoach.repository.careerPaths;

import aiCareerCoach.model.careerPath.CareerPathWrapper;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CareerPathRepo extends MongoRepository<CareerPathWrapper, String> {
}
