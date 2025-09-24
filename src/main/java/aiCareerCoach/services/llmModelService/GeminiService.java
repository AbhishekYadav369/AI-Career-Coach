package aiCareerCoach.services.llmModelService;

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
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build()).build();
    }

    public List<CareerOptionForStudent> generateResponseForStudent(Prompt prompt) {

        return chatClient.prompt(prompt)
                .call()
                .entity(new BeanOutputConverter<List<CareerOptionForStudent>>
                        (new ParameterizedTypeReference<List<CareerOptionForStudent>>() {
                        })
                );
    }

    public List<CareerOptionForGraduates> generateResponseForGraduate(Prompt prompt) {

        return chatClient.prompt(prompt)
                .call()
                .entity(new BeanOutputConverter<List<CareerOptionForGraduates>>
                        (new ParameterizedTypeReference<List<CareerOptionForGraduates>>() {
                        })
                );

    }

    public SkillsRoadmapResponse generateRoadmap(Prompt prompt) {

            // Convert LLM response to POJO
            return chatClient.prompt(prompt)
                    .call()
                    .entity(new BeanOutputConverter<>(SkillsRoadmapResponse.class));



//        ChatResponse chatResponse = chatClient.prompt(prompt)
//                .call()
//                .chatResponse();
//
//        return Optional.ofNullable(chatResponse)
//                .map(ChatResponse::getResult)
//                .map(Generation::getOutput)
//                .map(AbstractMessage::getText)
//                .orElse("No response from LLM model");

    }

// this return Ai Enhanced json
    public ResumeDTO generateResumeJSON(Prompt prompt) {
        return chatClient.prompt(prompt)
                    .call()
                    .entity(new BeanOutputConverter<>(ResumeDTO.class));
    }

//this return linkedin connection list based on  career paths
    public CareerPathConnection getConnections(Prompt prompt) {
        return chatClient.prompt(prompt)
                .call()
                .entity(new BeanOutputConverter<>(CareerPathConnection.class));
    }
}