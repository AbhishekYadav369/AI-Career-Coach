package aiCareerCoach.services.industryConnection;

import aiCareerCoach.model.linkedin.CareerPathConnection;
import aiCareerCoach.repository.connections.CareerPathConnectionRepository;
import aiCareerCoach.services.llmModelService.GeminiService;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
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
        CareerPathConnection connections=repository.findByCareerPath(careerPath);
        if(connections==null) {
            PromptTemplate promptTemplate = new PromptTemplate(resource);
             connections = geminiService.getConnections
             (promptTemplate.create(Map.of("careerPath", careerPath)));
        }

        repository.save(connections);

    return connections;

    }

}

