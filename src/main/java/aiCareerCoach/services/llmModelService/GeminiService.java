package aiCareerCoach.services.llmModelService;

import aiCareerCoach.aop.LlmServiceUnavailableException;
import aiCareerCoach.model.linkedin.CareerPathConnection;
import aiCareerCoach.model.careerPath.CareerOptionForGraduates;
import aiCareerCoach.model.careerPath.CareerOptionForStudent;
import aiCareerCoach.model.resumeBuilder.ResumeDTO;
import aiCareerCoach.model.roadmap.SkillsRoadmapResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class GeminiService {

    private final ChatClient chatClient;

    @Autowired
    public GeminiService(ChatClient.Builder builder) {
        ChatMemory chatMemory = MessageWindowChatMemory.builder().build();
        this.chatClient = builder
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .build();
    }

    public List<CareerOptionForStudent> generateResponseForStudent(Prompt prompt) {
        try {
            return chatClient.prompt(prompt)
                    .call()
                    .entity(new BeanOutputConverter<>(new ParameterizedTypeReference<List<CareerOptionForStudent>>() {}));
        } catch (Exception e) {
            throw new LlmServiceUnavailableException("AI service unavailable for Student response", e);
        }
    }

    public List<CareerOptionForGraduates> generateResponseForGraduate(Prompt prompt) {
        try {
            return chatClient.prompt(prompt)
                    .call()
                    .entity(new BeanOutputConverter<>(new ParameterizedTypeReference<List<CareerOptionForGraduates>>() {}));
        } catch (Exception e) {
            throw new LlmServiceUnavailableException("AI service unavailable for Graduate response", e);
        }
    }

    public SkillsRoadmapResponse generateRoadmap(Prompt prompt) {
        try {
            return chatClient.prompt(prompt)
                    .call()
                    .entity(new BeanOutputConverter<>(SkillsRoadmapResponse.class));
        } catch (Exception e) {
            throw new LlmServiceUnavailableException("AI service unavailable for Roadmap", e);
        }
    }

    public ResumeDTO generateResumeJSON(Prompt prompt) {
        try {
            return chatClient.prompt(prompt)
                    .call()
                    .entity(new BeanOutputConverter<>(ResumeDTO.class));
        } catch (Exception e) {
            throw new LlmServiceUnavailableException("AI service unavailable for Resume generation", e);
        }
    }

    public CareerPathConnection getConnections(Prompt prompt) {
        try {
            return chatClient.prompt(prompt)
                    .call()
                    .entity(new BeanOutputConverter<>(CareerPathConnection.class));
        } catch (Exception e) {
            throw new LlmServiceUnavailableException("AI service unavailable for Connections", e);
        }
    }
}
