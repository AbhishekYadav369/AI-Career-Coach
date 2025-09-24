package aiCareerCoach.services.industryConnection;

import aiCareerCoach.model.linkedin.CareerPathConnection;
import aiCareerCoach.model.roadmap.SkillsRoadmapResponse;
import aiCareerCoach.repository.connections.CareerPathConnectionRepository;
import aiCareerCoach.services.llmModelService.GeminiService;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class CareerPathConnectionService {
    @Value("classpath:customPrompts/connectionTemplate.st")
    private Resource resource;
    private final CareerPathConnectionRepository repository;
    private final GeminiService geminiService;

    @Autowired
    public CareerPathConnectionService(CareerPathConnectionRepository
                                      repository,GeminiService geminiService) {
        this.repository = repository;
        this.geminiService = geminiService;
    }

    // Save a CareerPathConnections document
    public CareerPathConnection saveCareerPathConnections(CareerPathConnection connections) {
        return repository.save(connections);
    }

    // Retrieve by career path
    public CareerPathConnection getConnectionsByCareerPath(String careerPath) {

        PromptTemplate promptTemplate = new PromptTemplate(resource);
         CareerPathConnection connections= geminiService.getConnections
             (promptTemplate.create(Map.of("careerPath", careerPath)));

    if(connections==null)
        return repository.findByCareerPath(careerPath);

    return connections;

    }

    // Retrieve all
    private List<CareerPathConnection> getAllConnections() {
        return repository.findAll();
    }


    // Delete by ID
    private void deleteById(String id) {
        repository.deleteById(id);
    }
}

