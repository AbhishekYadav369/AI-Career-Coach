package com.aicareercoach.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.model.ChatResponse;

import org.springframework.ai.chat.prompt.Prompt;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OllamaService {
    @Autowired
    private EmbeddingModel embeddingModel;
    @Autowired
    private PromptService promptService;
    private final ChatClient chatClient;

    private OllamaService(ChatClient.Builder builder) {

        ChatMemory chatMemory = MessageWindowChatMemory.builder().build();
        this.chatClient = builder
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build()).build();
    }

    public String generateResponse(String interest, String skills, String goals, String comfort) {
        /* Create a prompt using the PromptService and pass the parameters to it
         The prompt will be used to generate a response from the chat client*/
        Prompt prompt = promptService.getPromptForCollege(interest, skills, goals,comfort);
        ChatResponse chatResponse = chatClient.prompt(prompt)
                .call()
                .chatResponse();
        System.out.println(chatResponse.getMetadata().getModel());

        return chatResponse
                .getResult()
                .getOutput()
                .getText();

    }
    public float[] getEmbeddingModel(String message) {
        return embeddingModel.embed(message);
    }
}
