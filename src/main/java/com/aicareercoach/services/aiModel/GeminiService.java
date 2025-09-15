package com.aicareercoach.services.aiModel;

import com.aicareercoach.model.quizResponse.CareerOptionForGraduates;
import com.aicareercoach.model.quizResponse.CareerOptionForStudent;
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
                        (new ParameterizedTypeReference<List<CareerOptionForGraduates>>() {})
               );

    }

}
