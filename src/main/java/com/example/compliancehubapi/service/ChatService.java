package com.example.compliancehubapi.service;


import com.example.compliancehubapi.tools.*;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatClient chatClient;
    private final ChatClient chatClientWithMemory;
    private final ChatMemory chatMemory;
    private final DateTimeTools dateTimeTools;
    private final UserTools userTools;
    private final UserProfileTools userProfileTools;
    private final RegulationTools regulationTools;
    private final ComplianceDocumentTools complianceDocumentTools;


    public ChatService(ChatClient.Builder chatClientBuilder, ChatMemory chatMemory, DateTimeTools dateTimeTools,
                       UserTools userTools, UserProfileTools userProfileTools, RegulationTools regulationTools,
                       ComplianceDocumentTools complianceDocumentTools) {
        this.chatClient = chatClientBuilder.build();
        this.dateTimeTools = dateTimeTools;
        this.userTools = userTools;
        this.chatClientWithMemory = chatClientBuilder
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .build();
        this.chatMemory = chatMemory;
        this.userProfileTools = userProfileTools;
        this.regulationTools = regulationTools;
        this.complianceDocumentTools = complianceDocumentTools;
    }


    public String ask(String question) {
        return chatClient.prompt(question)
                .call()
                .content();
    }

    public String chatbot(String conversationId, String message) {
        return chatClientWithMemory
                .prompt(message)
                .system("You are an AI agent with the responsibility of answering questions about sellers compliance. " +
                        "If they ask anything unrelated to the compliance subjects, you should politely decline and offer help with the compliance subjects.")
                .advisors(advisor -> advisor.param(ChatMemory.CONVERSATION_ID, conversationId))
                .tools(dateTimeTools, userTools, userProfileTools, regulationTools, complianceDocumentTools)
                .call()
                .content();
    }

}
