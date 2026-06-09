package com.example.compliancehubapi.service;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class GuardrailService {

    private final ChatClient chatClient;

    public GuardrailService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();

    }


    public String ask(String question) {
        return chatClient.prompt(question)
                .system("You are an AI agent with the responsibility of answering questions about sellers compliance . If they ask about football, you should politely decline.")
                .call()
                .content();
    }


}
