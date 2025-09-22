package aiCareerCoach.repository.careerpaths;

import aiCareerCoach.model.llmResponseFormat.CareerOptionForStudent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CareerOptionStudentRepository extends MongoRepository<CareerOptionForStudent, String> {
}
