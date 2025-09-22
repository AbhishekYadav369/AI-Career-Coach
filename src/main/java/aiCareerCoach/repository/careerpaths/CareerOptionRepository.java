package aiCareerCoach.repository.careerpaths;

import aiCareerCoach.model.llmResponseFormat.CareerOptionForGraduates;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
//for graduates
    @Repository
    public interface CareerOptionRepository extends MongoRepository<CareerOptionForGraduates, String> {

    }


