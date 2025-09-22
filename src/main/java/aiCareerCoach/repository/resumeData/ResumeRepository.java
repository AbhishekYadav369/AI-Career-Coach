package aiCareerCoach.repository.resumeData;

import aiCareerCoach.model.resumeBuilder.ResumeWrapper;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ResumeRepository extends MongoRepository<ResumeWrapper,String> {
}
