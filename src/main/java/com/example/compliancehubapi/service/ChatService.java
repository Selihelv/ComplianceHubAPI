package com.example.compliancehubapi.service;


import com.example.compliancehubapi.tools.DateTimeTools;
import com.example.compliancehubapi.tools.UserTools;
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

    private final GuardrailService guardrailService;

    public ChatService(ChatClient.Builder chatClientBuilder, ChatMemory chatMemory, DateTimeTools dateTimeTools, UserTools userTools, GuardrailService guardrailService) {
        this.chatClient = chatClientBuilder.build();
        this.dateTimeTools = dateTimeTools;
        this.userTools = userTools;
        this.guardrailService = guardrailService;
        this.chatClientWithMemory = chatClientBuilder
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .build();
        this.chatMemory = chatMemory;
    }


    public String ask(String question){
        return chatClient.prompt(question)
                .call()
                .content();
    }

public String chatbot(String conversationId, String message){

        String guardrailResponse = guardrailService.ask(message);
      if(guardrailResponse.equals("DENIED REQUEST")){
          return "DENIED REQUEST";
      }


        return chatClientWithMemory
                .prompt(message)
                .system("You are a helpful Compliance Assistant.")
                .advisors(advisor -> advisor.param(ChatMemory.CONVERSATION_ID, conversationId) )
                .tools(dateTimeTools, userTools)
                .call()
                .content();
}

}
