package aiCareerCoach.repository.roadmapRepo;

import aiCareerCoach.model.roadmap.SkillsRoadmapResponse;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoadmapRepository extends MongoRepository <SkillsRoadmapResponse, String> {
}
